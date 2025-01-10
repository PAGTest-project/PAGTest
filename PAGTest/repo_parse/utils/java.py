from typing import Dict, List


def get_java_standard_method_name(method_name: str, params: List[Dict[str, str]], return_type: str):
    return f'[{return_type}]' + method_name + \
        '(' + ','.join([param['type'] for param in params]) +  ')'
