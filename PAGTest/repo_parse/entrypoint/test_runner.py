import os
import subprocess
import time
from typing import List
from repo_parse import logger
from repo_parse.config import JACOCO_COVERAGE_JAR_PATH, REPO_PATH, RUNNING_STATUS_DIR
from repo_parse.utils.coverage import run_jacoco_coverage_analyzer
from repo_parse.utils.data_processor import save_json

class TestRunner:
    
    def __init__(self, running_status_dir, exec_file_path):
        self.target_class_name = None
        self.key = None
        self.class_files_path = None
        self.original_result, self.apt_result = {}, {}
        self.failed_testfiles_paths = {"failed_testfiles": []}
        self.success_testfiles_paths = {"success_testfiles": []}
        self.generated_testcase_result = {"generated_testcases": []}
        self.try_generated = {}
        self.running_status_dir = running_status_dir
        self.exec_file_path = exec_file_path
        
    def run_apt_test(self, testclass_name: str) -> bool | List[str]:
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
                exec_file_path=self.exec_file_path,
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

        except Exception as e:
            logger.error(f"Failed to get coverage for class {testclass_name}: {e}")
            running_status = {
                "name": testclass_name,
                "status": "coverage get error"
            }
            self.incremental_save_running_status(file_name=testclass_name, data=running_status)
            return False

        logger.info(f"generated coverage_result: {coverage_result}")
        if coverage_result is None:
            return False, "No coverage result found"
        return True, ""

    def run_mvn_test(self, work_dir: str = REPO_PATH, testclass_name: str = None) -> List[str]:
        try:
            os.chdir(work_dir)
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
            if "Unresolved compilation problem" in e.stdout:
                running_status = {
                    "name": testclass_name,
                    "status": "compile error"
                }
            elif "org.opentest4j.AssertionFailedError:" in e.stdout:
                running_status = {
                    "name": testclass_name,
                    "status": "assertion error"
                }
            elif "test failures" in e.stdout:
                running_status = {
                    "name": testclass_name,
                    "status": "run error"
                }
            else:
                raise Exception(f"Unknown error occurred: {e}")
            self.incremental_save_running_status(file_name=testclass_name, data=running_status)
            parsed_errors = self.extract_maven_errors(maven_output=e.stdout)
            logger.info(f"Parsed Maven errors:\n{parsed_errors}")
            return parsed_errors

    def incremental_save_running_status(self, file_name, data: dict):
        if not os.path.exists(self.running_status_dir):
            os.makedirs(self.running_status_dir)
        file_path = self.running_status_dir + f"{file_name}_{str(int(time.time()))}.json"
        save_json(file_path=file_path, data=data)
        logger.info(f"Saved running status to {file_path}")