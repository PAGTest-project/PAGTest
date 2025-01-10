import os
import re
import time
import traceback
from typing import Any, Dict, List, Set, Tuple

from repo_parse.llm.deepseek_llm import DeepSeekLLM
from repo_parse.llm.llm import LLM
from repo_parse.analysis.analyzer import Analyzer
from repo_parse.config import BROTHER_ENHANCEMENTS_PATH, CHILD_ENHANCEMENTS_PATH, CLASS_TO_PRIMARY_TESTCASE_PATH, CLASS_TO_RELEVANT_TESTCASE_PATH, COMPLETED_TESTCASES_DIR, GENERATED_TESTCASES_DIR, GENERATED_TESTCASES_PATH, INTERFACE_BROTHER_ENHANCEMENTS_PATH, INTERFACE_BROTHER_RELATIONS_PATH, JUNIT_VERSION_PATH, METHOD_METAINFO_PATH, METHOD_TO_PRIMARY_TESTCASE_PATH, METHOD_TO_RELEVANT_TESTCASE_PATH, NODE_TO_TESTCASE_PATH, PARENT_ENHANCEMENTS_PATH, POTENTIAL_BROTHER_RELATIONS_PATH, SKIP_IF_NOT_RELEVANT_TESTFILES_FOR_CLASS, TESTCLASS_ANALYSIS_RESULT_PATH
from repo_parse.context_retrieval.static_retrieval.java_static_context_retrieval import JavaStaticContextRetrieval
from repo_parse.context_retrieval.static_retrieval.static_context_retrieval import StaticContextRetrieval
from repo_parse.metainfo.inherit_resolver import inherit_resolver
from repo_parse import logger
from repo_parse.prompt.testcase_generator import CLASS_LEVEL_PROMPT_JAVA, FALLBACK_PROMPT_JAVA, FIX_PROMPT_JAVA
from repo_parse.property_graph.property_analyzer import JavaPropertyAnalyzer
from repo_parse.utils.data_processor import extract_code_from_json, load_file, load_json, save_json


class TestcaseGenerator(Analyzer):
    def __init__(self, 
                 llm: LLM = None,
                 static_context_retrieval: StaticContextRetrieval = None,
                 property_graph_retrieval = None):
        Analyzer.__init__(self, llm=llm)
        self.node_to_testcase = []
        self.testclass_analysis_result = load_json(TESTCLASS_ANALYSIS_RESULT_PATH)
        self.static_context_retrieval = static_context_retrieval
        self.property_graph_retrieval = property_graph_retrieval
        self.method_to_primary_testcase = load_json(METHOD_TO_PRIMARY_TESTCASE_PATH)
        self.method_to_relevant_testcase = load_json(METHOD_TO_RELEVANT_TESTCASE_PATH)
        
    def excute(self):
        self.generate_testcases()
        
    def reslove_history_testcase_paths(self, testclass, update):
        pass
    
    def call_llm(self, system_prompt, user_input) -> str:
        full_response = self.llm.chat(system_prompt, user_input)
        return full_response
    
    def pack_repo_level_testcase_context(self):
        pass
    
    def pack_class_level_testcase_context(self, class_name: str, relations: List[Dict[str, Any]]) -> str:
        relation_description = ''
        
        for relation in relations:
            existing_testcases = set()
            
            for related_func in relation['enhancers']:
                resource = self.pgr.get_resource(func_uri=f"{class_name}.{related_func}")
                if resource is None:
                    continue
                
                for testcase_list in resource.values():
                    existing_testcases.update(testcase_list)
            
            if not existing_testcases:
                continue
            
            related_funcs = ', '.join(relation['enhancers'])
            enhanced_methods = ', '.join(relation['enhanced_methods'])
            
            existing_testcases_string = self.static_context_retrieval.pack_testcases_original_string(existing_testcases)
            
            relation_description += (
                "In the current class, the methods " + str(related_funcs) + " have a " +
                str(relation['relationship_type']) + " relationship with " + str(enhanced_methods) + ". "
                "Specifically, " + str(relation['description']) + ". "
                "The test cases for " + str(related_funcs) + " might provide some useful insights to help you generate test cases. "
                "Here is the information for these test cases:\n" +
                existing_testcases_string + "\n"
            )

        return relation_description
    
    def class_level_relation_retrieval(self, func_uri: str):
        realations = self.pgr.class_level_retrieval(func_uri)
        if realations is None:
            return ''
        
        class_name = func_uri.split(".")[0]
        relation_description = self.static_context_retrieval.pack_class_level_testcase_context(class_name, realations)
        return relation_description
    
    def repo_level_relation_retrieval(self, where_to_find, class_name: str, method_uri: str):
        pass

    def generate_testcases(self, func_uri: str):
        pass

    def complete_testcase(self, func_uri: str):
        pass


