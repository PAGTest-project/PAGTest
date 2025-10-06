import concurrent.futures

from repo_parse.llm.deepseek_llm import DeepSeekLLM
from repo_parse.llm.llm import LLM
from repo_parse.llm.qwen_llm import QwenLLM
from repo_parse.analysis.analyzer import Analyzer
from repo_parse.config import CLASS_PROPERTY_DIR, FILE_PATHS_WITH_TWO_DOTS, HISTORY_TESTCASE_PATHS_PATH, PACKAGE_PREFIX, RESOLVED_METAINFO_PATH, TESTCASE_ANALYSIS_RESULT_PATH, TESTCLASS_ANALYSIS_RESULT_DIR, TESTCLASS_ANALYSIS_RESULT_PATH, TESTFILE_METAINFO_PATH
from repo_parse.context_retrieval.static_retrieval.java_static_context_retrieval import JavaStaticContextRetrieval
from repo_parse.context_retrieval.static_retrieval.static_context_retrieval import StaticContextRetrieval
from repo_parse.property_graph.node_coordinator import JavaNodeCoordinator
from repo_parse.utils.data_processor import load_json, save_json
from repo_parse.prompt.testcase_analyzer import Prompt_Java, Prompt_Python, Prompt_TestSuit_Java, Prompt_TestSuit_Python
from repo_parse import logger


class TestCaseAnalyzer(Analyzer):
    def __init__(self, 
                 llm: LLM = None,
                 static_context_retrieval: StaticContextRetrieval = None,
                 system_prompt: str = None
                 ):
        Analyzer.__init__(self, llm=llm)
        self.llm = llm
        self.static_context_retrieval = static_context_retrieval
        # self.testfile_metainfo = load_json(TESTFILE_METAINFO_PATH)
        self.testcase_analysis_result_path = TESTCASE_ANALYSIS_RESULT_PATH
        self.testclass_analysis_result_path = TESTCLASS_ANALYSIS_RESULT_PATH
        self.all_classes = ', '.join(self.static_context_retrieval.class_map.keys())
        self.system_prompt = system_prompt

    def call_llm(self, system_prompt, user_input) -> str:
        full_response = self.llm.chat(system_prompt, user_input)
        return full_response
    
    def batch_high_level_analyze(self):
        raise NotImplementedError
    
    def high_level_analyze(self):
        """
        If one test function is in a test class, we analyze the test class;

        # The following cases is for Python and Go.
        else if one test function is in a file, we analyze the file;
            if file is too long,  
        """
        raise NotImplementedError

    def excute(self):
        results = []
        for testcase in self.testcase_metainfo:
            try:
                testcase_name = testcase['name']
                file = testcase['file']
                
                user_input = f'Here are all class: {self.all_classes}.\n' + 'This is the testcase: \n'
                original_string = self.static_context_retrieval.pack_testcase_file_level_context(testcase=testcase)
                
                # TODO: 添加import中的引用

                full_response = self.call_llm(system_prompt=self.system_prompt, user_input=user_input+original_string)
                resp_dict = self.extract(full_response)
                
                testcase_analyze_info = {
                    'file': file,
                }
                results.append({**testcase_analyze_info, **resp_dict})
                
                logger.info(f"Analyze testcase {testcase_name} finished")
            except Exception as e:
                logger.exception(f'Analyze testcase {testcase_name} failed: {e}')

        save_json(file_path=self.testcase_analysis_result_path, data=results)



