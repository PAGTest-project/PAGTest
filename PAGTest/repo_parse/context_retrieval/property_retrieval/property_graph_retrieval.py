from typing import Dict, List, Optional, Tuple

from repo_parse.config import BROTHER_RELATIONS_PATH, FUNC_RELATION_PATH, NODE_TO_TESTCASE_PATH, PROPERTY_GRAPH_PATH
from repo_parse.metainfo.metainfo import MetaInfo
from repo_parse.metainfo.model import MethodSignature, TestCase
from repo_parse.utils.data_processor import get_single_value_in_dict, load_json
from repo_parse import logger


class ContextType:
    function = "function"
    _class = "class"
    repo = "repo"
    
class ContextInfo:
    def __init__(self, relation = None, testcases: List[TestCase] = None):
        """
        relation:
            {
                "class_name": "Deduper",
                "relationship_type": "Dependency",
                "description": "The query method records keys of duplicates when the record_duplicates flag is set, which are then accessible through the keys_of_duplicates method.",
                "enhancers": [
                    "query"
                ],
                "enhanced_methods": [
                    "keys_of_duplicates"
                ]
            }
        """
        self.relation = relation
        self.testcases = testcases

class Context:
    def __init__(self, _from: str, to: str, what: str = '', 
                 content: Dict[str, ContextInfo] | ContextInfo = None) -> None:
        self._from = _from
        self.to = to
        self.what = what
        self.content = content

class PropertyContext:
    def __init__(self, 
                 func_level_context = None,
                 class_level_context = None,
                 repo_level_context = None):
        self.func_level_context = func_level_context
        self.class_level_context = class_level_context
        self.repo_level_context = repo_level_context


class PropertyGraphRetrieval(MetaInfo):
    def __init__(self,
                 node_to_testcase_path: str = NODE_TO_TESTCASE_PATH,
                 func_relation_path: str = FUNC_RELATION_PATH,
                 brother_relation_path: str = BROTHER_RELATIONS_PATH) -> None:
        MetaInfo.__init__(self)
        # self.node_to_testcase = load_json(node_to_testcase_path)
        self.node_to_testcase = []
        # self.func_relation = load_json(func_relation_path)
        self.func_relation = []
        self.brother_relation = load_json(brother_relation_path)
        
    def pack_func_property_context(self, method_signature: MethodSignature):
        pass

    def func_level_retrieval(self, func_uri: str):
        pass
    
    def repo_level_retrieval(self, func_uri: str):
        """
        Return a list of brother of the given function's class.
        """
        # 1. Find the related class.
        class_name, func_name = func_uri.split(".")
        if class_name not in self.brother_relation:
            return None

        return self.brother_relation[class_name]
    
    def class_level_retrieval(self, func_uri: str):
        """
        Return:
            {
                "methods_involved": [
                    "query",
                    "keys_of_duplicates"
                ],
                "relationship_type": "Dependency",
                "description": "The query method records keys of duplicates when the record_duplicates flag is set, which are then accessible through the keys_of_duplicates method.",
                "enhancers": [
                    "query"
                ],
                "enhanced_methods": [
                    "keys_of_duplicates"
                ]
            },
        """
        class_name, func_name = func_uri.split(".")
        results = []
        for info in self.func_relation:
            if info['class_name'] != class_name:
                continue
            relationships = info['relationships']
            for realtion in relationships:
                if func_name in realtion['enhanced_methods']:
                    results.append(realtion)
                    
        if not results:
            return None
        
        # Select at most two relationships.
        # TODO: Implement a better algorithm, such as ranking.
        return results[:2]

    def get_resource(self, func_uri: str) -> Dict[str, List[str]]:
        """
        Find the resource bound to this function, 
        currently only bound to Testcase
        func_uri: str, e.g. "Deduper.add"
        return: 
            {
                "FileWithContext": ["test_context"],
                "FileWithoutContext": ["test_context"]
            }
        """
        # Get exsitting testcases
        class_name, method_name = func_uri.split(".")
        _class = self.node_to_testcase.get(class_name)
        if _class is None:
            return None
        # 下面这个可能会返回None
        return _class.get(method_name)
    

