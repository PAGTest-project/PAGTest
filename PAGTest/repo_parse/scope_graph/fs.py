from pathlib import Path
from typing import Iterator, Tuple

from repo_parse.scope_graph.config import FILE_GLOB_ENDING, LANGUAGE
from repo_parse.scope_graph.repo_resolution.namespace import NameSpace

import logging

logger = logging.getLogger(__name__)

SRC_EXT = FILE_GLOB_ENDING[LANGUAGE]


class RepoFs:
    def __init__(self, repo_path: Path):
        self.path = repo_path
        self._all_paths = self._get_all_paths()

    def get_files_content(self) -> Iterator[Tuple[Path, bytes]]:
        for file in self._all_paths:
            if file.suffix == SRC_EXT:
                yield file, file.read_bytes()

    def match_file(self, ns_path: Path) -> Path:
        for path in self._all_paths:
            path_name = path.name.replace(SRC_EXT, "")
            match_path = list(path.parts[-len(ns_path.parts) : -1]) + [path_name]

            if match_path == list(ns_path.parts):
                if path.suffix == SRC_EXT:
                    return path.resolve()
                elif path.is_dir():
                    return (path / "__init__.py").resolve()

        return None

    def _get_all_paths(self):
        return [p for p in self.path.rglob("*") if p.suffix == SRC_EXT or p.is_dir()]