class PythonTestcaseAnalyzer(TestCaseAnalyzer):
    def __init__(self, 
                 llm: LLM = None,
                 ):
        static_context_retrieval = PythonStaticContextRetrieval()
        TestCaseAnalyzer.__init__(self, llm=llm, static_context_retrieval=static_context_retrieval,
                                  system_prompt=Prompt_Python)
        
    def high_level_analyze(self):
        """
        If one test function is in a test class, we analyze the test class;

        # The following cases is for Python and Go.
        else if one test function is in a file, we analyze the file;
            if file is too long,  
        """
        results = []
        # for testclass in self.testclass_metainfo:
        #     name = testclass['name']
        #     try:
        #         file_path = testclass['file_path']
        #         imports = self.get_imports(file_path=file_path)
        #         imports_str = '\n'.join(imports)
        #         original_string = testclass['original_string']
                
        #         full_response = self.call_llm(system_prompt=Prompt_TestSuit_Python, user_input=imports_str + original_string)
        #         resp_dict = self.extract(full_response)
        #         testclass_info = {
        #             'file_path': file_path,
        #             'testclass_name': name,
        #         }
        #         results.append({**testclass_info, **resp_dict})
                
        #         logger.info(f"Analyze testclass {name} finished")
        #     except Exception as e:
        #         logger.exception(f'Analyze testclass {name} failed: {e}')
                
        for testfile in self.testfile_metainfo:
            name = testfile['name']
            try:
                file_path = testfile['file_path']
                # imports = self.get_imports(file_path=file_path)
                # imports_str = '\n'.join(imports)
                original_string = testfile['original_string']
                user_input = "The file path is:" + file_path + "\n" + "The souce code is:\n"
                
                full_response = self.call_llm(system_prompt=Prompt_TestSuit_Python, user_input=user_input+original_string)
                resp_dict = self.extract(full_response)
                testfile_info = {
                    'file_path': file_path,
                }
                results.append({**testfile_info, **resp_dict})
                
                logger.info(f"Analyze testclass {name} finished")
            except Exception as e:
                logger.exception(f'Analyze testclass {name} failed: {e}')

        save_json(file_path=self.testclass_analysis_result_path, data=results)


    # NOTE: This method is not used for now.
    # def get_unresolved_refs(self):
    #     testcase_unresolved_refs = []
    #     testcase_pure_unresolved_refs = []
    #     for testcase_info in self.testcase_metainfo:
    #         testcase_name = testcase_info['name']
    #         file = testcase_info['file']
    #         # file_imports = self.analayze_imports(file)
    #         original_string = testcase_info['original_string']
    #         scope_graph = build_scope_graph(bytearray(original_string, encoding="utf-8"), language="python")
    #         unresolved_ref = scope_graph.unresolved_refs_name()
    #         testcase_unresolved_refs.append({
    #             'name': testcase_name,
    #             'file': file,
    #             'unresolved_refs': unresolved_ref
    #         })
    #         pure_unresolved_refs = self.filter_refs(unresolved_ref)
    #         testcase_pure_unresolved_refs.append({
    #             'name': testcase_name,
    #             'file': file,
    #             'unresolved_refs': list(set(pure_unresolved_refs))
    #         })
            
            
    #     print("Analyze finished")
    #     save_json(self.testcase_unresolved_refs_path, testcase_unresolved_refs)
    #     save_json(self.testcase_pure_unresolved_refs_path, testcase_pure_unresolved_refs)


