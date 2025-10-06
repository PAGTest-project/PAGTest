from pathlib import Path

current_file_path = Path(__file__).resolve()
CURRENT_PROJECT_PATH = str(current_file_path.parent.parent)
print("CURRENT_PROJECT_PATH:", CURRENT_PROJECT_PATH)

LANGUAGE_MODE = "java"

REPO_PATH = r"~/targets/java/commons-collections-3"
PACKAGE_PREFIX = "org.apache.commons.collections4"

SKIP_IF_NOT_RELEVANT_TESTFILES_FOR_CLASS = False
USE_SPECIFY_CLASS_AND_METHOD = True
USE_METHOD_TO_TEST_PATHS = False
METHOD_TO_TEST_PATHS = [
]
BAD_TESTFILE_ARCHIVE_DIR = str(Path(REPO_PATH) / "bad_testfile_archive")


EXCEPTE_PATH = [
    BAD_TESTFILE_ARCHIVE_DIR,    
]

RESOLVED_METAINFO_PATH = CURRENT_PROJECT_PATH + r"/repo_parse/outputs/" + REPO_PATH.split('/')[-1] + '/'
ALL_METAINFO_PATH = RESOLVED_METAINFO_PATH + "all_metainfo.json"
LOG_DIR = RESOLVED_METAINFO_PATH + "logs/"

CLASS_METAINFO_PATH = RESOLVED_METAINFO_PATH + "class_metainfo.json"
METHOD_METAINFO_PATH = RESOLVED_METAINFO_PATH + "method_metainfo.json"
TESTCASE_METAINFO_PATH = RESOLVED_METAINFO_PATH + "testcase_metainfo.json"
TESTCLASS_METAINFO_PATH = RESOLVED_METAINFO_PATH + "testclass_metainfo.json"
FILE_IMPORTS_PATH = RESOLVED_METAINFO_PATH + "file_imports.json"
PACKAGES_METAINFO_PATH = RESOLVED_METAINFO_PATH + "packages_metainfo.json"
FILE_METAINFO_PATH = RESOLVED_METAINFO_PATH + "file_metainfo.json"
TESTFILE_METAINFO_PATH = RESOLVED_METAINFO_PATH + "testfile_metainfo.json"
ABSTRACTCLASS_METAINFO_PATH = RESOLVED_METAINFO_PATH + "abstractclass_metainfo.json"
JUNIT_VERSION_PATH = RESOLVED_METAINFO_PATH + "junit_version.json"

CLASS_PROPERTY_PATH = RESOLVED_METAINFO_PATH + "class_property.json"
CLASS_PROPERTY_DIR = RESOLVED_METAINFO_PATH + "class_property/"
CLASS_SIMILARITY_PATH = RESOLVED_METAINFO_PATH + "class_similarity.json"
TESTCASE_ANALYSIS_RESULT_PATH = RESOLVED_METAINFO_PATH + "testcase_analysis_result.json"
TESTCLASS_ANALYSIS_RESULT_PATH = RESOLVED_METAINFO_PATH + "testclass_analysis_result.json"
TESTCLASS_ANALYSIS_RESULT_DIR = RESOLVED_METAINFO_PATH + "testclass_analysis_result/"
INHERIT_TREE_PATH = RESOLVED_METAINFO_PATH + "inherit_tree.json"
FUNC_RELATION_PATH = RESOLVED_METAINFO_PATH + "func_relation.json"
METHOD_PROPERTY_DIR = RESOLVED_METAINFO_PATH + "method_property/"
BROTHER_RELATIONS_PATH = RESOLVED_METAINFO_PATH + "brother_relations.json"
BROTHER_ENHANCEMENTS_PATH = RESOLVED_METAINFO_PATH + "brother_enhancements.json"
PARENT_ENHANCEMENTS_PATH = RESOLVED_METAINFO_PATH + "parent_enhancements.json"
CHILD_ENHANCEMENTS_PATH = RESOLVED_METAINFO_PATH + "child_enhancements.json"
POTENTIAL_BROTHER_RELATIONS_PATH = RESOLVED_METAINFO_PATH + "potential_brother_relations.json"
INTERFACE_BROTHER_RELATIONS_PATH = RESOLVED_METAINFO_PATH + "interface_brother_relations.json"
INTERFACE_BROTHER_ENHANCEMENTS_PATH = RESOLVED_METAINFO_PATH + "interface_brother_enhancements.json"
NODE_TO_TESTCASE_PATH = RESOLVED_METAINFO_PATH + "node_to_testcase.json"
NODE_COORDINATOR_RESULT_PATH = RESOLVED_METAINFO_PATH + "node_coordinator_result.json"
PART_UNMAPPED_NODES_PATH = RESOLVED_METAINFO_PATH + "part_unmapped_nodes.json"
FULL_UNMAPPED_NODES_PATH = RESOLVED_METAINFO_PATH + "full_unmapped_nodes.json"
EXISTING_ENHANCEMENTS_PATH = RESOLVED_METAINFO_PATH + "existing_enhancements.json"
METHOD_TO_PRIMARY_TESTCASE_PATH = RESOLVED_METAINFO_PATH + "method_to_primary_testcase.json"
METHOD_TO_RELEVANT_TESTCASE_PATH = RESOLVED_METAINFO_PATH + "method_to_relevant_testcase.json"
CLASS_TO_PRIMARY_TESTCASE_PATH = RESOLVED_METAINFO_PATH + "class_to_primary_testcase.json"
CLASS_TO_RELEVANT_TESTCASE_PATH = RESOLVED_METAINFO_PATH + "class_to_relevant_testcase.json"
HISTORY_TESTCASE_PATHS_PATH = RESOLVED_METAINFO_PATH + "history_testcase_paths.json"
FILE_PATHS_WITH_TWO_DOTS = RESOLVED_METAINFO_PATH + "file_paths_with_two_dots.txt"
PROPERTY_GRAPH_PATH = RESOLVED_METAINFO_PATH + "property_graph.json"
METHOD_COVERAGE_RESULT_PATH = RESOLVED_METAINFO_PATH + "method_coverage_result.json"

