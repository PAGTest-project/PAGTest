import os
from pathlib import Path
import re
import shutil
import subprocess
from typing import Dict, List

from repo_parse.llm.deepseek_llm import DeepSeekLLM
from repo_parse.analysis.testcase_analyzer import JavaTestcaseAnalyzer
from repo_parse.common.enum import LanguageEnum
from repo_parse.config import BAD_TESTFILE_ARCHIVE_DIR, CLASS_METAINFO_PATH, CLASS_TO_PRIMARY_TESTCASE_PATH, COMPLETE_EXPERIMENT_RESULT_PATH, COMPLETE_FAILED_TESTFILES_PATH, COMPLETE_GENERATED_TESTCASES_RESULT_PATH, COMPLETE_GENERATED_TESTFILE_RUNNING_STATUS_PATH, COMPLETE_TRY_GENERATED_TESTCASES_PATH, COMPLETED_TESTCASES_DIR, EXPERIMENT_RESULT_PATH, FAILED_TESTFILES_PATH, GENERATED_TESTCASES_DIR, GENERATED_TESTCASES_RESULT_PATH, GENERATED_TESTFILE_RUNNING_STATUS_PATH, JACOCO_COVERAGE_JAR_PATH, MAX_RETRY_NUMBER, METHOD_COVERAGE_RESULT_PATH, METHOD_METAINFO_PATH, METHOD_TO_TEST_PATHS, REPO_PATH, RESOLVED_METAINFO_PATH, RUNNING_STATUS_DIR, TESTCLASS_ANALYSIS_RESULT_PATH, TESTCLASS_METAINFO_PATH, TRY_GENERATED_TESTCASES_PATH, USE_METHOD_TO_TEST_PATHS, USE_SPECIFY_CLASS_AND_METHOD
from repo_parse.generator.testcase_generator import JavaTestcaseGenerator, TestcaseGenerator
from repo_parse.metainfo.java_metainfo_builder import JavaMetaInfoBuilder
from repo_parse.metainfo.metainfo import run_build_metainfo
from repo_parse.property_graph.node_coordinator import JavaNodeCoordinator
from repo_parse.entrypoint.test_runner import TestRunner
from repo_parse.entrypoint.testutils import get_testclass_names, load_class_already_haved_testcase, load_target_methods
from repo_parse.utils.coverage import run_jacoco_coverage_analyzer
from repo_parse import logger
from repo_parse.utils.data_processor import get_singe_key_in_dict, load_json, save_json

exec_file_path = str(Path(REPO_PATH) / "target" / "jacoco.exec")


