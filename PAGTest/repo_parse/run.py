from functools import partial
from repo_parse.llm.deepseek_llm import DeepSeekLLM
from repo_parse.llm.qwen_llm import QwenLLM
from repo_parse.analysis.testcase_analyzer import (
    JavaTestcaseAnalyzer, 
    PythonTestcaseAnalyzer, 
    run_testcase_analyzer
)
from repo_parse.common.enum import LanguageEnum
from repo_parse.generator.testcase_generator import JavaTestcaseGenerator
from repo_parse.metainfo.java_metainfo_builder import JavaMetaInfoBuilder
from repo_parse.metainfo.metainfo import run_build_metainfo
from repo_parse.property_graph.node_coordinator import (
    JavaNodeCoordinator
)
from repo_parse.property_graph.property_analyzer import (
    JavaPropertyAnalyzer
)
from repo_parse.config import LANGUAGE_MODE, REPO_PATH
from repo_parse.test.run_test import run_testrunner
from repo_parse.test.testutils import get_target_methods, get_testclass_names


# llm = QwenLLM()
llm = DeepSeekLLM()

running_config = None

def load_running_config():
    global running_config

    if LANGUAGE_MODE == "java":
        running_config = {
            "testcase_analyzer": {
                "analyzer": partial(JavaTestcaseAnalyzer, llm=llm)
            },
            "property_analyzer": {
                "analyzer": partial(JavaPropertyAnalyzer, llm=llm)
            },
            "node_coordinator": {
                "coordinator": partial(JavaNodeCoordinator, llm=llm)
            },
            "testcase_generator": {
                "generator": partial(JavaTestcaseGenerator, llm=llm)
            }
        }
    elif LANGUAGE_MODE == "python":
        pass

def main():

    global running_config

    # classes = {
    #     "java": {
    #         "parser": RefineJavaParser,
    #         "builder": JavaMetaInfoBuilder
    #     }
    # }

    # source_parse = {
    #     "repo_path": REPO_PATH,
    #     "language": LanguageEnum.JAVA if LANGUAGE_MODE == "java" else LanguageEnum.PYTHON,
    #     "parser": classes[LANGUAGE_MODE]["parser"]()
    # }

    # 1. Run source_parse.py to extract all metainfo.
    # run_source_parse(**source_parse)

    # builder = classes[LANGUAGE_MODE]["builder"]()

    # 2. Extract class, method, testcase, package metainfo from all metainfo.
    # run_build_metainfo(builder)
    
    load_running_config()
    
    # # # Run testcase_analysis.py to analyze the testcase.
    # run_testcase_analyzer(**running_config['testcase_analyzer'],
    #                       is_batch=True
    #                       )

    # # # # # 4. Run node_coordinator.py to coordinate the exsixting testcase and expected testcase.
    # run_node_coordinator(**running_config['node_coordinator'])
    
    get_testclass_names()
    get_target_methods()
    
    run_testrunner(**running_config['testcase_generator'])


if __name__ == "__main__":
    main()