GENERATED_TESTCASES_PATH = RESOLVED_METAINFO_PATH + "generated_testcases.json"
GENERATED_TESTCASES_DIR = RESOLVED_METAINFO_PATH + "generated_testcases/"
COMPLETED_TESTCASES_DIR = RESOLVED_METAINFO_PATH + "completed_testcases/"
RUNNING_STATUS_DIR = RESOLVED_METAINFO_PATH + "running_status/"
FAILED_TESTFILES_PATH = RESOLVED_METAINFO_PATH + "failed_testfiles.json"
GENERATED_TESTFILE_RUNNING_STATUS_PATH = RESOLVED_METAINFO_PATH + "generated_testfile_running_status.json"
GENERATED_TESTCASES_RESULT_PATH = RESOLVED_METAINFO_PATH + "generated_testcases_result.json"
TESTFILES_PATH = RESOLVED_METAINFO_PATH + "testfiles.txt"
TARGET_METHODS_PATH = RESOLVED_METAINFO_PATH + "target_methods.txt"
CLASS_ALREADY_HAVED_TESTCASE_PATH = RESOLVED_METAINFO_PATH + "class_already_haved_testcase.txt"
EXPERIMENT_RESULT_PATH = RESOLVED_METAINFO_PATH + "experiment_result.json"
TRY_GENERATED_TESTCASES_PATH = RESOLVED_METAINFO_PATH + "try_generated_testcases.json"
FINAL_RESULT_PATH = RESOLVED_METAINFO_PATH + "final_result.json"
STATUS_DISTRIBUTION_PATH = RESOLVED_METAINFO_PATH + "status_distribution.txt"
MAX_RETRY_NUMBER = 2

"""For complete test"""
COMPLETE_EXPERIMENT_RESULT_PATH = RESOLVED_METAINFO_PATH + "complete_experiment_result.json"
COMPLETE_GENERATED_TESTFILE_RUNNING_STATUS_PATH = RESOLVED_METAINFO_PATH + "complete_generated_testfile_running_status.json"
COMPLETE_GENERATED_TESTCASES_RESULT_PATH = RESOLVED_METAINFO_PATH + "complete_generated_testcases_result.json"
COMPLETE_FAILED_TESTFILES_PATH = RESOLVED_METAINFO_PATH + "complete_failed_testfiles.json"
COMPLETE_TRY_GENERATED_TESTCASES_PATH = RESOLVED_METAINFO_PATH + "complete_try_generated_testcases.json"

"""For Python specific"""
PYTHON_STD_LIB_PATH = CURRENT_PROJECT_PATH + r"/repo_parse/scope_graph/languages/python/sys_modules.json"
PYTHON_KEYWORD_AND_BUILTIN_PATH = CURRENT_PROJECT_PATH + r"/repo_parse/scope_graph/languages/python/keyword_and_builtin.json"

"""For Java specific"""
JAVA_SCM = CURRENT_PROJECT_PATH + r"/repo_parse/scope_graph/languages/java/java.scm"
JAVA_STD_LIB_PATH = CURRENT_PROJECT_PATH + r"/repo_parse/scope_graph/languages/java/sys_modules.json"
JAVA_KEYWORD_AND_BUILTIN_PATH = CURRENT_PROJECT_PATH + r"/repo_parse/scope_graph/languages/java/keyword_and_builtin.json"
RECORD_METAINFO_PATH = RESOLVED_METAINFO_PATH + "record_metainfo.json"
INTERFACE_METAINFO_PATH = RESOLVED_METAINFO_PATH + "interface_metainfo.json"

JACOCO_COVERAGE_JAR_PATH = str(Path(CURRENT_PROJECT_PATH) / "repo_parse" / "utils" / "coverage-1.0-SNAPSHOT.jar") 