class ProjectRunner(TestRunner):
    
    def __init__(self, generator: TestcaseGenerator):
        TestRunner.__init__(self, running_status_dir=RUNNING_STATUS_DIR, exec_file_path=exec_file_path)
        self.target_class_name = None
        self.key = None
        self.class_files_path = None
        self.original_result, self.apt_result = {}, {}
        self.method_metainfo = load_json(METHOD_METAINFO_PATH)
        self.success_copied_testcase_paths = {"success_copied_testcases": []}
        self.failed_testfiles_paths = {"failed_testfiles": []}
        self.success_testfiles_paths = {"success_testfiles": []}
        self.generated_testcase_result = {"generated_testcases": []} # target_class_name+method_name : generated_testfile_path
        self.try_generated = {}
        self.generator = generator
        
    def skip(self, key):
        if os.path.exists(TRY_GENERATED_TESTCASES_PATH):
            original_result = load_json(TRY_GENERATED_TESTCASES_PATH)
            if key in original_result.keys():
                logger.info(f"Found existing testcase for {self.key}, skip generating testcase.")
                return True
        return False

    def get_method_coverage(self, class_name, class_files_path, method_name):
        if '.' in class_name:
            class_name = class_name.replace('.', '/')
        command = [
            "java", "-jar", JACOCO_COVERAGE_JAR_PATH, 
            exec_file_path, class_files_path, class_name, method_name
        ]
        
        try: 
            result = subprocess.run(command, check=True, capture_output=True, text=True)
            return result
        except Exception as e:
            logger.error(f"Failed to run JacocoCoverageAnalyzer for {class_name}.{method_name}: {e.meassage}")
    
    def get_all_methods_coverage(self):
        class_method_list = []
        class_to_primary_testcases = load_json(CLASS_TO_PRIMARY_TESTCASE_PATH)
        for class_name, primary_testcases in class_to_primary_testcases.items():
            method_names = set()
            for primary_testcase in primary_testcases:
                method_names.add(primary_testcase['method_name'])
            class_method_list.extend([{"class": class_name, "method": method_name} for method_name in method_names])

        coverage_results = []

        for item in class_method_list:
            class_name = item["class"]

            method_name = item["method"].split("(")[0]
            result = self.run(target_class_uris=class_name, target_method_name=method_name, short_mode=True, get_all_methods_coverage=True)
            if result is None:
                logger.error(f"Failed to run test for {class_name}.{method_name}")
                continue

            output_lines = result.stdout.splitlines()
            coverage_info = {
                "class": class_name,
                "method": method_name,
                "instruction_coverage": None,
                "branch_coverage": None,
                "line_coverage": None
            }

            for line in output_lines:
                if "Instruction Coverage:" in line:
                    cov_percentage = line.split(":")[1].strip()
                    if cov_percentage == 'NaN':
                        coverage_info["instruction_coverage"] = -1
                    else:
                        coverage_info["instruction_coverage"] = float(cov_percentage)
                elif "Branch Coverage:" in line:
                    cov_percentage = line.split(":")[1].strip()
                    if cov_percentage == 'NaN':
                        coverage_info["branch_coverage"] = -1
                    else:
                        coverage_info["branch_coverage"] = float(cov_percentage)
                elif "Line Coverage:" in line:
                    cov_percentage = line.split(":")[1].strip()
                    if cov_percentage == 'NaN':
                        coverage_info["line_coverage"] = -1
                    else:
                        coverage_info["line_coverage"] = float(cov_percentage)
                        coverage_results.append(coverage_info)

        save_json(file_path=METHOD_COVERAGE_RESULT_PATH, data=coverage_results)
        logger.info(f"Saved method coverage results")

    def get_part_cover_methods(self):
        pass
    
    def run(self, target_class_uris, target_method_name, short_mode: bool = False, get_all_methods_coverage: bool = False):
        uris = target_class_uris
        
        if short_mode:
            _class = self.find_class(target_class_uris)
            if _class is None:
                logger.error(f"Failed to find class {target_class_uris}, maybe it is a abstract class.")
                return
            
            uris = _class['uris']
            method = self.find_method(target_method_name)
            if method is None:
                logger.error(f"..Failed to find method {target_method_name} in class {target_class_uris}")
                return
            target_method_name = '[' + method['uris'][method['uris'].find('[')+1:]

        method_uri = uris + "." + target_method_name
        _method = self.find_method_by_uri(method_uri)
        if _method is None:
            logger.error(f"...Failed to find method {target_method_name} in class {target_class_uris}")
            return
        
        if not self.is_method_public(_method):
            logger.warning(f"Method {target_method_name} is not public, please check the method signature.")
            return

        if uris.startswith("src/main/java/"):
            trimmed_uris = uris[len("src/main/java/"):]
        elif uris.startswith("src/main/"):
            trimmed_uris = uris[len("src/main/"):]
        elif uris.startswith("service/src/main/java/"): # For openai4j
            trimmed_uris = uris[len("service/src/main/java/"):]
        elif "src/test" in uris:
            return
        else:
            raise Exception(f"Invalid uris: {uris}")
        
        testclass_name = None

        handled_uris = trimmed_uris.split('/')
        target_class_name = '.'.join(handled_uris[:-1]) + '.' + handled_uris[-1].split('.java.')[-1]
        self.target_class_name = target_class_name
        self.target_method_name = target_method_name
        self.method_name = self.target_method_name.split("(")[0].split("]")[-1]
        self.key = self.target_class_name + self.target_method_name
        self.class_files_path = str(Path(REPO_PATH) / "target" / "classes" / target_class_name.replace(".", "/")) + ".class"
        class_name = target_class_name.split(".")[-1]

        if get_all_methods_coverage:
            return self.get_method_coverage(target_class_name, self.class_files_path, self.method_name)

        java_file_name = JavaTestcaseGenerator.get_new_class_name(class_name=_method['class_name'], target_method=_method['name'])
        for root, dirs, files in os.walk(GENERATED_TESTCASES_DIR):
            for file in files:
                if file.endswith('.java') and java_file_name in file:
                    logger.info(f"Found existing testcase for {self.key}, skip generating testcase.")
                    return

        if os.path.exists(FAILED_TESTFILES_PATH):
            failed_testfiles = load_json(FAILED_TESTFILES_PATH)
            if os.path.exists(GENERATED_TESTFILE_RUNNING_STATUS_PATH):
                data = load_json(GENERATED_TESTFILE_RUNNING_STATUS_PATH)
                success_testfiles = data["success_testfiles"]
            for failed_testfile in failed_testfiles['failed_testfiles']:
                if failed_testfile in success_testfiles:
                    continue
                self.archive_bad_testclass(failed_testfile)

        """Run testcase generation"""
        generated_class_name = None
        target_method = None
        data = load_json(METHOD_METAINFO_PATH)
        for method in data:
            name = '[' + method['uris'].split('[')[-1]
            if name == target_method_name and method['class_name'] == class_name:
                logger.info(f"Found method {target_method_name} in class {class_name}")
                target_method = method
        
        if target_method is None:
            logger.error(f".Failed to find method {target_method_name} in class {class_name}")
            return
        
        try:
            try_generated, generated_class_name = self.generator.refined_generate_testcases(target_method)
            self.try_generated[try_generated] = []
        except Exception as e:
            logger.error(f"we failed to generate test cases for method {class_name + '_' + target_method_name}: {e}")
            running_status = {
                "name": class_name + '_' + target_method_name + "Test",
                "status": "generate failed"
            }
            self.incremental_save_running_status(file_name=class_name + '_' + target_method_name + "Test", data=running_status)
            self.save_try_generated_testcases()
            return

        if generated_class_name is None:
            logger.error(f"Failed to generate test cases for method {class_name + '_' + target_method_name}")
            self.save_try_generated_testcases()
            return

        original_testcase_path = GENERATED_TESTCASES_DIR + generated_class_name + '.java'
        logger.info(f"original_testcase_path: {original_testcase_path}")
        dest_testcase_dir = str(Path(REPO_PATH) / "src" / "test" / "java" / Path('/'.join(target_class_name.split('.')[:-1])))
        dest_testcase_path = os.path.join(dest_testcase_dir, os.path.basename(original_testcase_path))
        logger.info(f"dest_testcase_path: {dest_testcase_path}")
        
        try:
            if not os.path.exists(dest_testcase_dir):
                os.makedirs(dest_testcase_dir)
            
            shutil.copy2(original_testcase_path, dest_testcase_path)
            logger.info(f"Successfully copied test case from {original_testcase_path} to {dest_testcase_dir}")
            self.success_copied_testcase_paths['success_copied_testcases'].append(dest_testcase_path)
        except Exception as e:
            logger.error(f"An error occurred while copying test case: {e}")

        if testclass_name is not None:
            self.generated_testcase_result["generated_testcases"].append({testclass_name + '_' + target_method_name: dest_testcase_path})
        else:
            self.generated_testcase_result["generated_testcases"].append({uris + target_method_name: dest_testcase_path})

        apt_status = False
        no_testcase_generated = False
        if not generated_class_name:
            logger.warning(f"{target_method_name} No test case generated")
            self.apt_result[self.key] = None
            no_testcase_generated = True
        else:
            apt_status, err = self.run_apt_test(testclass_name=generated_class_name, dest_testcase_path=dest_testcase_path)
            if err:
                self.try_generated[try_generated].append(err)
                if err == "coverage get error":
                    raise Exception(f"No coverage result found for {class_name}.{target_method_name}")

        if not err:
            self.success_testfiles_paths["success_testfiles"].append(dest_testcase_path)
            logger.info(f"{target_method_name} Test passed")
        elif no_testcase_generated is True:
            logger.warning(f"{target_method_name} No test case generated")
        else:
            retry_apt_status = apt_status
            retry_times = 0
            while retry_times < MAX_RETRY_NUMBER and err:
                retry_times += 1
                parsed_errors = self.remove_file_path_from_list(retry_apt_status, dest_testcase_path)
                regenerated_class_name = self.retry_generate_testcases(
                    target_method=target_method, parsed_errors=parsed_errors, 
                    original_testcase_path=original_testcase_path, dest_testcase_path=dest_testcase_path)
                if regenerated_class_name is None:
                    continue

                retry_apt_status, err = self.run_apt_test(testclass_name=regenerated_class_name, dest_testcase_path=dest_testcase_path)
                if err:
                    self.try_generated[try_generated].append(err)
                else:
                    self.success_testfiles_paths["success_testfiles"].append(dest_testcase_path)
                    logger.info(f"{target_method_name} Test passed")
                    break

            self.failed_testfiles_paths["failed_testfiles"].append(dest_testcase_path)

        self.save_running_status()
        self.save_experiment_result(incremental_save=True)
        self.save_generated_testcase_result()
        self.save_failed_testclass_paths()
        self.save_try_generated_testcases()

        if not err:
            return dest_testcase_path
    
    def remove_file_path_from_list(self, log_lines, file_path):
        cleaned_lines = []
        for line in log_lines:
            if file_path in line:
                cleaned_line = line.replace(file_path, '')
                cleaned_lines.append(cleaned_line)
            else:
                cleaned_lines.append(line)
        return cleaned_lines

    def save_try_generated_testcases(self, incremental_save: bool = False, file_path: str = TRY_GENERATED_TESTCASES_PATH):
        if incremental_save and os.path.exists(file_path):
           try_generated_testcases = load_json(file_path)
           self.try_generated.update(try_generated_testcases)
        save_json(file_path=file_path, data=self.try_generated)
        logger.info(f"Saved try_generated_testcases to {file_path}")

    def find_class(self, class_to_find: str):
        class_metainfo = load_json(CLASS_METAINFO_PATH)
        _class = None
        cnt = 0
        for cls in class_metainfo:
            if cls["name"] == class_to_find:
                _class = cls
                cnt += 1
        if cnt > 1:
            logger.error(f"Find more than one class with the same name: {self.target_class_name}")
            return None
        return _class
    
    @staticmethod
    def is_method_public(method: Dict):
        return 'public' in method['attributes'].get('non_marker_annotations', [])
    
    def find_method_by_uri(self, method_uri: str):
        for m in self.method_metainfo:
            if m["uris"] == method_uri:
                return m

    def find_method(self, method_to_find: str):
        method = None
        cnt = 0
        for m in self.method_metainfo :
            if m["name"] == method_to_find:
                method = m
                cnt += 1
        if cnt > 1:
            logger.error(f"Find more than one method with the same name: {method_to_find}")
            return None
        return method

    def run_mvn_test(self, work_dir: str = REPO_PATH, testclass_name: str = None) -> List[str]:
        try:
            logger.info(f"Start running maven test command: {testclass_name}")
            os.chdir(work_dir)
            clean_command = ["mvn", "clean"]
            result_clean = subprocess.run(clean_command, check=True, capture_output=True, text=True)

            test_command = ["mvn", "test", "-Dcheckstyle.skip=true", "-Drat.skip=true", "-Dmoditect.skip=true"]
            if testclass_name:
                test_command.extend([f"-Dtest={testclass_name}"])
            result = subprocess.run(test_command, check=True, capture_output=True, text=True)
            logger.info(f"Maven test command {' '.join(test_command)} executed successfully")
                
            running_status = {
                "name": testclass_name,
                "status": "test success"
            }
            self.incremental_save_running_status(file_name=testclass_name, data=running_status)
        except subprocess.CalledProcessError as e:
            logger.error(f"Maven test command failed with error code {e.returncode}: {e}")
            logger.error(f"Maven test output:\n{e.stdout}")
            if (
                "expected:" in e.stdout and
                "but was:" in e.stdout
            ):
                running_status = {
                    "name": testclass_name,
                    "status": "assertion error",
                    "error": e.stdout
                }
            elif "Unresolved compilation problem" or "Compilation failure" in e.stdout:
                running_status = {
                    "name": testclass_name,
                    "status": "compile error",
                    "error": e.stdout
                }
            elif "org.opentest4j.AssertionFailedError:" in e.stdout:
                running_status = {
                    "name": testclass_name,
                    "status": "assertion error",
                    "error": e.stdout
                }
            elif "test failures" in e.stdout:
                running_status = {
                    "name": testclass_name,
                    "status": "run error",
                    "error": e.stdout
                }
            else:
                logger.error(f"Unknown error occurred: {e.stdout}")
            self.incremental_save_running_status(file_name=testclass_name, data=running_status)
            parsed_errors = self.extract_maven_errors(maven_output=e.stdout)
            logger.info(f"Parsed Maven errors:\n{parsed_errors}")
            return parsed_errors

    def extract_maven_errors(self, maven_output: str) -> List[str]:
        error_messages = []
        lines = maven_output.splitlines()
        capture = False
        current_error_block = []

        exclude_start_pattern = re.compile(r'^\[ERROR\] Failed to execute goal org\.apache\.maven\.plugins:maven-surefire-plugin.*$')
        error_start_pattern = re.compile(r'^\[ERROR\].*$')

        for line in lines:
            if exclude_start_pattern.match(line):
                break

            if error_start_pattern.match(line):
                if current_error_block:
                    error_messages.extend(current_error_block)
                    current_error_block = []

                capture = True
                current_error_block = [line.strip()]
            elif capture:
                if re.match(r'^\[INFO\]', line) or re.match(r'^\[WARN\]', line):
                    capture = False
                    error_messages.extend(current_error_block)
                    current_error_block = []
                else:
                    current_error_block.append(line.strip())

        if current_error_block:
            error_messages.extend(current_error_block)

        if len(error_messages) > 60:
            error_messages = error_messages[-60:]

        return error_messages

    def archive_bad_testclass(self, testfile_path: str):
        archive_dir = Path(BAD_TESTFILE_ARCHIVE_DIR)
        archive_dir.mkdir(parents=True, exist_ok=True)
        
        testfile_path = Path(testfile_path)
        if testfile_path.exists():
            try:
                shutil.move(testfile_path, archive_dir / os.path.basename(testfile_path))
                logger.info(f"Archived {testfile_path} to {archive_dir}")
            except Exception as e:
                logger.error(f"Failed to archive {testfile_path}: {e}")
        else:
            pass

    def save_generated_testcase_result(self, file_path: str = GENERATED_TESTCASES_RESULT_PATH):
        if os.path.exists(file_path):
            original_result = load_json(file_path)
            existing_keys = set([get_singe_key_in_dict(item) for item in original_result.get("generated_testcases", [])])

            for item in original_result.get("generated_testcases", []):
                if get_singe_key_in_dict(item) not in existing_keys:
                    self.generated_testcase_result.setdefault("generated_testcases", []).append(item)
        
        save_json(file_path=file_path, data=self.generated_testcase_result)
        logger.info(f"Saved generated test case result to {file_path}")    

    def save_running_status(self, file_path: str = GENERATED_TESTFILE_RUNNING_STATUS_PATH):
        save_json(file_path=file_path, data={**self.failed_testfiles_paths, **self.success_testfiles_paths})
        logger.info(f"Saved running status to {file_path}")

    def save_failed_testclass_paths(self, file_path: str = FAILED_TESTFILES_PATH):
        save_json(file_path=file_path, data=self.failed_testfiles_paths)
        logger.info(f"Saved failed test class paths to {file_path}")    

    def save_experiment_result(self, incremental_save: bool = False, file_path: str = EXPERIMENT_RESULT_PATH):
        if incremental_save and os.path.exists(file_path):
            self.experiment_result = load_json(file_path)
            self.experiment_result["apt"].update(self.apt_result)                
        else:
            self.experiment_result = {"original": self.original_result, "apt": self.apt_result}
        save_json(file_path=file_path, data=self.experiment_result)

    def run_apt_test(self, testclass_name: str, dest_testcase_path: str) -> bool | List[str]:
        try:
            status = self.run_mvn_test(testclass_name=testclass_name)
            if status is not None:
                return status, "run_mvn_test error"
        except Exception as e:
            logger.error(f"Test failed for class {testclass_name}: {e}")
            return False, "run_mvn_test unknown error"

        try:
            coverage_result = run_jacoco_coverage_analyzer(
                jar_path=JACOCO_COVERAGE_JAR_PATH,
                exec_file_path=exec_file_path,
                class_files_path=self.class_files_path,
                target_class_name=self.target_class_name,
                target_method_name=self.target_method_name.split("(")[0].split("]")[-1]
            )
            running_status = {
                "name": testclass_name,
                "status": "coverage get success",
                "coverage_result": coverage_result,
            }
            self.incremental_save_running_status(file_name=testclass_name, data=running_status)
            if coverage_result is None:
                raise Exception("No coverage result found")
        except Exception as e:
            logger.error(f"Failed to get coverage for class {testclass_name}: {e}")
            running_status = {
                "name": testclass_name,
                "status": "coverage get error"
            }
            self.incremental_save_running_status(file_name=testclass_name, data=running_status)
            return False, "coverage get error"

        logger.info(f"generated coverage_result: {coverage_result}")
        if coverage_result is None:
            return False, "No coverage result found"
        self.apt_result[self.key] = coverage_result[self.method_name]
        return True, ""

    def retry_generate_testcases(self, target_method, parsed_errors,
                                 original_testcase_path, dest_testcase_path):
        regenerated_class_name = self.generator.retry_generate_testcases(
            method=target_method, parsed_errors=parsed_errors)
        try:        
            shutil.copy2(original_testcase_path, dest_testcase_path)
            logger.info(f"Successfully copied test case from {original_testcase_path} to {dest_testcase_path}")
            self.success_copied_testcase_paths['success_copied_testcases'].append(dest_testcase_path)
            return regenerated_class_name
        except Exception as e:
            logger.error(f"An error occurred while copying test case: {e}")

