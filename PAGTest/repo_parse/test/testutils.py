import os

from repo_parse.config import BROTHER_ENHANCEMENTS_PATH, BROTHER_RELATIONS_PATH, CLASS_ALREADY_HAVED_TESTCASE_PATH, CLASS_TO_PRIMARY_TESTCASE_PATH, FINAL_RESULT_PATH, RUNNING_STATUS_DIR, STATUS_DISTRIBUTION_PATH, TARGET_METHODS_PATH, TESTCLASS_METAINFO_PATH, TESTFILES_PATH
from repo_parse.utils.data_processor import load_json, save_json
from repo_parse import logger


def find_latest_modified_file(directory):
    if not os.path.isdir(directory):
        return None

    latest_file = None
    latest_mtime = 0
    
    for root, dirs, files in os.walk(directory):
        for file in files:
            filepath = os.path.join(root, file)
            mtime = os.path.getmtime(filepath)
            if mtime > latest_mtime:
                latest_mtime = mtime
                latest_file = filepath       
    return latest_file

def get_testfiles():
    testclasses = load_json(TESTCLASS_METAINFO_PATH)
    testfiles = [testclass['uris'] for testclass in testclasses]
    with open(TESTFILES_PATH, 'w') as f:
        for testfile in testfiles:
            f.write(testfile + '\n')
    logger.info(f'Testfiles saved to {TESTFILES_PATH}')
    
def get_testclass_names():
    data = load_json(CLASS_TO_PRIMARY_TESTCASE_PATH)
    res = []
    for k in data.keys():
        res.append(k)
    with open(CLASS_ALREADY_HAVED_TESTCASE_PATH, 'w') as f:
        for name in res:
            f.write(name + '\n')
    logger.info(f'get_testclass_names saved to {CLASS_ALREADY_HAVED_TESTCASE_PATH}')
    
def get_target_methods():
    with open(CLASS_ALREADY_HAVED_TESTCASE_PATH, 'r') as f:
        data = f.read()
    with open(TARGET_METHODS_PATH, 'w') as f:
        f.write(data)
    logger.info(f'get_target_methods saved to {TARGET_METHODS_PATH}')

def load_target_methods():
    if not os.path.exists(TARGET_METHODS_PATH):
        raise FileNotFoundError(f'Target methods not found at {TARGET_METHODS_PATH}')
    with open(TARGET_METHODS_PATH, 'r') as f:
        data = f.read().splitlines()
    res = {"class": [], "method": []}
    for line in data:
        if '.' in line:
            res['method'].append(line)
        else:
            res['class'].append(line)
    return res 

def load_class_already_haved_testcase():
    if not os.path.exists(CLASS_ALREADY_HAVED_TESTCASE_PATH):
        raise FileNotFoundError(f'Class already had testcase not found at {CLASS_ALREADY_HAVED_TESTCASE_PATH}')
    with open(CLASS_ALREADY_HAVED_TESTCASE_PATH, 'r') as f:
        data = f.read().splitlines()
    return data

def extract_final_result():
    result = {}
    status_priority = {
        'compile error': 0,
        'assertion error': 1,
        'test success': 2,
        'coverage_result get error': 3,
        'coverage get error': 4,
        'coverage get success': 5
    }

    for filename in os.listdir(RUNNING_STATUS_DIR):
        items = filename.split('_')
        class_name = items[0]
        method_name = items[1].split('Test')[0]
        data = load_json(os.path.join(RUNNING_STATUS_DIR, filename))
        key = class_name + '.' + method_name
        if key not in result:
            result[key] = {}
            result[key]['status'] = 'compile error'

        current_status = result[key]['status']
        new_status = data['status']
        
        if new_status == "generate failed":
            logger.info(f"Generate failed for {key}")

        if status_priority[new_status] > status_priority[current_status]:
            result[key]['status'] = new_status

        if 'coverage_result' in data:
            if data['coverage_result'] is None:
                result[key]['status'] = 'coverage get error'
                continue
            if method_name in data['coverage_result']:
                coverage_result = data['coverage_result'][method_name]
                result[key]['coverage_result'] = coverage_result
            else:
                logger.error(f'Coverage result not found for {key}')
                result[key]['status'] = 'coverage get error'
            
    save_json(file_path=FINAL_RESULT_PATH, data=result)
    logger.info(f'Final result saved to {FINAL_RESULT_PATH}')

def analyze_results(file_path: str = FINAL_RESULT_PATH):
    data = load_json(file_path)    
    status_count = {}
    line_coverage_count = 0

    for key, value in data.items():
        status = value.get("status")
        if status:
            if status in status_count:
                status_count[status] += 1
            else:
                status_count[status] = 1

        coverage_result = value.get("coverage_result", [])
        if not coverage_result:
            continue
        
        line_coverage = coverage_result[2].get("Line Coverage")
        branch_coverage = coverage_result[1].get("Branch Coverage")
        
        if line_coverage == 'NaN':
            line_coverage = -1
        if branch_coverage == 'NaN':
            branch_coverage = -1

        if line_coverage == 1.0 and (branch_coverage == 1.0 or branch_coverage == -1):
            line_coverage_count += 1

    return status_count, line_coverage_count

def print_and_save_status_distribution(status_count, line_coverage_count, filename = STATUS_DISTRIBUTION_PATH):
    with open(filename, 'w') as file:
        file.write("Status Distribution:\n")
        for status, count in status_count.items():
            print(f"{status}: {count}")
            file.write(f"{status}: {count}\n")
        file.write(f"\nNumber of Line Coverage = 1: {line_coverage_count}\n")
        print(f"\nNumber of Line Coverage = 1: {line_coverage_count}")


if __name__ == '__main__':
    # get_testfiles()
    # get_testclass_names()

    extract_final_result()
    
    status_count, line_coverage_count = analyze_results()
    print_and_save_status_distribution(status_count, line_coverage_count)