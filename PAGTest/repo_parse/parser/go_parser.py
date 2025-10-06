# Copyright (c) Microsoft Corporation.
# Licensed under the MIT License.

"""
java_parser.py

This is the class which uses tree_sitter to parse Go files
into structural components defined by the source_parser schema,
the code is incomplete and is included as a placeholder.
"""

from typing import List, Dict, Any, Tuple, Union

from source_parser.parsers.language_parser import (
    LanguageParser,
    has_correct_syntax,
    children_of_type,
    children_not_of_type,
    previous_sibling,
)
from source_parser.parsers.commentutils import strip_c_style_comment_delimiters
from source_parser.utils import static_hash

from repo_parse.utils.data_processor import save_json


def match_from_span(node, blob: str) -> str:
    start = node.startIndex
    end = node.endIndex
    return blob[start:end]


class RefineGoParser(LanguageParser):

    FILTER_PATHS = ("test", "vendor")

    _method_types = (
        "method_declaration", 
        "function_declaration"
    )
    _struct_types = (
        "struct_type"
    )
    _interface_types = ("interface_type")
    _package_type = ("package_clause")
    _import_type = ("import_declaration")
    _docstring_types = ("comment", "line_comment", "block_comment")
    _include_patterns = "*?.go"


    @property
    def schema(self):
        return {
            "file_hash": static_hash(self.file_bytes),
            "file_docstring": self.file_docstring,
            "package": self.pack_node,
            "imports":self.file_imports,
            "gloable_vars": self.gloable_vars,
            "methods": [self._parse_method_node(c) for c in self.method_nodes],
            "structs": [self._parse_struct_node(c) for c in self.struct_nodes],
            "interfaces": [self._parse_interface_node(c) for c in self.interface_nodes],
        }
    
    @property
    def pack_node(self):
        self.span_select(self.tree.root_node.child_by_field_name(self._package_type), indent=False)
    
    def _parse_class_node(self, class_node) -> Dict[str, Union[str, List, Dict]]:
        """Implement this method following `parse_class_node`"""
        pass

    @property
    def class_types(self) -> Tuple[str]:
        """Tuple of class node type strings"""
        pass

    @property
    def file_context(self) -> List[str]:
        """List of global import and assignment statements"""
        pass

    @property
    def include_patterns(self):
        return self._include_patterns

    @staticmethod
    def get_definition(tree, blob: str) -> List[Dict[str, Any]]:
        definitions = []
        comment_buffer = []
        for child in tree.root_node.children:
            if child.type == "comment":
                comment_buffer.append(child)
            elif child.type in ("method_declaration", "function_declaration"):
                docstring = "\n".join(
                    [match_from_span(comment, blob) for comment in comment_buffer]
                )

                metadata = RefineGoParser.get_function_metadata(child, blob)
                definitions.append(
                    {
                        "type": child.type,
                        "identifier": metadata["identifier"],
                        "parameters": metadata["parameters"],
                        "function": match_from_span(child, blob),
                        "docstring": docstring,
                        # "start_point": child.start_point,
                        # "end_point": child.end_point,
                    }
                )
                comment_buffer = []
            else:
                comment_buffer = []
        return definitions

    @staticmethod
    def get_function_metadata(function_node, blob: str) -> Dict[str, str]:
        metadata = {
            "identifier": "",
            "parameters": "",
        }
        if function_node.type == "function_declaration":
            metadata["identifier"] = match_from_span(function_node.children[1], blob)
            metadata["parameters"] = match_from_span(function_node.children[2], blob)
        elif function_node.type == "method_declaration":
            metadata["identifier"] = match_from_span(function_node.children[2], blob)
            metadata["parameters"] = " ".join(
                [
                    match_from_span(function_node.children[1], blob),
                    match_from_span(function_node.children[3], blob),
                ]
            )
        return metadata

    @classmethod
    def get_lang(cls):
        return "go"

    @property
    def method_types(self):
        """Return method node types"""
        return self._method_types

    @property
    def struct_types(self):
        """Return struct node types"""
        return self._struct_types
    
    @property
    def interface_types(self):
        return self._interface_types

    @property
    def struct_nodes(self):
        """List of top-level child nodes corresponding to structs"""
        struct_nodes = []
        if not self.tree.root_node.children:
            return struct_nodes
        
        for child in self.tree.root_node.children:
            if child.type == "type_declaration":
                type_spec = child.children[1]
                struct_node = children_of_type(type_spec, self.struct_types)
                if not struct_node:
                    continue
                struct_nodes.append(type_spec)
        return struct_nodes
    
    @property
    def interface_nodes(self):
        """List of top-level child nodes corresponding to interfaces"""
        interface_nodes = []
        if not self.tree.root_node.children:
            return interface_nodes
        
        for child in self.tree.root_node.children:
            if child.type == "type_declaration":
                type_spec = child.children[1]
                interface_node = children_of_type(type_spec, self.interface_types)
                if not interface_node:
                    continue
                interface_nodes.append(type_spec)
        return interface_nodes

    @property
    def import_types(self):
        """Return import node types"""
        return self._import_type

    @property
    def file_docstring(self):
        """The first top-level single or multi-line comment in the file"""
        file_docstring = ""
        if not self.tree.root_node.children:
            return file_docstring
        first = self.tree.root_node.children[0]
        if first.children and first.children[0].type == "string":
            file_docstring = self.span_select(first.children[0])
        elif first.type == "comment":
            file_docstring = self.span_select(first)
        return file_docstring

    @property
    def gloable_vars(self) -> List[str]:
        """List of global import and assignment statements"""
        context = []
        for child in self.tree.root_node.children:
            if (
                child.type == "constant_declaration"
                or child.type == "var_declaration"
            ):
                context.append(self.span_select(child)) 
        return context 

    def _parse_method_node(self, method_node, parent_node=None):
        """Parse method nodes"""
        method_dict = {
            "syntax_pass": has_correct_syntax(method_node),
            "original_string": self.span_select(method_node),
        }

        # Parse the docstring
        comment_node = self._get_docstring_before(method_node, parent_node)
        method_dict["docstring"] = (
            strip_c_style_comment_delimiters(
                self.span_select(comment_node, indent=False)
            )
            if comment_node
            else ""
        )

        method_dict["name"] = self.span_select(
            method_node.child_by_field_name("name"),
            indent=False
        )

        # Parse parameters
        parameters_node = method_node.child_by_field_name("parameters")
        method_dict["params"] = self._parse_params(parameters_node) if parameters_node else []

        # Parse return values
        result_node = method_node.child_by_field_name("result")
        method_dict["return_types"] = self._parse_return_types(result_node) if result_node else []

        receiver = method_node.child_by_field_name("receiver")
        method_dict["receiver_type"] = self._parse_params(receiver) if receiver else []
        return method_dict

    def _parse_params(self, parameters_node):
        """Parse parameters, supporting grouped types and pointers."""
        params = []
        current_type = None

        for param_node in parameters_node.children:
            if param_node.type == "parameter_declaration":
                param_names = []

                # Collect all parameter names before the type
                for child in param_node.children:
                    if child.type == "identifier":
                        param_names.append(self.span_select(child, indent=False))
                    elif child.type in ["type_identifier", "pointer_type", "slice_type"]:
                        # Handle parameter type (including pointer types)
                        current_type = self._parse_type_node(child)

                # Map the type to each parameter name
                for param_name in param_names:
                    params.append({"name": param_name, "type": current_type})

            elif param_node.type in ["type_identifier", "pointer_type"]:
                # Support cases where type declarations appear after a group of parameters
                current_type = self._parse_type_node(param_node)

        return params

    def _parse_return_types(self, result_node):
        """Parse return types, supporting multiple return values."""
        # if not result_node.children:
        #     return [self.span_select(result_node, indent=False)]

        # closure
        if result_node.type == "function_type":
            return [self.span_select(result_node, indent=False)]
        
        if len(result_node.children) > 0:         
            return_types = []   
            for return_type_node in result_node.children:
                if return_type_node.type == "parameter_declaration":
                    # Parse each return type, which could be a pointer or a regular type
                    for child in return_type_node.children:
                        if child.type in ["type_identifier", "pointer_type"]:
                            return_types.append(self._parse_type_node(child))

            return return_types
        
        return [self.span_select(result_node, indent=False)]

    def _parse_type_node(self, type_node):
        """Recursively parse a type node, including handling pointer types."""
        # if type_node.type == "pointer_type":
        #     return self.span_select(type_node, indent=False)
        # elif type_node.type == "type_identifier":
        #     return self.span_select(type_node, indent=False)
        return self.span_select(type_node, indent=False)

    def _parse_struct_node(self, struct_node, parent_node=None):
        """Parse struct nodes"""
        struct_dict = {
            "original_string": "type " + self.span_select(struct_node, indent=False),
            "fields": [],
        }

        # Parse docstring
        docstring_node = self._get_docstring_before(struct_node, parent_node)
        struct_dict["docstring"] = (
            strip_c_style_comment_delimiters(self.span_select(docstring_node))
            if docstring_node
            else ""
        )

        # Parse struct name
        name_node = children_of_type(struct_node, "type_identifier")[0]
        struct_dict["name"] = (
            self.span_select(name_node, indent=False) if name_node else ""
        )

        struct_type = children_of_type(struct_node, "struct_type")[0]

        # Parse fields
        fields_node_list = children_of_type(struct_type, "field_declaration_list")[0]
        results = [self._parse_field_node(f) for f in fields_node_list.children if f.type == "field_declaration"]
        struct_dict["fields"] = [field[0] for field in results]

        return struct_dict

    def _parse_field_node(self, field_node):
        """Parse field nodes, supporting multiple fields sharing the same type and embedded fields."""
        field_names = []

        # Collect all field names (could be multiple)
        for child in field_node.children:
            if child.type == "field_identifier":
                field_names.append(self.span_select(child, indent=False))

        # Determine field type (also handling embedded fields)
        field_type_node = field_node.child_by_field_name("type")
        field_type = self.span_select(field_type_node, indent=False) if field_type_node else ""

        # Handle embedded fields (no explicit field name)
        if len(field_names) == 0 and field_type:
            return [{"name": field_type, "type": field_type, "embedded": True}]

        # Map type to each field name
        return [{"name": field_name, "type": field_type, "embedded": False} for field_name in field_names]

    def _parse_interface_node(self, interface_node, parent_node=None):
        """Parse interface nodes"""
        interface_dict = {
            "name": "",
            "original_string": self.span_select(interface_node),
            "methods": [],
            "inherits": [],
        }

        docstring_node = self._get_docstring_before(interface_node, parent_node)
        interface_dict["interface_docstring"] = (
            strip_c_style_comment_delimiters(self.span_select(docstring_node))
            if docstring_node
            else ""
        )

        name_node = children_of_type(interface_node, "type_identifier")[0]
        interface_dict["name"] = (
            self.span_select(name_node, indent=False) if name_node else ""
        )

        interface_type = children_of_type(interface_node, "interface_type")[0]
        for child in interface_type.children:
            if child.type == "type_elem":
                interface_dict["inherits"].append(self.span_select(child, indent=False))
            if child.type == "method_spec":
                interface_dict["methods"].append(
                    self._parse_interface_method_node(child)
                )

        return interface_dict
    
    def _parse_interface_method_node(self, method_node):
        """Parse method nodes"""
        method_dict = {
            "original_string": "type " + self.span_select(method_node, indent=False),
        }

        # Parse the docstring
        comment_node = self._get_docstring_before(method_node, method_node)
        method_dict["docstring"] = (
            strip_c_style_comment_delimiters(
                self.span_select(comment_node, indent=False)
            )
            if comment_node
            else ""
        )

        method_dict["name"] = self.span_select(
            method_node.child_by_field_name("name"),
            indent=False
        )

        # Parse parameters
        parameters_node = method_node.child_by_field_name("parameters")
        method_dict["params"] = self._parse_params(parameters_node) if parameters_node else []

        # Parse return values
        result_node = method_node.child_by_field_name("result")
        method_dict["return_types"] = self._parse_return_types(result_node) if result_node else []

        return method_dict


    def _get_docstring_before(self, node, parent_node=None):
        """
        Returns docstring node directly before 'node'.
        """
        prev_sib = previous_sibling(node, parent_node or self.tree.root_node)
        if prev_sib and prev_sib.type in self._docstring_types:
            return prev_sib
        return None


if __name__ == "__main__":
    file_path = r"C:\Users\v-zhanzhe\Desktop\code2\APT-master\APT-master\repo_parse\parser\example\example.go"
    parser = RefineGoParser()
    with open(file_path, 'r', encoding='utf-8') as f:
        file_contents = f.read()

    try:
        processed_contents = parser.preprocess_file(file_contents)
        parser.update(processed_contents)
        save_json(file_path=r'C:\Users\v-zhanzhe\Desktop\code2\APT-master\APT-master\repo_parse\parser\go_result.json', data=parser.schema)
    except Exception as e_err:
        print(f"\n\tFile {file_path} raised {type(e_err)}: {e_err}\n")