def create_round_directory(base_path=RESOLVED_METAINFO_PATH, prefix='round_'):
    round_number = 1
    while True:
        directory_path = os.path.join(base_path, f'{prefix}{round_number}')
        if not os.path.exists(directory_path):
            os.makedirs(directory_path, exist_ok=True)
            logger.info(f"Create directory: {directory_path}")
            return directory_path, round_number
        round_number += 1

def run_testrunner(generator: TestcaseGenerator):
    method_metainfo = load_json(METHOD_METAINFO_PATH)
    generator = generator()
    runner = ProjectRunner(generator)
    batch_run(method_metainfo=method_metainfo, runner=runner)

def batch_run(method_metainfo, runner: TestRunner):
    
    while True:
        success_testcase_paths = run(method_metainfo=method_metainfo, runner=runner)
        logger.info(f"one batch finished, success_testcase_paths: {success_testcase_paths}")
        if not success_testcase_paths:
            logger.info("No more test cases to run, break")
            break

        # run_source_parse(repo_path=REPO_PATH, language=LanguageEnum.JAVA, parser=RefineJavaParser())
        logger.info("Run source parse finished.")
        run_build_metainfo(builder=JavaMetaInfoBuilder())

        directory_path, round_number = create_round_directory()

        analyzer = JavaTestcaseAnalyzer(llm=DeepSeekLLM())
        incremental_path = os.path.join(directory_path, "testclass_analysis_result.json")
        
        testclass_analysis_result_paths = [res['file_path'] for res in load_json(TESTCLASS_ANALYSIS_RESULT_PATH)]
        analyzer.batch_incremental_high_level_analyze(testclass_analysis_result_paths=testclass_analysis_result_paths, 
                                                    save_path=incremental_path,
                                                    round_number=round_number)
        analyzer.merge_testclass_analysis_result(original_path=TESTCLASS_ANALYSIS_RESULT_PATH,
                                                 incremental_path=incremental_path,
                                                #  save_path=incremental_path.split('.json')[0] + str(round_number) + ".json",
                                                 save_path=TESTCLASS_ANALYSIS_RESULT_PATH)
        
        coordinator = JavaNodeCoordinator()
        coordinator.map_method_to_testcase(coordinator.testclass_analysis_result)
        coordinator.map_class_to_testcase()
        logger.info("Run node coordinator finished, start to generate testcase in new round.")
        
        get_testclass_names()