class PythonPropertyGraphRetrieval(PropertyGraphRetrieval):
    def __init__(self) -> None:
        PropertyGraphRetrieval.__init__(self)
        
    def pack_func_property_context(self, method_signature: MethodSignature):
        pass
    
    def func_level_retrieval(self, func_uri: str):
        pass
    
    def repo_level_retrieval(self, func_uri: str):
        """
        Return a list of brother of the given function's class.
        """
        # 1. Find the related class.
        class_name, func_name = func_uri.split(".")
        if class_name not in self.brother_relation:
            return None

        return self.brother_relation[class_name]
    
    def class_level_retrieval(self, func_uri: str):
        """
        Return:
            {
                "methods_involved": [
                    "query",
                    "keys_of_duplicates"
                ],
                "relationship_type": "Dependency",
                "description": "The query method records keys of duplicates when the record_duplicates flag is set, which are then accessible through the keys_of_duplicates method.",
                "enhancers": [
                    "query"
                ],
                "enhanced_methods": [
                    "keys_of_duplicates"
                ]
            },
        """
        class_name, func_name = func_uri.split(".")
        results = []
        for info in self.func_relation:
            if info['class_name'] != class_name:
                continue
            relationships = info['relationships']
            for realtion in relationships:
                if func_name in realtion['enhanced_methods']:
                    results.append(realtion)
                    
        if not results:
            return None
        
        # Select at most two relationships.
        # TODO: Implement a better algorithm, such as ranking.
        return results[:2]

    def get_resource(self, func_uri: str) -> Dict[str, List[str]]:
        """
        Find the resource bound to this function, 
        currently only bound to Testcase
        func_uri: str, e.g. "Deduper.add"
        return: 
            {
                "FileWithContext": ["test_context"],
                "FileWithoutContext": ["test_context"]
            }
        """
        # Get exsitting testcases
        class_name, method_name = func_uri.split(".")
        _class = self.node_to_testcase.get(class_name)
        if _class is None:
            return None
        # 下面这个可能会返回None
        return _class.get(method_name)
    

