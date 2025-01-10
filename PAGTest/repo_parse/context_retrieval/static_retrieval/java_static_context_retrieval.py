from typing import Dict, List, Set, Tuple
from repo_parse import config
from repo_parse.context_retrieval.static_retrieval.static_context_retrieval import StaticContextRetrieval
from repo_parse.metainfo.metainfo import MetaInfo
from repo_parse.metainfo.model import JavaClass
from repo_parse.scope_graph.build_scopes import build_scope_graph
from repo_parse import logger
from repo_parse.utils.data_processor import get_single_value_in_dict, load_file, load_json
from repo_parse.utils.java import get_java_standard_method_name


class JavaStaticContextRetrieval(MetaInfo, StaticContextRetrieval):
    def __init__(self):
        MetaInfo.__init__(self)
        StaticContextRetrieval.__init__(self, config.JAVA_STD_LIB_PATH, config.JAVA_KEYWORD_AND_BUILTIN_PATH)
        self.record_metainfo = load_json(config.RECORD_METAINFO_PATH)
        self.interface_metainfo = load_json(config.INTERFACE_METAINFO_PATH)
        self.packages_metainfo = load_json(config.PACKAGES_METAINFO_PATH)
        
    def load_std_lib(self) -> List[str]:
        pass

    def load_keywords_and_builtin(self):
        data = load_json(self.keywords_and_builtin_path)
        return data['keyword_and_builtin']
    
    def find_unresolved_refs(self, content: str):
        scope_graph = build_scope_graph(bytearray(content, encoding="utf-8"), language="java")
        unresolved_refs =  [ref.name for ref in scope_graph.unresolved_refs]
        unresolved_refs = list(set(unresolved_refs))
        return unresolved_refs
    
    def find_ref_in_package(self, current_file_path, unresolved_ref, original_string, use_dot=False):
        package = self.file_imports_metainfo[current_file_path][0]
        if package not in package:
            logger.warning(f"package not in file {current_file_path}.")
            return None

        all_files = self.packages_metainfo.get(package)
        if all_files is None:
            logger.warning(f"No files found in package {package}.")
            return None
        
        for file in all_files:
            class_name = file.split('/')[-1].split('.')[0]
            if class_name != unresolved_ref:
                continue
            class_uri = file + '.' + class_name
            _class = self.get_class(class_uri)
            if _class is not None:
                logger.info(f"Find class {unresolved_ref} in package.")
                if use_dot:
                    return self.process_method_and_field_invocations(original_string, class_name, file, _class)
                else:
                    return self.get_class_montage(_class)
        return None

    def pack_package_info(self, unresolved_refs, file_path, original_string):
        class_montages = []
        for unresolved_ref in unresolved_refs:
            class_montage = self.find_ref_in_package(file_path, unresolved_ref, original_string)
            if class_montage is not None:
                class_montages.append(class_montage)                
        return class_montages
    
    def pack_package_info_use_dot(self, unresolved_refs, file_path, original_string):
        res = []
        for unresolved_ref in unresolved_refs:
            info = self.find_ref_in_package(file_path, unresolved_ref, original_string, use_dot=True)
            if info:
                methods, fields = info
                if methods or fields:
                    res.append({'name': unresolved_ref, 'methods': methods, 'fields': fields})
        return res
    
    def pack_repo_info_use_dot(self, unresolved_refs, original_string, imports):
        res = []
        for unresolved_ref in unresolved_refs:
            info = self.find_ref_in_repo_use_dot(unresolved_ref, imports, original_string)
            if info:
                methods, fields = info
                if methods or fields:
                    res.append({'name': unresolved_ref, 'methods': methods, 'fields': fields})
        return res
    
    def pack_repo_info(self, unresolved_refs, imports):
        montages = []
        resolved_refs = set()
        for unresolved_ref in unresolved_refs:
            montage = self.find_ref_in_repo(unresolved_ref, imports)
            if montage is not None:
                montages.append(montage)    
                resolved_refs.add(unresolved_ref)            
        return montages, resolved_refs
    
    def find_ref_in_repo(self, unresolved_ref, imports):
        for _import in imports:
            if config.PACKAGE_PREFIX in _import:
                tokens = _import.rstrip(';').split(' ')[-1].split('.')
                class_name = tokens[-1]
                if class_name == unresolved_ref:
                    package_name = '.'.join(tokens[:-1])
                    _class = self.get_class_or_none(class_name, package_name)
                    if _class is not None:
                        logger.info(f"find_ref_in_repo: Find class {class_name} for target method.")
                        class_montage = self.get_class_montage(_class)
                        montage_description = self.pack_class_montage_description(class_montage)
                        return montage_description

                    interface = self.get_interface_or_none(class_name, package_name)
                    if interface is not None:
                        logger.info(f"find_ref_in_repo: Find interface {class_name} in for target method.")
                        interface_montage = self.get_interface_montage(interface)
                        montage_description = self.pack_interface_montage_description(interface_montage)
                        return montage_description
                    
                    abstract_class = self.get_abstractclass_or_none(class_name, package_name)
                    if abstract_class is not None:
                        logger.info(f"find_ref_in_repo: Find abstract class {class_name} in for target method.")
                        abstract_class_montage = self.get_abstractclass_montage(abstract_class)
                        montage_description = self.pack_abstractclass_montage_description(abstract_class_montage)
                        return montage_description

    def find_ref_in_repo_use_dot(self, unresolved_ref, imports, original_string):
        for _import in imports:
            if config.PACKAGE_PREFIX in _import:
                tokens = _import.rstrip(';').split(' ')[-1].split('.')
                class_name = tokens[-1]
                if class_name == unresolved_ref:
                    logger.info(f"Find class {class_name} in for target method.")
                    package_name = '.'.join(tokens[:-1])
                    _class = self.get_class_or_none(class_name, package_name)
                    if _class is not None:
                        methods, fields = self.process_method_and_field_invocations(
                            original_string=original_string, 
                            class_name=class_name, 
                            file_path=_class['file_path'], 
                            _class=_class)
                        return methods, fields        

    
    def find_file_level_context(self, code_block, where, language='java'):
        scope_graph = build_scope_graph(bytearray(code_block, encoding="utf-8"), language=language)
        unresolved_refs = set(scope_graph.unresolved_refs_name())
        if not unresolved_refs:
            return None, unresolved_refs
                
        finds = Finds()
        inherited_fields = self.get_inherited_fields(_class=where)
        if inherited_fields:
            logger.info(f"Find inherited fields for class {where['name']}.")
            where['fields'].extend(inherited_fields.values())
        
        field_finds, unresolved_refs = self.find_defs_in_fileds(unresolved_refs, where)
        if field_finds:
            finds.find_in_fileds = field_finds
            unresolved_refs.update(set(get_single_value_in_dict(t) for t in field_finds))
            for t in field_finds:
                self.find_in_repo(unresolved_ref=get_single_value_in_dict(t), 
                                  finds=finds, unresolveds=unresolved_refs)
                
            if not unresolved_refs:
                logger.info("All unresolved refs have been resolved.")
                
        if not unresolved_refs:
            return finds, []
        
        unresolveds= set(unresolved_refs)
        for unresolved_ref in unresolved_refs:
            self.find_in_repo(unresolved_ref, finds, unresolveds)
        
        return finds, unresolveds
    
    def find_defs_in_fileds(self, unresolved_refs: Set[str], fields: List[JavaClass]) -> Tuple[List[Dict[str, str]], Set]:
        findings = []
        unresolved = set(unresolved_refs)
        for ref in unresolved_refs:
            for field in fields['fields']:
                if field['name'] == ref:
                    findings.append({ref: field['type']})
                    unresolved.remove(ref)
                    logger.info(f"Find {ref} in class fields.")  
        return findings, unresolved
        
    
    def find_defs_in_global_variables(self):
        pass
    
    def find_defs_in_methods(self):
        pass
    
    def find_defs_in_classes(self):
        pass
    
    def pack_inherited_method_info(self, _class, inherited_method_info):
        return _class['original_string'].rstrip('}') + inherited_method_info + "}"
    
    def get_methods_original_string(self, class_methods_dict: Dict[str, List[str]]):
        original_string = ""
        for _class, methods in class_methods_dict.items():
            for method in self.method_metainfo:
                if method['class_name'] != _class:
                    continue

                if get_java_standard_method_name(
                    method_name=method['name'],
                    params=method['params'],
                    return_type=method['return_type']
                ) in methods:
                    original_string += method['original_string']
                    
        return original_string
    
    def get_inherited_fields(self, _class: Dict) -> Dict[str, List[str]]:
        cls_name = _class['name']
        cls = self.class_map.get(cls_name)
        if not cls or not cls['superclasses']:
            return {}

        inherited_fields = {}

        def _collect_fields(current_class, child_fields):
            if current_class in inherited_fields:
                return

            parent_class = self.class_map.get(current_class)
            if parent_class is None:
                return

            parent_fields = set([field['name'] for field in parent_class['fields']])
            non_overridden_fields = parent_fields - set(child_fields)

            if non_overridden_fields:
                inherited_fields[current_class] = list(non_overridden_fields)

            for superclass in parent_class['superclasses']:
                if superclass:
                    parent_class_fields = set([field['name'] for field in parent_class['fields']])
                    _collect_fields(superclass, parent_class_fields)

        if cls['superclasses']:
            child_fields = set([field['name'] for field in cls['fields']])
            _collect_fields(cls['superclasses'][0], child_fields)

        return inherited_fields

    def get_inherited_methods(self, cls_name) -> Dict[str, List[str]]:
        cls = self.class_map.get(cls_name)
        if not cls or not cls['superclasses']:
            return {}

        inherited_methods = {}

        def _collect_methods(current_class, child_methods):
            if current_class in inherited_methods:
                return

            parent_class = self.class_map.get(current_class)
            if parent_class is None:
                return

            parent_methods = set(parent_class['methods'])
            non_overridden_methods = parent_methods - set(child_methods)

            if non_overridden_methods:
                inherited_methods[current_class] = list(non_overridden_methods)

            for superclass in parent_class['superclasses']:
                if superclass:
                    _collect_methods(superclass, parent_methods)

        if cls['superclasses']:
            _collect_methods(cls['superclasses'], cls['methods'])

        return inherited_methods
    
    def prune_class(self, _class, method_names=None, save_constructors=True):
        pruned_class = {
            "file_path": _class['file_path'],
            "name": _class['name'],
            "class_docstring": _class['class_docstring'],
            "methods": [],
            "fields": [
                field['attribute_expression'] for field in _class['fields']
            ],
            "inner_classes": None
        }

        method_names = [method.replace(' ', '') for method in method_names]
        for method in _class['methods']:
            method_name = method.replace(' ', '')
            if (
                method_name.split(']')[-1] in method_names or
                method.find('[]') != -1 # 所有的构造函数也需要加上，获取到[]，如果里面没东西，那就是构造函数
            ):
                method_uri = _class['file_path'] + '.' + _class['name'] + '.' + method
                method_model = self.get_method(method_uri)
                pruned_class['methods'].append(method_model)

        pruned_class['inner_classes'] = _class['attributes']
        
        return pruned_class
    
    def pack_pruned_class_description(self, pruned_class):
        content = '\n'.join(self.get_imports(file_path=pruned_class['file_path'])) + '\n'
        class_doc = "<class_doc>" + pruned_class['class_docstring'] + "</class_doc>" if pruned_class['class_docstring'] else ""
        fields_str = "<fields>" + '\n'.join(pruned_class['fields']) + "</fields>" if pruned_class['fields'] else ""
        content += (
            "<class_name>" + pruned_class['name'] + "</class_name>" + class_doc + fields_str
        )
        
        method_str = "\n<methods>\n"
        for method in pruned_class['methods']:
            method_str += method['original_string']
        method_str += "</methods>"
        content += method_str if pruned_class['methods'] else ""
        
        inner_class_str = "\n<inner_classes>\n"
        for inner_class in pruned_class['inner_classes']:
             inner_class_str += inner_class['original_string']
        inner_class_str += "</inner_classes>"
        content += inner_class_str if pruned_class['inner_classes'] else ""
        
        return content
            

    def pack_class_montage_description(self, class_montage):
        return (
            "<class_name>" + class_montage['class_name'] + "</class_name>" +
            "<methods_signature>" + '\n'.join(class_montage['methods_signature']) + "</methods_signature>" +
            "<fields>" + '\n'.join(class_montage['fields']) + "</fields>"
        )
    
    def pack_method_with_doc(self, method_sig, method_doc):
        return (
            "Method:\n" + method_doc + method_sig + "\n"
        )
    
    def pack_class_montage_description_with_doc(self, class_montage):
        """
        {
            "class_name": _class['name'],
            "class_doc": "",
            "methods_signature": [method_doc, method_signature],
            "fields": [field['attribute_expression'] for field in _class['fields']]
        }
        """
        class_doc = ""
        class_doc += "<class_doc>" + class_montage['class_doc'] + "</class_doc>" if class_montage['class_doc'] else ""

        fields = ""
        fields += "\n<fields>\n" + '\n'.join(class_montage['fields']) + "</fields>" if class_montage['fields'] else ""
        
        return (
            "\n<class_name>\n" + class_montage['class_name'] + "</class_name>" + class_doc +
            "\n<methods_signature>\n" + 
            '\n'.join([self.pack_method_with_doc(method[1], method[0]) for method in class_montage['methods_signature']]) +
            "</methods_signature>" + fields
        )

    def pack_method_class_info(self, method: Dict, is_montage=False, use_doc=False) -> str:
        context = ""
        class_uri = method['class_uri']

        _class = self.get_class(uri=class_uri)
        class_name = _class['name']

        if is_montage:
            context += f"The following is the montage of target method `{method['name']}` in the class `{class_name}`:"
            class_montage = self.get_class_montage(_class, use_doc=use_doc)
            if class_montage is None:
                logger.exception(f"No class montage for {class_uri}")

            if use_doc:
                context += self.pack_class_montage_description_with_doc(class_montage)
            else:
                context += self.pack_class_montage_description(class_montage)
        else:
            context += f"The following is the definition of class `{class_name}`:\n"
            context += _class['original_string']
        
        context += "\n"
        return context

    def pack_testcase_file_level_context(self, testcase: Dict):
        original_string = testcase['original_string']
        class_uri = testcase['class_uri']
        testclass = self.get_testclass(uri=class_uri)
        finds, unresolveds = self.find_file_level_context(original_string, testclass, 'java')
        if finds is not None:
            if finds.find_in_fileds:
                original_string += "\nThese are all the references in the same file that are involved in the test case:"
                original_string += str(finds.find_in_fileds)
            if finds.class_finds:
                original_string += "\nThe following are all the definitions of the referenced class:"
                original_string += str(finds.class_finds)
            if finds.record_finds:
                original_string += "\nThe following are all the definitions of the referenced record:"
                original_string += str(finds.record_finds)
            if finds.interface_finds:
                original_string += "\nThe following are all the definitions of the referenced interface:"
                original_string += str(finds.interface_finds)

        return original_string