def is_getter_or_setter(method_name):
    return method_name.startswith("get") or method_name.startswith("set")

def get_all_methods_coverage():
    llm = DeepSeekLLM()
    runner = ProjectRunner(llm)
    runner.get_all_methods_coverage()

def run(method_metainfo, runner: TestRunner) -> List[str]:
    success_testcase_paths = []
    target_class = load_class_already_haved_testcase()
    for method in method_metainfo:
        file_path = method["file"]
        if 'test' in file_path:
            logger.info(f"Skipping method {method['name']}, since it may be a test class")
            continue
        
        if not method["return_type"]:
            logger.info(f"Skipping method {method['name']}, since it has no return type, may be a constructor")
            continue
        
        modifiers = method['attributes'].get('modifiers', "")
        if modifiers == "private":
            logger.info(f"Skipping method {method['name']}, since it is private")
            continue
        
        if 'main' in method['name']:
            logger.info(f"Skipping method {method['name']}, since it is a main method")
            continue
        
        num_of_newline = method["original_string"].count('\n')
        if num_of_newline < 3:
            logger.info(f"Skipping method {method['name']}, since it has only 2 line")
            continue
        
        file_path = method['file']
        class_uri = method["class_uri"]
        target_method_name = '[' + method['uris'][method['uris'].find('[')+1:]
        class_name = class_uri.split('.java.')[-1]
        pure_method_name = target_method_name.split(']')[-1].split('(')[0]
        dest_testcase_path = None
                    
        if (
            USE_SPECIFY_CLASS_AND_METHOD and
            class_name not in target_class
        ):
            logger.info(f"Skipping method {method['name']}, since it is not in the specified class or method")
            continue

        if USE_METHOD_TO_TEST_PATHS:
            for p in METHOD_TO_TEST_PATHS:
                if file_path.startswith(p):
                    dest_testcase_path = runner.run(target_class_uris=class_uri, target_method_name=target_method_name)
                    if dest_testcase_path is not None:
                        success_testcase_paths.append(dest_testcase_path)
                logger.info(f"skipping method {method['name']}, since it is not in the specified path")
        else:
            dest_testcase_path = runner.run(target_class_uris=class_uri, target_method_name=target_method_name)
            if dest_testcase_path is not None:
                success_testcase_paths.append(dest_testcase_path)

    return success_testcase_paths
