from pydantic import BaseModel

from dataclasses import dataclass
from typing import NamedTuple, TypeAlias, Tuple, List
import json
from repo_parse.scope_graph.config import SYS_MODULES_LIST, THIRD_PARTY_MODULES_LIST
from pathlib import Path

from logging import getLogger

logger = getLogger(__name__)

SymbolId: TypeAlias = str

class Point(NamedTuple):
    row: int
    column: int


class TextRange(BaseModel):
    start_byte: int
    end_byte: int
    start_point: Point
    end_point: Point

    def __init__(
        self,
        *,
        start_byte: int,
        end_byte: int,
        start_point: Tuple[int, int],
        end_point: Tuple[int, int],
    ):
        super().__init__(
            start_byte=start_byte,
            end_byte=end_byte,
            start_point=start_point,
            end_point=end_point,
        )

    def line_range(self):
        return self.start_point.row, self.end_point.row

    def contains(self, range: "TextRange"):
        return range.start_byte >= self.start_byte and range.end_byte <= self.end_byte

    def contains_line(self, range: "TextRange", overlap=False):
        if overlap:
            return (
                range.start_point.row >= self.start_point.row
                and range.start_point.row <= self.end_point.row
            ) or (
                range.end_point.row <= self.end_point.row
                and range.end_point.row >= self.start_point.row
            )

        return (
            range.start_point.row >= self.start_point.row
            and range.end_point.row <= self.end_point.row
        )

def get_shortest_subpath(path: Path, root: Path) -> Path:
    return path.relative_to(root)

class SysModules:
    def __init__(self, lang):
        try:
            sys_mod_file = open(SYS_MODULES_LIST, "r")
            self.sys_modules = json.loads(sys_mod_file.read())
        except Exception as e:
            logger.error(f"Error loading system modules: {e}")
            self.sys_modules = []

    def __iter__(self):
        return iter(self.sys_modules)

    def check(self, module_name):
        return module_name in self.sys_modules


class ThirdPartyModules:
    def __init__(self, lang):
        self.lang = lang
        try:
            with open(THIRD_PARTY_MODULES_LIST, "r") as file:
                self.third_party_modules = json.loads(file.read())["modules"]
        except Exception as e:
            logger.error(f"Error loading third party modules: {e}")
            self.third_party_modules = []

    def check(self, module_name):
        return module_name in self.third_party_modules

    def __iter__(self):
        return iter(self.third_party_modules)

    def update(self, new_modules: List[str]):
        self.third_party_modules.extend(new_modules)
        try:
            with open(THIRD_PARTY_MODULES_LIST, "w") as file:
                json.dump({"modules": self.third_party_modules}, file, indent=4)
        except Exception as e:
            logger.error(f"Error writing third party modules: {e}")