class JavaTestcaseGenerator(TestcaseGenerator):
    def __init__(self, 
                 llm: LLM = None):
        static_context_retrieval = JavaStaticContextRetrieval()
        self.property_analyzer = JavaPropertyAnalyzer(llm=llm)
        TestcaseGenerator.__init__(self, 
                                   llm=llm, 
                                   static_context_retrieval=static_context_retrieval)
        self.junit_version = load_json(JUNIT_VERSION_PATH).get('junit_version')
        self.class_to_primary_testcase = load_json(CLASS_TO_PRIMARY_TESTCASE_PATH)
        self.class_to_relevant_testcase = load_json(CLASS_TO_RELEVANT_TESTCASE_PATH)
        self.inherit_resolver = inherit_resolver()
        self.interface_brother_relations = load_json(INTERFACE_BROTHER_RELATIONS_PATH)
        self.potential_brother_relations = load_json(POTENTIAL_BROTHER_RELATIONS_PATH)
        self.pruned_class_description = None
        self.brother_enhancements = load_json(BROTHER_ENHANCEMENTS_PATH)
        self.parent_enhancements = load_json(PARENT_ENHANCEMENTS_PATH)
        self.child_enhancements = load_json(CHILD_ENHANCEMENTS_PATH)
        self.interface_brother_enhanements = load_json(INTERFACE_BROTHER_ENHANCEMENTS_PATH)

    def _add_enhanced_methods(self, method_names: set, gwt_enhancements: Dict, phase: str):
        enhanced_by = gwt_enhancements.get(phase, {}).get('enhanced_by', [])
        for item in enhanced_by:
            method_names.add(item['method_name'])
            
    def _refined_add_enhanced_methods(self, method_names: set, gwt_enhancements: Dict, phase: str):
        enhanced_by = gwt_enhancements.get(phase, {}).get('enhanced_by', [])
        for item in enhanced_by:        
            if item.get('is_external', False) == False:
                method_names.add(item['method_name'] + "#" + str(item['is_external']) + "#" + "This")
            else:
                method_names.add(item['method_name'] + "#" + str(item['is_external']) + "#" + item.get('class_name', 'None'))

    def _find_testcase(self, testcase_name: str, testclass_analysis_result: List[Dict]):
        testcases = testclass_analysis_result['test_cases']
        for item in testcases:
            if testcase_name == item['name']:
                return item
        
    def retry_generate_testcases(self, method: Dict, parsed_errors: List[str], save: bool = True):
        result = []
        generated_class_name = None
        try:
            logger.info('Start retry_generate_testcases for method: ' + method['name'])
            _class = self.find_class(uri=method['class_uri'])
            if not _class:
                logger.error(f'No class found for {method["name"]}')
                return None
            class_name = _class['name']
            original_string = method['original_string']
            target_method = method['name']
            new_class_name = JavaTestcaseGenerator.get_new_class_name(class_name=class_name, target_method=target_method)
            json_path = GENERATED_TESTCASES_DIR + new_class_name + '.json'
            code_path = json_path.split('.json')[0] + '.java'
            code_to_fix = load_file(file_path=code_path)
            if code_to_fix is None:
                logger.error(f'No code to fix for {method["name"]}')
                return
            
            fix_suggestion = ""
            
            for i in range(len(parsed_errors)):
                j = i + 1
                if "Unhandled exception type" in parsed_errors[i]:
                    fix_suggestion += "Did you forget to handle the exception?\n"
                elif "找不到符号" in parsed_errors[i] and j < len(parsed_errors):
                    if "类" in parsed_errors[j]:
                        class_name = parsed_errors[j].split("类")[1].strip()
                        missed_class = self.fuzzy_get_class(class_name)
                        if missed_class is not None:
                            file_path = missed_class['file_path']
                            package_info = self.file_imports_metainfo[file_path][0]
                            if 'package' in package_info:
                                import_statement = f"import {package_info}.{class_name};"
                                fix_suggestion += f"\nConsider adding the following import statement at the top of your file:\n{import_statement}\n"
            self.property_analyzer.pack_inherit_context(_class=_class)

            junit_version_description = "The junit version is " + str(self.junit_version) + "\n"
            class_original_string = self.pruned_class_description if self.pruned_class_description else _class['original_string']
            class_str = "<class>" + class_original_string + "</class>\n" 
                        
            user_input = (
                "<target_method>" + original_string + "</target_method>\n" + 
                "The class is \n" + class_str +
                "<code_to_fix>" + code_to_fix + "</code_to_fix>" + "\n" + 
                "<error_message>" + '\n'.join(parsed_errors) + "</error_message>" + "\n" + junit_version_description
            )
            
            user_input += (
                "<fix_suggestion>" + fix_suggestion + "</fix_suggestion>\n"
            ) if fix_suggestion else ""

            logger.info(f"regenerate testcase user_input:\n{user_input}")
            full_response = self.call_llm(system_prompt=FIX_PROMPT_JAVA, user_input=user_input)
            raw_code = self.extract_code(full_response)
            
            logger.info(f"Regenerated testcase extract successfully! try to save to file...")
            generated_class_name, fixed_result = self.check_and_fix_testclass_name(
                raw_code=raw_code, new_class_name=new_class_name)
            if not fixed_result:
                logger.error(f'{method["name"]} fix testclass name failed in retry!')
                return
            
            result.append({
                "strategy": "retry",
                "code": fixed_result,
            })    
        except Exception as e:
            logger.exception(f'retry_generate_testcases failed: {e}')
            logger.error(traceback.format_exc())
        
        if save:
            # rename history file
            if os.path.exists(json_path):
                new_file_name = json_path.split('.json')[0] + '_' + str(int(time.time())) + '.json'
                os.rename(json_path, new_file_name)
            save_json(file_path=json_path, data=result)
            
            if os.path.exists(code_path):
                new_file_name = code_path.split('.java')[0] + '_' + str(int(time.time())) + '.java'
                os.rename(code_path, new_file_name)
            extract_code_from_json(json_file_path=json_path, output_file_path=code_path)
            logger.info(f'Save regenerated testcases to {json_path}')
            
        return generated_class_name
    
    def find_class(self, uri: str):
        _class = self.get_class(uri=uri)
        if _class:
            return _class

        _class = self.get_abstractclass(uri=uri)
        if _class:
            logger.warning(f'{uri} is abstract class, try to find its concrete class')
            return None

        interface = self.get_interface(uri=uri)
        if interface:
            logger.warning(f'{uri} is interface, try to find its concrete class')
            return None
    
    def find_testsuit(self, related_testcases):
        testsuits = []
        for testcase_uri in related_testcases:
            testcase_file_path, testclass_name, testcase_name = testcase_uri.split("#")
            for testclass_result in self.testclass_analysis_result:
                if (
                    testclass_result['file_path'] != testcase_file_path 
                    or testclass_name != testclass_result['testclass_name']
                    or (self._find_testcase(testcase_name, testclass_result) is None)
                ):
                    continue
                testsuits.append(testclass_result)
        return testsuits
    
    def find_and_minimize_testsuits():
        pass
    
    def find_related_testcases(self, where_to_find, method_names: Set[str], target_method_class_name: str):
        new_where_to_find = {}
        for k, v in where_to_find.items():
            new_k = k.replace(' ', '')
            new_where_to_find[new_k] = v

        where_to_find = new_where_to_find
        
        related_testcases = set()        
        for _method in method_names:
            is_external = _method.split('#')[-2] == 'True'
            outer_class_name = None
            if is_external:
                outer_class_name = _method.split('#')[-1]
            else:
                pass
            method_sig = _method.split('#')[0].replace(' ', '')
            if method_sig not in where_to_find:
                # logger.info(f'{method_sig} not found in method_to_primary_testcase!')
                continue
            
            method_info = where_to_find[method_sig]
            for testcase_info in method_info:
                if testcase_info['class_name'] == target_method_class_name:
                    # logger.info(f'{method_sig} found in method_to_primary_testcase!')
                    testcase_uri = testcase_info['file_path'] + '#' + testcase_info['testclass_name'] + '#' + testcase_info['testcase_name']
                    related_testcases.add(testcase_uri)
                elif testcase_info['class_name'] == outer_class_name:
                    testcase_uri = testcase_info['file_path'] + '#' + testcase_info['testclass_name'] + '#' + testcase_info['testcase_name']
                    logger.info(f"outer class {outer_class_name} found!")
                    related_testcases.add(testcase_uri)

        return related_testcases
    
    def find_testcase_in_method(self, where_to_find, method_sig: str, target_method_class_name: str):
        if method_sig not in where_to_find:
            return
                
        method_info = where_to_find[method_sig]
        for testcase_info in method_info:
            if testcase_info['class_name'] == target_method_class_name:
                logger.info(f'{method_sig} found in method_to_primary_testcase!')
                testcase_uri = testcase_info['file_path'] + '#' + testcase_info['testclass_name'] + '#' + testcase_info['testcase_name']
                return testcase_uri
    
    def process_related_testcases(self, related_testcases):
        """
        Process a list of related testcases to collect information about the testsuites they belong to.

        :param related_testcases: List of strings representing URIs of related testcases.
        """
        testsuits = []
        testsuit_used_info = {}
        testsuit_uri_set = set()

        for testcase_uri in related_testcases:
            testcase_file_path, testclass_name, testcase_name = testcase_uri.split("#")
            for testclass_result in self.testclass_analysis_result:
                if testclass_result['file_path'] != testcase_file_path or testclass_name != testclass_result['testclass_name']:
                    continue
                
                testsuit_uri = f"{testclass_result['file_path']}{testclass_result['testclass_name']}"
                if testsuit_uri in testsuit_uri_set:
                    continue
                
                testcase_info = self._find_testcase(testcase_name, testclass_result)
                if testcase_info is None:
                    logger.error(f'{testcase_uri} not found in {testclass_result}, it is surprising!')
                    continue
                
                if testsuit_uri not in testsuit_used_info:
                    testsuit_used_info[testsuit_uri] = {
                        "testsuit_used_variables": set(),
                        "testsuit_used_methods": set(),
                        "testsuit_used_nested_classes": set(),
                        "testsuit_used_fixtures": set(),
                        "testsuit_used_testcases": set()
                    }
                
                external_dependencies = testcase_info.get('external_dependencies', {})
                class_members = external_dependencies.get('class_members', [])
                variables = [item['name'] for item in class_members if item['type'] == 'variable']
                methods = [item['name'] for item in class_members if item['type'] == 'method']
                nested_classes = [item['name'] for item in class_members if item['type'] == 'class']
                
                testsuit_used_info[testsuit_uri]["testsuit_used_variables"].update(set(variables))
                testsuit_used_info[testsuit_uri]["testsuit_used_methods"].update(set(methods))
                testsuit_used_info[testsuit_uri]["testsuit_used_nested_classes"].update(set(nested_classes))
                testsuit_used_info[testsuit_uri]["testsuit_used_testcases"].add(testcase_name)
                
                fixtures_used_of_testcase = testcase_info.get('fixtures_used', [])
                testsuit_used_info[testsuit_uri]["testsuit_used_fixtures"].update(set(fixtures_used_of_testcase))
                
                logger.info(f'{testcase_uri} related testsuit: {testsuit_uri}')
                testsuit_uri_set.add(testsuit_uri)
                testsuits.append(testclass_result)
        
        return testsuits, testsuit_used_info
    
    def minimize_testsuit(self, testsuit, testsuit_used_info, testsuit_uri):
        """
        Minimize a testsuit by removing unused variables, methods, nested classes, fixtures, and test cases.

        :param testsuit: Dictionary representing the original testsuit.
        :param testsuit_used_info: Dictionary containing used information for the testsuit.
        :param testsuit_uri: URI of the testsuit.
        :return: Minimized testsuit dictionary.
        """
        minimized_testsuit = testsuit.copy()

        # Variables
        testsuit_used_variables = testsuit_used_info[testsuit_uri]["testsuit_used_variables"]
        variables = minimized_testsuit['class_members'].get('variables', [])
        new_variables = [var for var in variables if var['name'] in testsuit_used_variables]
        minimized_testsuit['class_members']['variables'] = new_variables

        # Methods
        testsuit_used_methods = testsuit_used_info[testsuit_uri]["testsuit_used_methods"]
        methods = minimized_testsuit['class_members'].get('methods', [])
        new_methods = [method for method in methods if method['name'] in testsuit_used_methods]
        minimized_testsuit['class_members']['methods'] = new_methods

        # Nested Classes
        testsuit_used_nested_classes = testsuit_used_info[testsuit_uri]["testsuit_used_nested_classes"]
        nested_classes = minimized_testsuit['class_members'].get('nested_classes', [])
        new_nested_classes = [nested_class for nested_class in nested_classes if nested_class['name'] in testsuit_used_nested_classes]
        minimized_testsuit['class_members']['nested_classes'] = new_nested_classes

        # Fixtures
        testsuit_used_fixtures = testsuit_used_info[testsuit_uri]["testsuit_used_fixtures"]
        fixtures = minimized_testsuit.get('fixtures', [])
        new_fixtures = [fixture for fixture in fixtures if fixture in testsuit_used_fixtures]
        minimized_testsuit['fixtures'] = new_fixtures

        # Test Cases
        testsuit_used_testcases = testsuit_used_info[testsuit_uri]["testsuit_used_testcases"]
        test_cases = minimized_testsuit.get('test_cases', [])
        new_test_cases = [testcase for testcase in test_cases if testcase['name'] in testsuit_used_testcases]
        minimized_testsuit['test_cases'] = new_test_cases

        return minimized_testsuit
    
    def update_final_testsuits(self, testsuits, testsuit_used_info, testsuits_set, all_testsuits):
        for testsuit in testsuits:
            if testsuit['name'] in testsuits_set:
                continue
            testsuits_set.add(testsuit['name'])
            testsuit_uri = f"{testsuit['file_path']}{testsuit['testclass_name']}"
            minimized_testsuit = self.minimize_testsuit(testsuit, testsuit_used_info, testsuit_uri)
            all_testsuits.append(minimized_testsuit)
            
    def enhance_with_brother_no_llm_info(self):
        return (
            f"We found the same method in a sibling class of the target method's class, which already has test cases." 
            "This can provide you with a reference."
        )
        
    def process_all_repo_level_relation(self, where_to_find, class_name, method_sig, target_method, testsuits_set, all_testsuits, 
                                        stage: str = "no_llm", testcase_category: str = ""):
        testcases_from_brother = self.find_related_testcase_in_brother(
            where_to_find=where_to_find, class_name=class_name, method_sig=method_sig)
        testcases_from_parent = self.find_related_testcase_in_parent(
            where_to_find=where_to_find, class_name=class_name, method_sig=method_sig)
        testcases_from_interface = self.find_related_testcase_in_interface(
            where_to_find=where_to_find, class_name=class_name, method_sig=method_sig)
        testcases_from_child = self.find_related_testcase_in_child(
            where_to_find=where_to_find, class_name=class_name, method_sig=method_sig)
        testcases_from_potential_brother = self.find_related_testcase_in_potential_brother(
            where_to_find=where_to_find, class_name=class_name, method_sig=method_sig)
        primary_testcases_from_brother = list(set(testcases_from_brother + testcases_from_parent + \
            testcases_from_interface + testcases_from_child + testcases_from_child + testcases_from_potential_brother))
        if primary_testcases_from_brother:
            logger.info(f'{stage} {testcase_category} Found {primary_testcases_from_brother} testcases for {class_name}.{target_method}')
            primary_testcases_from_brother_testsuits, testsuit_used_info = self.process_related_testcases(primary_testcases_from_brother)
            logger.info(f"{stage} {testcase_category} from_brother_testsuits: {len(primary_testcases_from_brother_testsuits)}")
            self.update_final_testsuits(testsuits=primary_testcases_from_brother_testsuits,
                                        testsuit_used_info=testsuit_used_info,
                                        testsuits_set=testsuits_set,
                                        all_testsuits=all_testsuits)
        
    def from_brother_without_llm_v2(self, class_name, target_method, testsuits_set, all_testsuits):
        testsuit_uris = set()
        if class_name in self.brother_enhancements:
            for item in self.brother_enhancements[class_name]:
                for testcase in item['methods']:
                    if testcase['method_name'].strip('()') == target_method:
                        logger.info(f"Find brother enhancement for {target_method} in {class_name}")
                        testsuit_uris.add(f"{testcase['file_path']}#{testcase['testclass_name']}#{testcase['testcase_name']}")
             
        if class_name in self.parent_enhancements:
            for item in self.parent_enhancements[class_name]:
                for testcase in item['methods']:
                    if testcase['method_name'].strip('()') == target_method:
                        logger.info(f"Find parent enhancement for {target_method} in {class_name}")
                        testsuit_uris.add(f"{testcase['file_path']}#{testcase['testclass_name']}#{testcase['testcase_name']}")
  
        if class_name in self.child_enhancements:
            for item in self.child_enhancements[class_name]:
                for testcase in item['methods']:
                    if testcase['method_name'].strip('()') == target_method:
                        logger.info(f"Find child enhancement for {target_method} in {class_name}")
                        testsuit_uris.add(f"{testcase['file_path']}#{testcase['testclass_name']}#{testcase['testcase_name']}")

        if class_name in self.interface_brother_enhanements:
            for item in self.interface_brother_enhanements[class_name]:
                for testcase in item['methods']:
                    if testcase['method_name'].strip('()') == target_method:
                        logger.info(f"Find interface brother enhancement for {target_method} in {class_name}")
                        testsuit_uris.add(f"{testcase['file_path']}#{testcase['testclass_name']}#{testcase['testcase_name']}")

        primary_testcases_from_brother_testsuits, testsuit_used_info = self.process_related_testcases(testsuit_uris)
        logger.info(f"from_brother_testsuits: {len(primary_testcases_from_brother_testsuits)}")
        self.update_final_testsuits(testsuits=primary_testcases_from_brother_testsuits,
                                    testsuit_used_info=testsuit_used_info,
                                    testsuits_set=testsuits_set,
                                    all_testsuits=all_testsuits)

    def from_brother_without_llm(self, class_name, method_sig, target_method, testsuits_set, all_testsuits, 
                                 stage: str = "no_llm"):
        self.process_all_repo_level_relation(
            where_to_find=self.class_to_primary_testcase, class_name=class_name, 
            method_sig=method_sig, target_method=target_method, testsuits_set=testsuits_set,
            all_testsuits=all_testsuits, stage=stage, testcase_category="primary",
        )
        self.process_all_repo_level_relation(
            where_to_find=self.class_to_relevant_testcase, class_name=class_name, 
            method_sig=method_sig, target_method=target_method, testsuits_set=testsuits_set,
            all_testsuits=all_testsuits, stage=stage, testcase_category="relevant",
        )

    def refined_generate_testcases(self, method: Dict, save: bool = True):
        results = []
        try_generated = None
        system_prompt = CLASS_LEVEL_PROMPT_JAVA
        try:
            _class = self.find_class(uri=method['class_uri'])
            if not _class:
                logger.error(f'No class found for {method["name"]}')
                return try_generated, None
            target_method = method['name']
            file_path = method['file']
            class_name = _class['name']
            method_sig = method['uris'].split(']')[-1]
            logger.info(f'Generating testcase for {class_name}.{target_method}')
            
            testsuits_set = set()
            all_testsuits = []
            from_brother_enhanced_description = ""
            
            self.from_brother_without_llm_v2(class_name=class_name, target_method=target_method, testsuits_set=testsuits_set, all_testsuits=all_testsuits)   
             
            if testsuits_set:
                from_brother_enhanced_description = self.enhance_with_brother_no_llm_info()
                enhance_with_brother_no_llm_description = '\n\nHere is the description of the test cases for the methods mentioned in the enhanced information above:\n' + \
                    self.convert_to_natural_language_v2(all_testsuits)
                from_brother_enhanced_description += enhance_with_brother_no_llm_description

            related_info = self.property_analyzer.get_related_method(_class=_class, target_method=target_method)
            
            direct_method_names = set()
            direct_enhancements = related_info.get('direct_enhancements', [])
            for item in direct_enhancements:
                if item.get('is_external', False) is False:
                    direct_method_names.add(item['method_name'] + "#" + str(item['is_external']) + "#" + "This")
                else:
                    direct_method_names.add(item['method_name'] + "#" + str(item['is_external']) + "#" + item.get('class_name', 'None'))

            gwt_method_names = set()
            gwt_enhancements = related_info.get('gwt_enhancements', {})
            if not direct_method_names and not gwt_enhancements:
                return try_generated, None

            self._refined_add_enhanced_methods(gwt_method_names, gwt_enhancements, 'Given')
            self._refined_add_enhanced_methods(gwt_method_names, gwt_enhancements, 'When')
            self._refined_add_enhanced_methods(gwt_method_names, gwt_enhancements, 'Then')

            for item in direct_enhancements:
                self.from_brother_without_llm_v2(class_name=class_name, target_method=item['method_name'], 
                                                 testsuits_set=testsuits_set, all_testsuits=all_testsuits)   

            related_testcases = self.find_related_testcases(
                where_to_find=self.method_to_primary_testcase, method_names=direct_method_names, target_method_class_name=class_name) 
            direct_enhancements_primary_testsuits, testsuit_used_info = self.process_related_testcases(related_testcases)
            
            self.update_final_testsuits(testsuits=direct_enhancements_primary_testsuits,
                                        testsuit_used_info=testsuit_used_info,
                                        testsuits_set=testsuits_set,
                                        all_testsuits=all_testsuits)

            relevant_testcases = self.find_related_testcases(
                where_to_find=self.method_to_relevant_testcase, method_names=direct_method_names, target_method_class_name=class_name)
            direct_enhancements_relevant_testsuits = []
            if relevant_testcases:
                direct_enhancements_relevant_testsuits, testsuit_used_info = self.process_related_testcases(relevant_testcases)
            
            self.update_final_testsuits(testsuits=direct_enhancements_relevant_testsuits,
                                        testsuit_used_info=testsuit_used_info,
                                        testsuits_set=testsuits_set,
                                        all_testsuits=all_testsuits)

            related_testcases = self.find_related_testcases(
                where_to_find=self.method_to_primary_testcase, method_names=gwt_method_names, target_method_class_name=class_name)
            gwt_enhancements_primary_testsuits, testsuit_used_info = self.process_related_testcases(related_testcases)
            
            self.update_final_testsuits(testsuits=gwt_enhancements_primary_testsuits,
                                        testsuit_used_info=testsuit_used_info,
                                        testsuits_set=testsuits_set,
                                        all_testsuits=all_testsuits)
            
            relevant_testcases = self.find_related_testcases(
                where_to_find=self.method_to_relevant_testcase, method_names=gwt_method_names, target_method_class_name=class_name)
            gwt_enhancements_relevant_testsuits = []
            if relevant_testcases:
                gwt_enhancements_relevant_testsuits, testsuit_used_info = self.process_related_testcases(relevant_testcases)
            
            self.update_final_testsuits(testsuits=gwt_enhancements_relevant_testsuits,
                                        testsuit_used_info=testsuit_used_info,
                                        testsuits_set=testsuits_set,
                                        all_testsuits=all_testsuits)
            
            for item in gwt_method_names:
                gwt_method_name = item.split('#')[0]
                self.from_brother_without_llm(
                    class_name=class_name, method_sig=method_sig, 
                    target_method=gwt_method_name, testsuits_set=testsuits_set, 
                    all_testsuits=all_testsuits, stage="gwt_enhancements")

            if all_testsuits:
                testsuits_description = '\n\nHere is the description of the test cases for the methods mentioned in the enhanced information:\n' + \
                    self.convert_to_natural_language_v2(all_testsuits)
            else:
                testsuits_description = ''
                logger.warning(f'{method["name"]} related testcases is empty!')
                system_prompt = FALLBACK_PROMPT_JAVA
            
            original_string = 'Here is the raw code of target method:\n' + method['original_string']
            enhanced_description = '\n\nHere is the context that may enhence for testcase generation of target method:\n' + \
                self.enhance_with_related_info(related_info)
                
            imports = self.get_imports(file_path=method['file'])
            imports_str = '\n'.join(imports)

            extract_method_names = self.extract_method_names(related_info=related_info)
            static_context = '\n\nHere is the pruned class context of target method:\n' + imports_str
            pruned_class = self.static_context_retrieval.prune_class(_class=_class, method_names=extract_method_names)
            pruned_class_description = self.static_context_retrieval.pack_pruned_class_description(pruned_class)
            static_context += pruned_class_description
            self.pruned_class_description = pruned_class_description
            
            unresolved_refs = self.static_context_retrieval.find_unresolved_refs(method['original_string'])
            unresolved_refs = set(unresolved_refs) - set(class_name)
            class_field_map = {field['name'].split('=')[0].strip() if '=' in field['name'] else field['name']: field['type'] for field in _class['fields']}
            for unresolved_ref in list(unresolved_refs):
                if unresolved_ref in class_field_map:
                    unresolved_refs.remove(unresolved_ref)
                    unresolved_refs.add(class_field_map[unresolved_ref])

            unresolved_refs = unresolved_refs - set(self.static_context_retrieval.keywords_and_builtin)
            
            package_refs = self.static_context_retrieval.pack_package_info_use_dot(unresolved_refs, file_path, method['original_string'])
            package_refs_description = self.pack_package_refs_description(package_refs)
            if package_refs_description:
                logger.info(f'{method["name"]} added package_refs_description.')
                static_context += package_refs_description

            static_context += package_refs_description
            unresolved_refs = unresolved_refs - set([ref['name'] for ref in package_refs])
            
            repo_refs_use_dot = self.static_context_retrieval.pack_repo_info_use_dot(unresolved_refs, original_string=method['original_string'], imports=imports)
            repo_refs_use_dot_description = self.pack_repo_refs_use_dot_description(repo_refs_use_dot)
            unresolved_refs = unresolved_refs - set([ref['name'] for ref in repo_refs_use_dot])
            if repo_refs_use_dot_description:
                logger.info(f'{method["name"]} added repo_refs_use_dot_description.')
                static_context += repo_refs_use_dot_description

            montage_descriptions, resolved_refs = self.static_context_retrieval.pack_repo_info(unresolved_refs, imports=imports)
            if montage_descriptions:
                logger.info(f'add montages_description.')
                static_context += '\n'.join(montage_descriptions)
                unresolved_refs = unresolved_refs - set(resolved_refs)

            package_class_montages = self.static_context_retrieval.pack_package_info(unresolved_refs, file_path, original_string=method['original_string'])
            package_class_montages_description = self.pack_package_class_montages_description(package_class_montages)
            if package_class_montages_description:
                logger.info(f'{method["name"]} added package_class_montages_description.')
                static_context += package_class_montages_description

            junit_version_description = "\nThe junit version is " + str(self.junit_version) + "\n"
            user_input = '\n' + original_string + static_context + from_brother_enhanced_description + \
                enhanced_description + testsuits_description + junit_version_description
            try_generated = method['uris']
            return try_generated, self.cass_llm_to_generate(system_prompt, user_input, method, results, class_name, target_method, save=True)
        except Exception as e:
            logger.error(f'Error in refined_generate_testcases: {e}')
            logger.error(traceback.format_exc())
            return try_generated, None

    @staticmethod
    def get_new_class_name(class_name: str, target_method: str):
        class_name_camel = class_name[0].upper() + class_name[1:]
        new_class_name = class_name_camel + '_' + target_method + 'Test'
        return new_class_name

    def cass_llm_to_generate(self, system_prompt, user_input: str, method: Dict, results: Dict, 
                             class_name: str, target_method: str, save: bool, _dir: bool = GENERATED_TESTCASES_DIR):
        new_class_name = JavaTestcaseGenerator.get_new_class_name(class_name=class_name, target_method=target_method)
        json_file_path = _dir + new_class_name + '.json'
        java_file_path = json_file_path.split('.json')[0] + '.java'
        try:
            full_response = self.call_llm(system_prompt=system_prompt, user_input=user_input)
            raw_code = self.extract_code(full_response)
            logger.info(f"Generated testcase extract successfully! try to save to file...")
            
            generated_class_name, fixed_result = self.check_and_fix_testclass_name(raw_code=raw_code, new_class_name=new_class_name)
            if not fixed_result:
                logger.error(f'{method["name"]} fix testclass name failed!')
                return

            results.append({
                "strategy": "generate",
                "code": fixed_result,
            })
            logger.info(f'{method["name"]} generate testcase success!')
        except Exception as e:
            logger.exception(f'{method["name"]} generate testcase failed: {e}')
            return None

        if save:
            save_json(file_path=json_file_path, data=results)
            extract_code_from_json(json_file_path=json_file_path, output_file_path=java_file_path)
            logger.info(f'Save testcases to {json_file_path}')
            
        logger.info('Generate testcases done!')
        return generated_class_name

    def check_and_fix_testclass_name(self, raw_code: str, new_class_name: str) -> Tuple[str, str]:
        class_decl_pattern = re.compile(r'\bclass\s+(\w+)', re.MULTILINE)
        match = class_decl_pattern.search(raw_code)
        if not match:
            return '', ''

        class_name_in_code = match.group(1)
        fixed_code = re.sub(r'\bclass\s+' + re.escape(class_name_in_code), f'class {new_class_name}', raw_code)
        return new_class_name, fixed_code
        
    def convert_testsuits_to_context_mapping_v2(self, testsuits: List[Dict[str, Any]]):
        testfile_to_testclass_analysis_result = {}

        file_path_to_testcase_set_dict = {}
        file_path_to_fixture_set_dict = {}
        for testsuit in testsuits:
            file_path = testsuit['file_path']
            if file_path not in testfile_to_testclass_analysis_result:
                file_path_to_testcase_set_dict[file_path] = set()
                file_path_to_fixture_set_dict[file_path] = set()
                testfile_to_testclass_analysis_result[file_path] = {
                    "class_level_variables": set(),
                    "dependencies": [],
                    "fixtures": [],
                    "testcases": [],
                }
            
            if not testfile_to_testclass_analysis_result[file_path]['dependencies']:
                imports = self.get_imports(file_path=file_path)
                testfile_to_testclass_analysis_result[file_path]['dependencies'].extend(imports)

            class_level_variables = testsuit.get('class_level_variables', [])
            testfile_to_testclass_analysis_result[file_path]['class_level_variables'].update(class_level_variables)
            
            testclass_name = testsuit['testclass_name']

            for fixture_name in testsuit.get('fixtures', []):
                fuzzy_uri = file_path + testclass_name + fixture_name
                logger.info(f"fixture fuzzy_uri: {fuzzy_uri}")
                if fuzzy_uri in file_path_to_fixture_set_dict[file_path]:
                    logger.info(f"fixture {fixture_name} already exists in {file_path}")
                    continue

                file_path_to_fixture_set_dict[file_path].add(fuzzy_uri)
                original_fixture = self.fuzzy_get_method(file_path=file_path, class_name=testclass_name, method_name=fixture_name)
                if original_fixture is None:
                    logger.error(f"original_fixture is None for {fixture_name}")
                    continue
                
                original_string = original_fixture['original_string']
                testfile_to_testclass_analysis_result[file_path]['fixtures'].append(
                    {
                        'name': fixture_name,
                        'original_string': original_string,
                    }
                )
                
            testcases = testsuit.get('test_cases', [])
            for testcase in testcases:
                fuzzy_uri = file_path + testclass_name + testcase['name']
                logger.info(f"fuzzy_uri: {fuzzy_uri}")
                if fuzzy_uri in file_path_to_testcase_set_dict[file_path]:
                    logger.info(f"{testcase['name']} already exists in testcases")
                    continue

                file_path_to_testcase_set_dict[file_path].add(fuzzy_uri)
                original_testcase = self.fuzzy_get_testcase(file_path=file_path, class_name=testclass_name, testcase_name=testcase['name'])
                if original_testcase is None:
                    logger.error(f"original_testcase is None for {testcase['name']}")
                    continue
                original_string = original_testcase['original_string']
                external_dependencies = testcase.get('external_dependencies', {})
                fixtures_used = testcase.get('fixtures_used', [])
                testfile_to_testclass_analysis_result[file_path]['testcases'].append(
                    {
                        'name': testcase['name'],
                        'original_string': original_string,
                        'tested': testcase.get('tested', []),
                        'external_dependencies': external_dependencies,
                        'fixtures_used':  fixtures_used,
                        'description': testcase.get('description', "")
                    }
                )

            logger.info(f"testfile_to_testclass_analysis_result of testsuit: {testsuit['name']} extract finished")
        return testfile_to_testclass_analysis_result

    def convert_to_natural_language_v2(self, testsuits: Dict) -> str:
        """
        Convert the structured test case mapping into natural language descriptions with tagged sections.
        """
        descriptions = []
        for context in testsuits:
            file_path = context['file_path']
            descriptions.append(f"[File]: {file_path}")
            descriptions.append(f"[Name]: {context['testclass_name']}")

            # Tag for class_members variables
            class_members = context.get('class_members', {})
            ## variables
            variables = class_members.get('variables', [])
            if variables:
                descriptions.append(f"[Variables]:")
                for variable in variables:
                    descriptions.append(f"name: {variable['name']}, type: {variable['type']}")
            
            ## methods
            methods = class_members.get('methods', [])
            if methods:
                descriptions.append(f"[Methods]:")
                for method in methods:
                    # TODO: add method definition, now we dont have any case
                    descriptions.append(f"name: {method['name']}, signature: {method['signature']}")
            
            ## nested classes
            nested_classes = class_members.get('nested_classes', [])
            if nested_classes:
                descriptions.append(f"[Nested Classes]:")
                for nested_class in nested_classes:
                    descriptions.append(f"name: {nested_class['name']}, description: {nested_class['description']}")

            # Tag for dependencies
            if 'dependencies' in context:
                descriptions.append(f"[Dependencies]:\n" + "\n".join(context['dependencies']))
   
            for fixture in context['fixtures']:
                # get original string for fixture
                fixture_method = self.fuzzy_get_method(file_path=file_path, class_name=context['testclass_name'], method_name=fixture)
                if fixture_method is None:
                    logger.error(f"fixture_method is None for {fixture}")
                    continue
                descriptions.append(f"[Fixtures]:\n{fixture_method['original_string']}")
            
            for testcase in context['test_cases']:
                original_testcase = self.fuzzy_get_testcase(file_path=file_path, class_name=context['testclass_name'], testcase_name=testcase['name'])
                if original_testcase is None:
                    logger.error(f"original testcase is None for {testcase['name']}")
                    continue
                original_string = original_testcase['original_string']
                descriptions.append(f"[Test Case]:\n{original_string}")
                if testcase.get('fixtures_used'):
                    descriptions.append(f"and this testcase used fixture: {', '.join(testcase['fixtures_used'])}")

        return "\n".join(descriptions)
      
    def extract_method_names(self, related_info: Dict) -> List[str]:
        """
        Extract all method names from `related_info` and return them in a list.
        """
        method_names = []

        # Extract method names from direct enhancements
        if 'direct_enhancements' in related_info:
            for enhancement in related_info['direct_enhancements']:
                method_names.append(enhancement['method_name'])

        # Extract method names from GWT enhancements
        gwt_enhancements = related_info.get('gwt_enhancements', {})
        for enhancements in gwt_enhancements.values():
            for enhancement in enhancements.get('enhanced_by', []):
                method_names.append(enhancement['method_name'])

        return method_names

    def enhance_with_related_info(self, related_info: Dict) -> str:
        """
        Enhance the natural language description with related method information from `related_info` with tagged sections.
        """
        enhanced_description = []
        
        # Tag for direct enhancements
        if 'direct_enhancements' in related_info:
            enhanced_description.append("[Direct Enhancements]:")
            for enhancement in related_info['direct_enhancements']:
                enhanced_description.append(
                    f"Method '{enhancement['method_name']}' is related by '{enhancement['relation_type']}' "
                    f"(confidence: {enhancement['confidence']}): {enhancement['reason']}"
                )
        
        # Tag for GWT enhancements
        gwt_enhancements = related_info.get('gwt_enhancements', {})
        
        for gwt_phase, enhancements in gwt_enhancements.items():
            enhanced_description.append(f"[{gwt_phase} Enhancements]:")
            for enhancement in enhancements['enhanced_by']:
                enhanced_description.append(
                    f"Method '{enhancement['method_name']}' can enhance '{gwt_phase}' phase "
                    f"(relation: {enhancement['relation_type']}, confidence: {enhancement['confidence']}): "
                    f"{enhancement['reason']}"
                )
        
        return "\n".join(enhanced_description)
    
    def find_related_testcase_in_brother(self, where_to_find, class_name: str, method_sig: str):
        res = []
        brothers = self.inherit_resolver.get_brothers(class_name)
        for brother in brothers:
            if method_sig in where_to_find:
                testcase_uri = self.find_testcase_in_method(where_to_find, method_sig, brother['name'])
                if testcase_uri is None:
                    continue
                res.append(testcase_uri)
        return res
        
    def find_related_testcase_in_parent(self, where_to_find, class_name: str, method_sig: str):
        res = []
        parent = self.inherit_resolver.get_parent(class_name)
        if method_sig in where_to_find:
            testcase_uri = self.find_testcase_in_method(where_to_find, method_sig, parent['name'])
            if testcase_uri is None:
                return res
            res.append(testcase_uri)
        return res
    
    def find_related_testcase_in_child(self, where_to_find, class_name: str, method_sig: str):
        res = []
        for child in self.inherit_resolver.get_children(class_name):
            testcase_uri = self.find_testcase_in_method(where_to_find, method_sig, child)
            if testcase_uri is None:
                continue
            res.append(testcase_uri)
        return res
    
    def find_related_testcase_in_potential_brother(self, where_to_find, class_name: str, method_sig: str):
        res = []
        if not self.potential_brother_relations:
            return res
        potential_brothers = self.potential_brother_relations.get(class_name)
        if potential_brothers is None:
            return res

        for brother in potential_brothers:
            testcase_uri = self.find_testcase_in_method(where_to_find, method_sig, brother['name'])
            if testcase_uri is None:
                continue
            res.append(testcase_uri)
        return res

    def find_related_testcase_in_interface(self, where_to_find, class_name: str, method_sig: str):
        res = []
        brothers = []
        for relation in self.interface_brother_relations:
            methods = relation['methods']
            if method_sig not in methods:
                continue
            implementations = relation['implementations']
            for implementation in implementations:
                cls_name = implementation.split('.java.')[-1]
                if class_name != cls_name:
                    continue
                logger.info(f"Find interface brother: {cls_name} for {class_name} with {method_sig}")
                brothers.append(cls_name)
                        
        for brother in brothers:
            if method_sig in where_to_find:
                testcase_uri = self.find_testcase_in_method(where_to_find, method_sig, brother['name'])
                if testcase_uri is None:
                    continue
                res.append(testcase_uri)
        return res
    

    def repo_level_relation_retrieval(self, where_to_find, class_uri: str, method_uri: str):
        class_name = class_uri.split('.java.')[-1]
        related_testcases = {
            "brothers": [],
            "parents": [],
            "children": [],
            "interface": []
        }
        parent, brothers = self.inherit_resolver.get_brothers_and_parent(class_name)
        parent_methods = parent["methods"]
        for brother in brothers:
            for parent_method in parent_methods:
                method_sig = parent_method.split(']')[-1]
                if method_sig in where_to_find:
                    testcase_uri = self.find_testcase_in_method(where_to_find, method_sig, brother['name'])
                    if testcase_uri is None:
                        continue
                    related_testcases["brothers"].append(testcase_uri)

        parent = self.inherit_resolver.get_parent(class_name)
        parent_info = self.fuzzy_get_class(parent)
        parent_methods = parent_info["methods"]
        for parent_method in parent_methods:
            method_sig = parent_method.split(']')[-1]
            if method_sig in where_to_find:
                testcase_uri = self.find_testcase_in_method(where_to_find, method_sig, parent_info['name'])
                if testcase_uri is None:
                    continue
                related_testcases["parents"].append(testcase_uri)
                logger.info(f"Find testcase in parent: {testcase_uri}")

        for relation in self.interface_brother_relations:
            implementations = relation['implementations']
            for implementation in implementations:
                cls_name = implementation.split('.java.')[-1]
                if class_name != cls_name:
                    continue

                logger.info(f"Find interface brother: {cls_name} for {class_name}")
                for interface_method in relation['methods']:
                    method_sig = interface_method.split(']')[-1]
                    if method_sig in where_to_find:
                        testcase_uri = self.find_testcase_in_method(where_to_find, method_sig, cls_name)
                        if testcase_uri is None:
                            continue
                        related_testcases["interface"].append(testcase_uri)
        
        return related_testcases


def run_testcase_generator(testcase_generator: TestcaseGenerator, method):
    testcase_generator.generate_testcases(method)

    