class JavaTestcaseAnalyzer(TestCaseAnalyzer):
    def __init__(self, 
                 llm: LLM = None,
                 ):
        static_context_retrieval = JavaStaticContextRetrieval()
        TestCaseAnalyzer.__init__(self, llm=llm, 
                                  static_context_retrieval=static_context_retrieval,
                                  system_prompt=Prompt_Java)
        
    def reslove_history_testcase_paths(self, testclass, update=False):
        file_paths = [_class['file_path'] for _class in testclass]
        res = {'history_testcase_paths': file_paths}
        if update:
            history_testcase_paths = load_json(HISTORY_TESTCASE_PATHS_PATH)
            res['history_testcase_paths'].extend(history_testcase_paths['history_testcase_paths'])
        save_json(HISTORY_TESTCASE_PATHS_PATH, res)
        logger.info(f"History testcase paths saved")
        
    def pack_testclass_montage_description(self, class_montage):
        """
        {
            "class_name": _class['name'],
            "methods_signature": _class['methods'],
            "fields": [field['attribute_expression'] for field in _class['fields']]
        }
        """
        return (
            "" +
            self.pack_class_montage_description(class_montage)
        )
        
    def analyze_testclass(self, testclass):
        name = testclass['name']
        try:
            file_path = testclass['uris'].split('.java')[0] + '.java'
            imports = self.get_imports(file_path=file_path)
            imports_str = '\n'.join(imports)
            original_string = testclass['original_string']
            
            # 这里给错了啊！！。。。
            
            user_input = self.pack_static_context(testclass=testclass, original_string=original_string, 
                                                  imports=imports, file_path=file_path)

            full_response = self.call_llm(system_prompt=Prompt_TestSuit_Java, user_input=user_input)
            resp_dict = self.extract(full_response)
            testclass_info = {
                'file_path': file_path,
                'testclass_name': name,
                'dependencies': imports
            }
            return {**testclass_info, **resp_dict}
        except Exception as e:
            return {'testclass_uris': testclass["uris"], 'error': str(e)}

    def batch_high_level_analyze(self, filter_list=[]):
        results = []
        fails = []
        with concurrent.futures.ThreadPoolExecutor(max_workers=50) as executor:
            future_to_testclass = {}
            for testclass in self.testclass_metainfo:
                if filter_list and testclass['file_path'] not in filter_list:
                    continue
                future = executor.submit(self.analyze_testclass, testclass)
                future_to_testclass[future] = testclass
            for future in concurrent.futures.as_completed(future_to_testclass):
                testclass = future_to_testclass[future]
                try:
                    res = future.result()
                    if 'error' not in res:
                        results.append(res)
                        file_name = testclass['uris'].replace('/', '_') + '.json'
                        save_json(file_path=TESTCLASS_ANALYSIS_RESULT_DIR + file_name, data=res)
                        logger.info(f"Analyze testclass {testclass['name']} finished")
                    else:
                        fails.append(res)
                except Exception as e:
                    fails.append({'testclass_uris': testclass["uris"], 'error': str(e.stdout)})
                    logger.exception(f'Analyze testclass {testclass["name"]} failed: {e.stdout}')

        save_json(file_path=self.testclass_analysis_result_path, data=results)
        save_json(file_path=TESTCLASS_ANALYSIS_RESULT_DIR + 'failures.json', data=fails)

    def pack_static_context(self, testclass, original_string, imports, file_path):
        montage_description = ""
        resolved_ref = set()
        for _import in imports:
            if PACKAGE_PREFIX in _import:
                tokens = _import.rstrip(';').split(' ')[-1].split('.')
                class_name = tokens[-1]
                package_name = '.'.join(tokens[:-1])
                _class = self.get_class_or_none(class_name, package_name)
                # 这里暂时先只需要引入类的就行了
                if _class is not None:
                    resolved_ref.add (_class['name'])
                    class_montage = self.get_class_montage(_class)
                    montage_description += self.pack_testclass_montage_description(class_montage)
                    continue
                
                # interface = self.get_interface_or_none(class_name, package_name)
                # if interface is not None:
                #     resolved_ref.add(interface['name'])
                #     interface_montage = self.get_interface_montage(interface)
                #     montage_description += self.pack_interface_montage_description(interface_montage)
                #     continue
                
                # abstract_class = self.get_abstractclass_or_none(class_name, package_name)
                # if abstract_class is not None:
                #     resolved_ref.add(abstract_class['name'])
                #     abstract_class_montage = self.get_abstractclass_montage(abstract_class)
                #     montage_description += self.pack_abstractclass_montage_description(abstract_class_montage)

        montage_description = "\nAnd We provide you with the montage information of the imports to help you better identify." + montage_description \
            if montage_description else ""
        
        # # 1. 首先需要引入被测类的montage啊，让他直到有哪些方法。
        # class_montage = self.get_class_montage(testclass)
        # testclass_montage_description = self.pack_testclass_montage_description(class_montage)
        
        # 同一个package中的引用信息提供一下
        # TODO: 这里也需要进行判断的，如果是Class.XXXX，那就只需要引入XXXX就行了。
        unresolved_refs = self.static_context_retrieval.find_unresolved_refs(original_string)
        
        unresolved_refs = set(unresolved_refs) - resolved_ref - set(self.static_context_retrieval.keywords_and_builtin)
        
        package_class_montages = self.static_context_retrieval.pack_package_info(list(unresolved_refs), file_path, original_string)
        package_class_montages_description = self.pack_package_class_montages_description(package_class_montages)
        
        # import过来的类，montage信息要不要全部提供？太多了。暂时先不管了。
        
        imports_str = '\n'.join(imports)
        input_str = imports_str + original_string + montage_description
            
        # 这里可以加一个计算token的措施，如果token 超过阈值，就不加package的了
        input_token_count = self.token_count(input_str + package_class_montages_description)
        if input_token_count < 8182:
            pass
            # 先暂时跳过这个吧
            # input_str += package_class_montages_description
        else:
            logger.warning(f"Token count of input string for testcase analysis is too large: {input_token_count}")
        
        return input_str

    def high_level_analyze(self):
        """
        If one test function is in a test class, we analyze the test class;

        # The following cases is for Python and Go.
        else if one test function is in a file, we analyze the file;
            if file is too long,  
        """
        results = []
        fails = []
        for testclass in self.testclass_metainfo:
            name = testclass['name']
            if testclass['uris'] != "src/test/java/unit/websocketapi/TestSignedRequests.java.TestSignedRequests":
                continue
            try:
                file_path = testclass['uris'].split('.java')[0] + '.java'
                imports = self.get_imports(file_path=file_path)
                imports_str = '\n'.join(imports)
                original_string = testclass['original_string']
                                
                user_input = self.pack_static_context(testclass=testclass, original_string=original_string, 
                                                      imports=imports, file_path=file_path)
                
                full_response = self.call_llm(system_prompt=Prompt_TestSuit_Java, user_input=user_input)
                resp_dict = self.extract(full_response)
                testclass_info = {
                    'file_path': file_path,
                    'testclass_name': name,
                    'dependencies': imports
                }
                res = {**testclass_info, **resp_dict}
                results.append(res)
                
                file_name = testclass['uris'].replace('/', '_') + '.json'
                save_json(file_path=TESTCLASS_ANALYSIS_RESULT_DIR + file_name, data=res)
                logger.info(f"Analyze testclass {name} finished")
            except Exception as e:
                fails.append({'testclass_uris': testclass["uris"], 'error': str(e.stdout)})
                logger.exception(f'Analyze testclass {name} failed: {e.stdout}')

        save_json(file_path=self.testclass_analysis_result_path, data=results)
        save_json(file_path=TESTCLASS_ANALYSIS_RESULT_DIR + 'failures.json', data=fails)

    def merge_testclass_analysis_result(self, original_path, incremental_path, save_path):
        original_data = load_json(original_path)
        incremental_data = load_json(incremental_path)
        original_data.extend(incremental_data)
        data = original_data
        save_json(save_path, data)
        logger.info(f"Merged testclass analysis result saved to {save_path}")

    def batch_incremental_high_level_analyze(self, testclass_analysis_result_paths, save_path, round_number):
        results = []
        fails = []
        with concurrent.futures.ThreadPoolExecutor(max_workers=50) as executor:
            testclass_metainfo = []
            for testclass in self.testclass_metainfo:
                 file_path = testclass['file_path']
                 if file_path not in testclass_analysis_result_paths:
                     testclass_metainfo.append(testclass)

            future_to_testclass = {executor.submit(self.analyze_testclass, testclass): testclass for testclass in testclass_metainfo}
            for future in concurrent.futures.as_completed(future_to_testclass):
                testclass = future_to_testclass[future]
                try:
                    res = future.result()
                    if 'error' not in res:
                        results.append(res)
                        file_name = testclass['uris'].replace('/', '_') + '.json'
                        save_json(file_path=TESTCLASS_ANALYSIS_RESULT_DIR + file_name, data=res)
                        logger.info(f"Analyze testclass {testclass['name']} finished")
                    else:
                        fails.append(res)
                except Exception as e:
                    fails.append({'testclass_uris': testclass["uris"], 'error': str(e)})
                    logger.exception(f'Analyze testclass {testclass["name"]} failed: {e}')

        save_json(file_path=save_path, data=results)
        logger.info("Incremental high level analyze finished")
        save_json(file_path=TESTCLASS_ANALYSIS_RESULT_DIR + str(round_number) + 'failures.json', data=fails)
        
    def incremental_high_level_analyze(self, testclass_analysis_result_paths, save_path, round_number):
        """
        If one test function is in a test class, we analyze the test class;

        # The following cases is for Python and Go.
        else if one test function is in a file, we analyze the file;
            if file is too long,  
        """
        results = []
        fails = []
                        
        for testclass in self.testclass_metainfo:
            name = testclass['name']
            file_path = testclass['file_path']
            # if file_path not in success_testcase_paths:
            #     continue
            
            if file_path in testclass_analysis_result_paths:
                continue

            try:
                file_path = testclass['uris'].split('.java')[0] + '.java'
                imports = self.get_imports(file_path=file_path)
                imports_str = '\n'.join(imports)
                original_string = testclass['original_string']

                user_input = self.pack_static_context(testclass=testclass, original_string=original_string, 
                                                      imports=imports, file_path=file_path)

                full_response = self.call_llm(system_prompt=Prompt_TestSuit_Java, user_input=user_input)
                resp_dict = self.extract(full_response)
                testclass_info = {
                    'file_path': file_path,
                    'testclass_name': name,
                    'dependencies': imports
                }
                res = {**testclass_info, **resp_dict}
                results.append(res)
                
                file_name = testclass['uris'].replace('/', '_') + '.json'
                save_json(file_path=TESTCLASS_ANALYSIS_RESULT_DIR + file_name, data=res)
                logger.info(f"Analyze testclass {name} finished")
            except Exception as e:
                fails.append({'testclass_uris': testclass["uris"], 'error': str(e)})
                logger.exception(f'Analyze testclass {name} failed: {e}')

        save_json(file_path=save_path, data=results)
        logger.info("Incremental high level analyze finished")
        save_json(file_path=TESTCLASS_ANALYSIS_RESULT_DIR + str(round_number) + 'failures.json', data=fails)

