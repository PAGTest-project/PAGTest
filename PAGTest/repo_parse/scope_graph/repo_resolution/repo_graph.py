from typing import Any, List, Dict, Tuple, NewType
from pathlib import Path
from networkx import DiGraph

from repo_parse.scope_graph.fs import RepoFs
from repo_parse.scope_graph.scope_resolution.graph import ScopeGraph
from repo_parse.scope_graph.scope_resolution.graph_types import ScopeID
from repo_parse.scope_graph.build_scopes import build_scope_graph
from repo_parse.scope_graph.scope_resolution import LocalImportStmt
from repo_parse.scope_graph.utils import SysModules, ThirdPartyModules, TextRange
from repo_parse.scope_graph.config import LANGUAGE

from .imports import NameSpace, LocalImport, ModuleType, import_stmt_to_import
from .graph_type import EdgeKind, RepoNode, RepoNodeID

from collections import defaultdict
import logging

logger = logging.getLogger(__name__)


def repo_node_id(file: Path, scope_id: ScopeID):
    return "".join([str(file), "::", str(scope_id)])

class RepoGraph:
    """
    Constructs a graph of relation between the scopes of a repo
    """

    def __init__(self, path: Path):
        if not path.exists():
            raise FileNotFoundError(f"Path {path} does not exist")

        self.fs = RepoFs(path)
        self._graph = DiGraph()
        self.scopes_map: Dict[Path, ScopeGraph] = self._construct_scopes(self.fs)

        self._imports: Dict[Path, List[LocalImport]] = {}

        self._missing_import_refs: Dict[Path, List[str]] = {}
        self._resolved_import_refs: Dict[Path, List[str]] = defaultdict(list)
        self.total_scopes = set()

        for path, g in self.scopes_map.items():
            self._imports[path] = self._construct_import(g, path, self.fs)
            self._missing_import_refs[path] = [
                str(imp.namespace) for imp in self._imports[path]
            ]

        for path, imports in self._imports.items():
            imp2def: List[Tuple[LocalImport, str, ScopeID, Path]] = []

            local_imports = [
                local_imp
                for local_imp in imports
                if local_imp.module_type == ModuleType.LOCAL
            ]
            imp2def.extend(self.map_local_to_exports(path, local_imports))

            for imp, def_scope, name, export_file in imp2def:
                if imp.module_type == ModuleType.LOCAL:
                    for ref_scope in imp.ref_scopes:
                        if "client.py" in str(path):
                            print(
                                f"Adding ref: {imp.namespace.child}:{ref_scope} -> {export_file}:{def_scope}"
                            )

                        ref_node_id = repo_node_id(path, ref_scope)
                        ref_node = self.get_node(ref_node_id)
                        if not ref_node:
                            self.total_scopes.add(ref_node_id)
                            ref_node = self.create_node(ref_node_id)

                        self._missing_import_refs[path] = [
                            ref
                            for ref in self._missing_import_refs[path]
                            if ref != str(imp.namespace)
                        ]
                        self._resolved_import_refs[path].append(name)

                        imp_node_id = repo_node_id(export_file, def_scope)
                        imp_node = self.get_node(imp_node_id)
                        if not imp_node:
                            self.total_scopes.add(ref_node_id)
                            self.create_node(imp_node_id)

                        self._graph.add_edge(
                            ref_node_id,
                            imp_node_id,
                            kind=EdgeKind.ImportToExport,
                        )

    def get_node(self, node_id: RepoNodeID) -> RepoNode:
        node = self._graph.nodes.get(node_id, None)
        if node:
            return RepoNode(repo_id=node_id)

        return None

    def create_node(self, node_id: RepoNodeID):
        node = RepoNode(repo_id=node_id)

        self._graph.add_node(node.repo_id, name=node.name)
        return node

    def import_to_export_scope(self, ref_node_id: RepoNodeID) -> List[RepoNode]:
        return [
            self.get_node(v)
            for _, v, attrs in self._graph.edges(ref_node_id, data=True)
            if attrs["kind"] == EdgeKind.ImportToExport
        ]

    def map_local_to_exports(
        self, path: Path, imports: List[LocalImport]
    ) -> List[Tuple[LocalImport, str, ScopeID, Path]]:
        imp2def = []

        for imp in imports:
            export_file = self.fs.match_file(imp.namespace.to_path())
            if export_file:
                if "__init__.py" in str(export_file):
                    imports = self._imports[path]
                    pass
                else:
                    for name, def_scope in self._get_exports(
                        self.scopes_map[export_file], export_file
                    ):
                        if imp.namespace.child == name:
                            imp2def.append((imp, def_scope, name, export_file))

        return imp2def

    def _construct_scopes(self, fs: RepoFs) -> Dict[Path, ScopeGraph]:
        scope_map = {}
        for path, file_content in fs.get_files_content():
            scope_map[path.resolve()] = build_scope_graph(
                file_content, language=LANGUAGE
            )

        return scope_map

    def _construct_import(
        self, g: ScopeGraph, file: Path, fs: RepoFs
    ) -> Dict[Path, List[LocalImport]]:
        sys_modules_list = SysModules(LANGUAGE)
        third_party_modules_list = ThirdPartyModules(LANGUAGE)

        imports = []
        for imp_node in g.get_all_imports():
            imp_stmt = LocalImportStmt(imp_node.range, **imp_node.data)
            imp_blocks = import_stmt_to_import(
                import_stmt=imp_stmt,
                filepath=file,
                g=g,
                fs=fs,
                sys_modules=sys_modules_list,
                third_party_modules=third_party_modules_list,
            )
            imports.extend(imp_blocks)

        return imports

    def _get_exports(self, g: ScopeGraph, file: Path) -> List[Tuple[str, ScopeID]]:
        exports = []
        outer_scopes = [g.root_idx] + [s for s in g.child_scopes(g.root_idx)]

        for scope in outer_scopes:
            for def_node in g.definitions(scope):
                if scope != g.root_idx and (
                    def_node.data["def_type"] == "class"
                    or def_node.data["def_type"] == "function"
                ):
                    exports.append((def_node.name, scope))

        return exports

    def to_str(self):
        repr = ""
        for u, v, _ in self._graph.edges(data=True):
            u = self.get_node(u)
            v = self.get_node(v)
            repr += f"{u} -> {v}\n"

        return repr

    def print_missing_imports(self):
        for path, missing_imports in self._missing_import_refs.items():
            total_missing = 0
            total_resolved = 0

            for missed in missing_imports:
                total_missing += 1
            if self._resolved_import_refs.get(path, None):
                for resolved in self._resolved_import_refs[path]:
                    total_resolved += 1