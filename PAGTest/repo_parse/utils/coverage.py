import subprocess
from repo_parse import logger

def run_jacoco_coverage_analyzer(jar_path, exec_file_path, class_files_path, target_class_name, target_method_name=None):
    if '.' in target_class_name:
        target_class_name = target_class_name.replace('.', '/')
    
    command = [
        'java', '-jar', jar_path,
        exec_file_path,
        class_files_path,
        target_class_name
    ]

    if target_method_name:
        command.append(target_method_name)

    try:
        result = subprocess.run(command, check=True, capture_output=True, text=True)
        logger.info(f"run_jacoco_coverage_analyzer Command executed successfully: {' '.join(command)}")
        output_lines = result.stdout.split('\n')
        instruction_coverage_result = {}
        branch_coverage_result = {}
        line_coverage_result = {}
        
        if target_method_name:
            for line in output_lines:
                if line.startswith('Instruction Coverage:'):
                    cov_percentage = line.split(':')[-1].strip()
                    if cov_percentage == 'NaN':
                        cov_percentage = -1
                    if "Instruction Coverage" in instruction_coverage_result:
                        instruction_coverage_result["Instruction Coverage"] = max(instruction_coverage_result["Instruction Coverage"], float(cov_percentage))
                        logger.warning(f"Duplicate method name found: {target_method_name}")
                    else:
                        instruction_coverage_result["Instruction Coverage"] = float(cov_percentage)
                
                if line.startswith('Branch Coverage:'):
                    cov_percentage = line.split(':')[-1].strip()
                    if cov_percentage == 'NaN':
                        cov_percentage = -1
                    if "Branch Coverage" in branch_coverage_result:
                        branch_coverage_result["Branch Coverage"] = max(branch_coverage_result["Branch Coverage"], float(cov_percentage))
                    else:
                        branch_coverage_result["Branch Coverage"] = float(cov_percentage)
                        
                if line.startswith('Line Coverage:'):
                    cov_percentage = line.split(':')[-1].strip()
                    if cov_percentage == 'NaN':
                        cov_percentage = -1
                    if "Line Coverage" in line_coverage_result:
                        line_coverage_result["Line Coverage"] = max(line_coverage_result["Line Coverage"], float(cov_percentage))
                    else:
                        line_coverage_result["Line Coverage"] = float(cov_percentage)

            return {target_method_name: [instruction_coverage_result, branch_coverage_result, line_coverage_result]}
        else:
            methods_coverage = {}
            current_method = None
            current_coverage = {}

            for line in output_lines:
                if line.startswith('Method name:'):
                    if current_method:
                        methods_coverage[current_method] = current_coverage
                    current_method = line.split(':')[-1].strip()
                    current_coverage = {}
                elif line.startswith('Instruction Coverage:'):
                    cov_percentage = line.split(':')[-1].strip()
                    if cov_percentage == 'NaN':
                        cov_percentage = -1
                    current_coverage['Instruction Coverage'] = float(cov_percentage)
                elif line.startswith('Branch Coverage:'):
                    cov_percentage = line.split(':')[-1].strip()
                    if cov_percentage == 'NaN':
                        cov_percentage = -1
                    current_coverage['Branch Coverage'] = float(cov_percentage)
                elif line.startswith('Line Coverage:'):
                    cov_percentage = line.split(':')[-1].strip()
                    if cov_percentage == 'NaN':
                        cov_percentage = -1
                    current_coverage['Line Coverage'] = float(cov_percentage)

            if current_method:
                methods_coverage[current_method] = current_coverage
            return methods_coverage
    except subprocess.CalledProcessError as e:
        logger.error(f"run_jacoco_coverage_analyzer Command failed: {' '.join(command)}, {e.stdout}")