def run_testcase_analyzer(analyzer: TestCaseAnalyzer, is_batch: bool = False, use_file_paths_with_two_dots: bool = False):
    logger.info("Starting testcase analyzer...")
    # analyzer.excute()
    analyzer = analyzer()
    analyzer.reslove_history_testcase_paths(analyzer.testclass_metainfo, update=False)
    if not is_batch:
        analyzer.high_level_analyze()
    else:
        if use_file_paths_with_two_dots:
            with open (FILE_PATHS_WITH_TWO_DOTS, 'r') as f:
                filter_list = f.read().splitlines()
        else:
            filter_list = []
        analyzer.batch_high_level_analyze(filter_list=filter_list)
    logger.info("Testcase analyzer finished!")


if __name__ == "__main__":
    llm = DeepSeekLLM()
    # llm = QwenLLM()
    # analyzer = PythonTestcaseAnalyzer(
    #     llm=llm,
    # )
    analyzer = JavaTestcaseAnalyzer(
        llm=llm,
    )
    
    # analyzer.excute()
    analyzer.high_level_analyze()
    # analyzer.reslove_history_testcase_paths(analyzer.testclass_metainfo, update=False)
    # analyzer.merge_testclass_analysis_result(
    #     original_path=r"/home/zhangzhe/APT/repo_parse/outputs/hospital-management-api/testclass_analysis_result.json",
    #     incremental_path=r"/home/zhangzhe/APT/repo_parse/outputs/hospital-management-api/round_1/testclass_analysis_result.json",
    #     save_path=r"/home/zhangzhe/APT/repo_parse/outputs/hospital-management-api/testclass_analysis_result.json"
    # )
    # analyzer.get_unresolved_refs()
    
    # testclass_analysis_result_paths = [res['file_path'] for res in load_json(TESTCLASS_ANALYSIS_RESULT_PATH)]
    # analyzer.batch_incremental_high_level_analyze(testclass_analysis_result_paths=testclass_analysis_result_paths, 
    #                                                 save_path="/home/zhangzhe/APT/repo_parse/outputs/commons-cli/round_1/testclass_analysis_result.json",
    #                                                 round_number=1)
    
    # coordinator = JavaNodeCoordinator()
    # coordinator.map_method_to_testcase(coordinator.testclass_analysis_result)
    # coordinator.map_class_to_testcase()
    
    # analyzer.merge_testclass_analysis_result(original_path=r"/home/zhangzhe/APT/repo_parse/outputs/binance-connector-java-3/testclass_analysis_result.json",
    #                                          incremental_path=r"/home/zhangzhe/APT/repo_parse/outputs/binance-connector-java-3/round_1/testclass_analysis_result.json",
    #                                          save_path=r"/home/zhangzhe/APT/repo_parse/outputs/binance-connector-java-3/testclass_analysis_result.json")
        
    print("Finished")
    