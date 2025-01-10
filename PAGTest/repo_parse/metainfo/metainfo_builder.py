from abc import ABC, abstractmethod
import json
from typing import Dict, List

from repo_parse.metainfo.model import Class, File, Method, TestClass, TestMethod
from repo_parse.utils.data_processor import save_json
from repo_parse.config import ALL_METAINFO_PATH, CLASS_METAINFO_PATH, FILE_IMPORTS_PATH, METHOD_METAINFO_PATH, PACKAGES_METAINFO_PATH, RESOLVED_METAINFO_PATH, TESTCASE_METAINFO_PATH, TESTCLASS_METAINFO_PATH
from repo_parse import logger


class MetaInfoBuilder(ABC):
    def __init__(self, metainfo_json_path: str = ALL_METAINFO_PATH,
                 resolved_metainfo_path: str = RESOLVED_METAINFO_PATH):
        self.metainfo_json_path = metainfo_json_path
        self.metainfo = self.load_metainfo()
        self.resolved_metainfo_path = resolved_metainfo_path
        self.methods: List[Method] = []
        self.classes: List[Class] = []
        self.testcases: List[TestMethod] = []
        self.testclasses: List[TestClass] = []
        self.files: List[File] = []
    def load_metainfo(self):
        with open(self.metainfo_json_path) as f:
            metainfo = json.load(f)
        
        return metainfo
    
    @abstractmethod
    def build_metainfo(self):
        pass

    def save_metainfo(self, path_to_data: Dict[str, List[Dict]]):
        def save_data(file_path, data):
            save_json(file_path, [item.to_json() for item in data])
            
        for path, data in path_to_data.items():
            save_data(path, data)

        logger.info("save metainfo success!")


