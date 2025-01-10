import traceback
from typing import Dict, List, Tuple
from tree_sitter_languages import get_language, get_parser

from repo_parse.scope_graph.scope_resolution import LocalScope, LocalDef, Reference, Scoping
from repo_parse.scope_graph.scope_resolution.imports import (
    LocalImportStmt,
    parse_from,
    parse_alias,
    parse_name,
)
from repo_parse.scope_graph.utils import TextRange
from repo_parse.scope_graph.scope_resolution.graph import ScopeGraph

from repo_parse.scope_graph.ts.capture_types import (
    LocalDefCapture,
    LocalRefCapture,
    LocalImportPartCapture,
    ImportPartType,
)

from repo_parse.config import JAVA_SCM

NAMESPACES = {
    "python": ["class", "function", "parameter", "variable"],
    "java": ["class", "method", "parameter", "variable"]
}

def get_language_query_file(language: str) -> str:
    if language == "java":
        return JAVA_SCM
    else:
        raise ValueError(f"Unsupported language: {language}")

def build_query(file_content: bytearray, language: str) -> Tuple:
    try:
        language_sitter = get_language(language)
        parser = get_parser(language)
        query_file_path = get_language_query_file(language)
        query_file_content = open(query_file_path, "rb").read()
        
        root = parser.parse(file_content).root_node
        query = language_sitter.query(query_file_content)
        return query, root
    except Exception as e:
        raise RuntimeError(f"Failed to build query for language {language}: {e}, trace: {traceback.format_exc()}")

def build_scope_graph(src_bytes: bytearray, language: str = "python") -> ScopeGraph:
    query, root_node = build_query(src_bytes, language)
    
    local_def_captures: List[LocalDefCapture] = []
    local_ref_captures: List[LocalRefCapture] = []
    local_scope_capture_indices: List[int] = []
    local_import_stmt_capture_indices: List[int] = []
    local_import_part_captures: List[LocalImportPartCapture] = []

    capture_map: Dict[int, TextRange] = {}
    
    for i, (node, capture_name) in enumerate(query.captures(root_node)):
        capture_map[i] = TextRange(
            start_byte=node.start_byte,
            end_byte=node.end_byte,
            start_point=node.start_point,
            end_point=node.end_point,
        )
        parts = capture_name.split(".")
        match parts:
            case [scoping, "definition", sym]:
                local_def_captures.append(LocalDefCapture(
                    index=i, symbol=sym, scoping=Scoping(scoping)))
            case [scoping, "definition"]:
                local_def_captures.append(LocalDefCapture(
                    index=i, symbol=None, scoping=Scoping(scoping)))
            case ["local", "reference"]:
                local_ref_captures.append(LocalRefCapture(index=i, symbol=None))
            case ["local", "scope"]:
                local_scope_capture_indices.append(i)
            case ["local", "import", "statement"]:
                local_import_stmt_capture_indices.append(i)
            case ["local", "import", part]:
                local_import_part_captures.append(LocalImportPartCapture(index=i, part=part))

    root_range = TextRange(
        start_byte=root_node.start_byte,
        end_byte=root_node.end_byte,
        start_point=root_node.start_point,
        end_point=root_node.end_point,
    )
    scope_graph = ScopeGraph(root_range, src_bytes=src_bytes)

    for i in local_scope_capture_indices:
        scope_graph.insert_local_scope(LocalScope(capture_map[i]))

    for i in local_import_stmt_capture_indices:
        range = capture_map[i]
        from_name, aliases, names = "", [], []
        for part in local_import_part_captures:
            part_range = capture_map[part.index]
            if range.contains(part_range):
                match part.part:
                    case ImportPartType.MODULE:
                        from_name = parse_from(src_bytes, part_range)
                    case ImportPartType.ALIAS:
                        aliases.append(parse_alias(src_bytes, part_range))
                    case ImportPartType.NAME:
                        names.append(parse_name(src_bytes, part_range))

        import_stmt = LocalImportStmt(
            range, names, from_name=from_name, aliases=aliases
        )
        scope_graph.insert_local_import(import_stmt)

    for def_capture in local_def_captures:
        range = capture_map[def_capture.index]
        local_def = LocalDef(range, src_bytes, def_capture.symbol)
        match def_capture.scoping:
            case Scoping.GLOBAL:
                scope_graph.insert_global_def(local_def)
            case Scoping.HOISTED:
                scope_graph.insert_hoisted_def(local_def)
            case Scoping.LOCAL:
                scope_graph.insert_local_def(local_def)

    for local_ref_capture in local_ref_captures:
        range = capture_map[local_ref_capture.index]
        symbol_id = local_ref_capture.symbol if local_ref_capture.symbol in NAMESPACES[language] else None
        new_ref = Reference(range, src_bytes, symbol_id=symbol_id)
        scope_graph.insert_ref(new_ref)

    return scope_graph