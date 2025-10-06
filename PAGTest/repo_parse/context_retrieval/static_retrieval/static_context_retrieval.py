from abc import ABC, abstractmethod

from typing import Dict, List
from repo_parse.metainfo.model import Class, TestCase
from repo_parse import logger


class Finds:
    def __init__(self):
        self.find_in_fileds = []
        self.find_in_methods = []
        self.class_finds = []
        self.record_finds = []
        self.interface_finds = []


class StaticContextRetrieval(ABC):
    def __init__(self, std_lib_path, keywords_and_builtin_path):
        self.std_lib_path = std_lib_path
        self.keywords_and_builtin_path = keywords_and_builtin_path
        self.std_lib = self.load_std_lib()
        self.keywords_and_builtin = self.load_keywords_and_builtin()
        self.class_map = {cls['name']: cls for cls in self.class_metainfo}

    def load_std_lib(self) -> List[str]:
        pass

    def load_keywords_and_builtin(self):
        pass
    
    def pack_package_info(self, unresolved_refs, file_path, original_string):
        pass
    
    def pack_repo_info(self, unresolved_refs, imports):
        pass
    
    def pack_package_info_use_dot(self, unresolved_refs, file_path, original_string):
        pass
    
    @abstractmethod
    def pack_method_class_info(self, method: Dict, is_montage=False) -> str:
        pass
    
    def prune_class(self, _class, method_names=None, save_constructors=True):
        pass
    
    def pack_pruned_class_description(self, pruned_class):
        pass
    
    def pack_repo_info_use_dot(self, unresolved_refs, original_string, imports):
        pass
    
    @abstractmethod
    def pack_testcase_file_level_context(self, testcase: TestCase):
        pass
    
    @abstractmethod
    def find_file_level_context(self, code_block, where, language='python'):
        pass
    
    @abstractmethod
    def get_inherited_methods(self, cls_name) -> Dict[str, List[str]]:
        pass
    
    @abstractmethod
    def get_methods_original_string(self, class_methods_dict: Dict[str, List[str]]):
        pass
    
    @abstractmethod
    def pack_inherited_method_info(self, _class, inherited_method_info):
        pass
    
    def get_inherited_method_info(self, _class: Class) -> str:
        inherit_methods = self.get_inherited_methods(cls_name=_class['name'])
        
        if inherit_methods:
            inherit_methods_original_string = self.get_methods_original_string(class_methods_dict=inherit_methods)
            logger.info(f"Get inherited methods from super class: {_class['name']}")
            return inherit_methods_original_string
        return ''



