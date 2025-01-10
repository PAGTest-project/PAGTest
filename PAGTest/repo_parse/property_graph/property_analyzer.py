
import json
import os
from typing import Any, Dict

from repo_parse.llm.deepseek_llm import DeepSeekLLM
from repo_parse.llm.llm import LLM
from repo_parse.analysis.analyzer import Analyzer
from repo_parse.config import CLASS_METAINFO_PATH, FUNC_RELATION_PATH, METHOD_PROPERTY_DIR
from repo_parse.context_retrieval.static_retrieval.java_static_context_retrieval import JavaStaticContextRetrieval
from repo_parse.context_retrieval.static_retrieval.static_context_retrieval import StaticContextRetrieval
from repo_parse.prompt.graph_builder import Function_Relation_Prompt
from repo_parse.prompt.property_analyzer import Prompt
from repo_parse.utils.data_processor import load_json, save_json
from repo_parse import logger


class PropertyAnalyzer(Analyzer):
    def __init__(self, 
                 llm: LLM = None,
                 static_context_retrieval: StaticContextRetrieval = None,
                 func_relation_path: str = FUNC_RELATION_PATH):
        Analyzer.__init__(self, llm=llm)
        self.static_context_retrieval = static_context_retrieval
        self.func_relation_path = func_relation_path
        self.method_property_dir = METHOD_PROPERTY_DIR
        self.cache = self._load_cache()
        
    def excute(self):
        pass
        
    def call_llm(self, system_prompt, user_input) -> str:
        full_response = self.llm.chat(system_prompt, user_input)
        return full_response
    
    def _load_cache(self) -> Dict[Any, Any]:
        cache_file = os.path.join(self.method_property_dir, 'cache.json')
        if os.path.exists(cache_file):
            with open(cache_file, 'r') as f:
                return json.load(f)
        return {}

    def _save_cache(self):
        cache_file = os.path.join(self.method_property_dir, 'cache.json')
        with open(cache_file, 'w') as f:
            json.dump(self.cache, f, indent=4)
            
    def pack_inherit_context(self, _class: Dict):
        class_name = _class["name"]
        inherited_method_info = self.static_context_retrieval.get_inherited_method_info(_class=_class)
        _class['original_string'] = self.static_context_retrieval.pack_inherited_method_info(_class, inherited_method_info)
        if _class['super_interfaces']:
            for interface_name in _class['super_interfaces']:
                interface_info = self.fuzzy_get_interface(interface_name)
                if interface_info is None:
                    continue
                _class['original_string'] += '\n Interface code: ' + interface_info['original_string'] + '\n'
    
    def get_related_method(self, _class: Dict, target_method: str):
        class_name = _class["name"]
        cache_key = class_name + '.' + target_method
        if cache_key in self.cache:
            logger.info(f"Using cached result for class: {class_name}, method: {target_method}")
            file_path = self.cache[cache_key]
            return load_json(file_path)
        
        try:
            self.pack_inherit_context(_class)
            user_input = 'The target method is:\n' + target_method + '\n'
            original_string = 'The class is:\n' + _class["original_string"]
            imports = self.get_imports(file_path=_class['file_path'])
            imports_str = '\n'.join(imports)

            unresolved_refs = self.static_context_retrieval.find_unresolved_refs(original_string)
            package_class_montages = self.static_context_retrieval.pack_package_info(unresolved_refs, _class['file_path'], original_string)
            package_class_montages_description = self.pack_package_class_montages_description(package_class_montages)
            
            user_input += imports_str + original_string + package_class_montages_description
            
            full_response = self.call_llm(system_prompt=Prompt, user_input=user_input)
            resp_dict = self.extract(full_response)
                        
            file_path = self.method_property_dir + '_' + class_name + '_' + target_method + '.json'
            save_json(file_path=file_path, data=resp_dict)
            
            self.cache[cache_key] = file_path
            self._save_cache()
            
        except Exception as e:
            logger.exception(f"Error in class {class_name} get_related_method: {e}")
            return {}
        
        logger.info("Finished resolving property")
        return resp_dict
    
    def resolve_class_level_relation(self):
        results = []
        for _class in self.class_metainfo:
            try:
                class_name = _class["name"]
                inherited_method_info = self.static_context_retrieval.get_inherited_method_info(_class=_class)
                _class['original_string'] = self.static_context_retrieval.pack_inherited_method_info(_class, inherited_method_info)
                    
                original_string = _class["original_string"]
                full_response = self.call_llm(system_prompt=Function_Relation_Prompt, user_input=original_string)
                resp_dict = self.extract(full_response)

                results.append(resp_dict)
                logger.info(f"Resolving class level relation for class: {class_name}")
            except Exception as e:
                logger.exception(f"Error in resolving class level relation: {e}")
                
        save_json(file_path=self.func_relation_path, data=results)
        logger.info("Finished resolving class level relation")
    
    def resolve_repo_level_relation(self):
        pass
    
    def resolve_func_level_relation(self):
        pass


class JavaPropertyAnalyzer(PropertyAnalyzer):
    def __init__(self, 
                 llm: LLM = None):
        static_context_retrieval = JavaStaticContextRetrieval()
        PropertyAnalyzer.__init__(self, llm=llm, static_context_retrieval=static_context_retrieval)


def run_property_analyzer(analyzer: PropertyAnalyzer):
    logger.info("run_property_analyzer started")
    analyzer.resolve_class_level_relation()
    logger.info("run_property_analyzer finished")