class JavaPropertyGraphRetrieval(PropertyGraphRetrieval):
    def __init__(self) -> None:
        PropertyGraphRetrieval.__init__(self)
        
    def func_level_context_to_str(self, func_level_context: Context) -> str:
        context_str = ""
        if func_level_context is not None:
            context_str += (
                f"We found the following context for the function {func_level_context.to}: \n" + \
                f"It already has the following unit tests as a reference: \n" + \
                + '<Testcase>' + self.pack_testcases_original_string(
                   get_single_value_in_dict(func_level_context.content.testcases)) + '</Testcase>\n'
            )
        return context_str
    
    def class_level_context_to_str(self, class_level_context: Context) -> str:
        context_str = ""
        if class_level_context is not None:
            context_str += f"We found the following context for the function {class_level_context.to} in class level:\n"
            contents: Dict[str, ContextInfo] = class_level_context.content
            for enhancer, context_info in contents.items():
                relation = context_info.relation
                
                context_str += (
                    f"{enhancer} and {class_level_context.to} have a {relation['relationship_type']} relationship, "
                    f"described as {relation['description']} The existing test cases for {enhancer} may provide some reference:\n" + '<Testcase>'
                    f"{self.pack_testcases_original_string(get_single_value_in_dict(context_info.testcases))}" + '</Testcase>\n'
                )
        return context_str
    
    def repo_level_context_to_str(self, repo_level_context: Context) -> str:
        context_str = ""
        if repo_level_context is not None:
            pass
        
        return context_str
        
    def context_to_str(self, func_level_context: Context, class_level_context: Context, repo_level_context: Context) -> str:
        context_str = ""
        if func_level_context is not None:
            context_str += (
                f"\nWe found the following context for the function {func_level_context.to}: \n" + \
                f"It already has the following unit tests as a reference: \n" + \
                + '<Testcase>' + self.pack_testcases_original_string(
                   get_single_value_in_dict(func_level_context.content.testcases)) + '</Testcase>\n'
            )
        
        if class_level_context is not None:
            context_str += f"We found the following context for the function `{class_level_context.to}` in class level:\n"
            contents: Dict[str, ContextInfo] = class_level_context.content
            for enhancer, context_info in contents.items():
                relation = context_info.relation
                context_str += (
                    f"`{enhancer}` and `{class_level_context.to}` have a {relation['relationship_type']} relationship, "
                    f"described as `{relation['description']}` The existing test cases for `{enhancer}` may provide some reference:\n" + '<Testcase>\n'
                    f"{self.pack_testcases_original_string(get_single_value_in_dict(context_info.testcases))}" + '</Testcase>\n'
                )

                context_str += (
                    f"We also provide the whole class context for related testcases:\n"
                    f"{self.pack_testclass_and_imports_for_testcases(get_single_value_in_dict(context_info.testcases))}"
                )
        
        if repo_level_context is not None:
            pass
        
        return context_str

    def pack_func_property_context(self, method_signature: MethodSignature) -> Tuple[Context, Context, Context]:
        """这三个要不要放到一块？放到一块，根据已有的上下文，动态组装prompt吧！"""
        # TODO: 现在可能会出现一个类里面method重名的情况，这部分后面可以再优化
        file_path = method_signature.file_path
        class_name = method_signature.class_name
        method_name = method_signature.method_name
        
        # 1. Get the function level context.
        func_level_context = self.func_level_retrieval(file_path, class_name, method_name) 
        
        class_level_context = self.class_level_retrieval(file_path, class_name, method_name)

        repo_level_context = self.repo_level_retrieval(file_path, class_name, method_name)
        
        return self.context_to_str(func_level_context, class_level_context, repo_level_context)
        # return func_level_context, class_level_context, repo_level_context
        
    def func_level_retrieval(self, file_path: str, class_name: str, method_name: str) -> Context:
        related_testcases = self.node_to_testcase.get(class_name, {}).get(method_name)
        if related_testcases is None:
            return None
        
        logger.info(f"Found {len(related_testcases)} testcases for {method_name}")
        context_info = ContextInfo(testcases=related_testcases)
        context = Context(
            _from=ContextType.function,
            to=method_name,
            content=context_info,
        )
        return context

    def class_level_retrieval(self, file_path: str, class_name: str, method_name: str) -> Context:
        """Return the function having relation with the given function in the same class 
        and having exsiting testcases"""
        # 1. First, get all the function that has relation with the given function.
        relations = []
        for _class in self.func_relation:
            if _class['class_name'] != class_name:
                continue
            
            for relation in _class['relationships']:
                if method_name in relation['enhanced_methods']:
                    relations.append(relation)
        
        # 2. For each relation, make sure that the testcase exists.
        func_to_testcase: Dict[str, ContextInfo] = {}
        for relation in relations:
            enhancers = relation['enhancers']
            for enhancer in enhancers:
                related_testcases: List[str] = self.node_to_testcase.get(class_name, {}).get(enhancer)
                if related_testcases is None:
                    continue

                context_info = ContextInfo(
                    relation=relation,
                    testcases=related_testcases
                )
                func_to_testcase[enhancer] = context_info
        
        if not func_to_testcase:
            return None
        
        context = Context(
            _from=ContextType._class,
            to=method_name,
            content=func_to_testcase
        )
        return context
    
    def repo_level_retrieval(self, file_path: str, class_name: str, method_name: str):
        """ """
        # 1. Find the related class.
        class_relation = self.brother_relation.get(class_name)
        if class_relation is None:
            return None
        
        # TODO: Implement repo level retrieval.


if __name__ == "__main__":
    retrieval = JavaPropertyGraphRetrieval()
    context = retrieval.pack_func_property_context(
        method_signature=...
    )
    print(context)