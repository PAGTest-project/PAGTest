# Copyright (c) Microsoft Corporation.
# Licensed under the MIT License.

"""
java_parser.py

This is the class which uses tree_sitter to parse Java files
into structural components defined by the source_parser schema


java_parser language-specific output schema for classes:
[{
    'name': 'class name',
    'original_string': 'verbatim entire string of class',
    'body': 'verbatim string for class body',
    'class_docstring': 'comment preceeding class',
    'definition': 'signature and name and modifiers defining class',
    'syntax_pass': True/False,

    'attributes': {
                 'comments': ['list of comments that may appear in class modifiers'],
                 'marker_annotations': ['@marker1', '@marker2', ...],
                 'modifiers': 'verbatim modifiers',
                 'non_marker_annotations': ['list of public/private/static, any non-marker, non-comment'],

                 'fields': [{'comments': ['list of comments that appear in field modifiers'],
                             'docstring': 'comment preceeding field',
                             'marker_annotations': ['@marker1', '@marker2', ...],
                             'modifiers': '@marker1\n    public',
                             'name': 'field_name',
                             'non_marker_annotations': ['public', 'static, ...],
                             'attribute_expression': 'verbatim field string',
                             'syntax_pass': True/False,
                             'type': 'int, e.g.'}, ... ] # note that fields do not have 'attributes' key, unlike classes and methods
                'classes': [...], # nested classes
                },

    'methods': [{'attributes': {
                     'comments': ['list of comments that may appear in method modifiers'],
                     'marker_annotations': ['@marker1', '@marker2', ...],
                     'modifiers': 'verbatim modifiers',
                     'non_marker_annotations': ['list of public/private/static, any non-marker, non-comment'],
                     'return_type': 'return type',
                             },
               'body': 'method body, verbatim string',
               'docstring': 'comment preceeding method',
               'name': 'method name',
               'original_string': 'verbatim string of entire method',
               'signature': 'verbatim string of method signature',
               'syntax_pass': True/False},
               'classes': [...], # nested classes
              ... ],
}, ... ]

Does not handle:
 - comments within signatures/class definitions
 - comments within method/class bodies (other than javadoc)
 - comments between class definitions (other than javadoc)
These are generally included in the verbatim/original_string fields, but may be left out if between structures or if non-javadoc.

"""

from typing import List

from source_parser.parsers.language_parser import (
    LanguageParser,
    has_correct_syntax,
    children_of_type,
    children_not_of_type,
    previous_sibling,
)
from source_parser.parsers.commentutils import strip_c_style_comment_delimiters
from source_parser.utils import static_hash


class RefineJavaParser(LanguageParser):
    """
    Parser for Java source code structural feature extraction
    into the source_parser schema.
    """

    _method_types = (
        "constructor_declaration",
        "method_declaration",
    )
    _class_types = ("class_declaration",)
    _interface_types = ("interface_declaration",)
    _record_types = ("record_declaration",)
    _record_constructor_types = ("compact_constructor_declaration",)
    _import_types = (
        "import_declaration",
        "package_declaration",
    )
    _docstring_types = ("comment", "line_comment", "block_comment")
    _include_patterns = "*?.java"

    @property
    def schema(self):
        """
        The file-level components of the schema

        See the top-level README.md file for a detailed description
        of the schema contents
        """
        return {
            "file_hash": static_hash(self.file_bytes),
            "file_docstring": self.file_docstring,
            "contexts": self.file_context,
            "methods": [self.parse_method_node(c) for c in self.method_nodes],
            "classes": [self.parse_class_node(c) for c in self.class_nodes],
            "interfaces": [self.parse_interface_node(c) for c in self.interface_nodes],
            "records": [self.parse_record_node(c) for c in self.record_nodes],
        }

    @classmethod
    def get_lang(cls):
        return "java"

    @property
    def method_types(self):
        """Return method node types"""
        return self._method_types
    
    @property
    def record_constructor_types(self):
        return self._record_constructor_types

    @property
    def class_types(self):
        """Return class node types string"""
        return self._class_types
    
    @property
    def interface_types(self):
        """Return interface node types string"""
        return self._interface_types
    
    @property
    def interface_nodes(self):
        """
        List of top-level child nodes corresponding to classes.
        Expect that `self.parse_class_node` will be run on these.
        """
        return children_of_type(self.tree.root_node, self.interface_types)
    
    @property
    def record_types(self):
        """Return record node types string"""
        return self._record_types
    
    @property
    def record_nodes(self):
        """
        List of top-level child nodes corresponding to classes.
        Expect that `self.parse_class_node` will be run on these.
        """
        return children_of_type(self.tree.root_node, self.record_types)

    @property
    def import_types(self):
        """Return class node types string"""
        return self._import_types

    @property
    def include_patterns(self):
        return self._include_patterns

    @property
    def __file_docstring_nodes(self):
        """List of top-level child nodes corresponding to comments"""
        return children_of_type(self.tree.root_node, self._docstring_types)

    def _get_docstring_before(self, node, parent_node=None):
        """
        Returns docstring node directly before 'node'.

        If the previous sibling is not a docstring, returns None.
        """

        if parent_node is None:
            parent_node = self.tree.root_node

        prev_sib = previous_sibling(node, parent_node)
        if prev_sib is None:
            return None
        if prev_sib.type in self._docstring_types:
            return prev_sib
        return None

    @property
    def file_docstring(self):
        """The first top-level single or multi-line comment in the file that
        is not a class's javadoc. If the first non-javadoc comment is after
        a javadoc comment, i.e., between classes, it is ignored. (only considering
        comments at beginning of the file)"""

        class_comment_nodes = [
            self._get_docstring_before(c_node) for c_node in self.class_nodes
        ]

        if len(self.__file_docstring_nodes) > 0:
            if self.__file_docstring_nodes[0] not in class_comment_nodes:
                return strip_c_style_comment_delimiters(
                    self.span_select(self.__file_docstring_nodes[0])
                )
        return ""

    @property
    def file_context(self) -> List[str]:
        """List of global import and assignment statements"""

        # there are no global assignment statements in java
        file_context_nodes = children_of_type(self.tree.root_node, self._import_types)
        return [self.span_select(node) for node in file_context_nodes]

    def _parse_super_interfaces_list(self, extends_interfaces_node, parent_node=None):
        result = []
        for child in extends_interfaces_node[0].children:
            if child.type == "type_list":
                for node in child.children:
                    if node.type == "type_identifier":
                        result.append(self.span_select(node, indent=False))
                    elif node.type == "generic_type":
                        result.append(self.span_select(node, indent=False))
        return result

    def parse_interface_node(self, interface_node, parent_node=None):
        class_dict = {}
        attributes = {}

        class_dict = {
            "original_string": self.span_select(interface_node),
            "definition": self.span_select(*interface_node.children[:-1]),
        }

        # look for javadoc directly preceeding the class
        docstring_node = self._get_docstring_before(interface_node, parent_node)
        class_dict["interface_docstring"] = (
            strip_c_style_comment_delimiters(self.span_select(docstring_node))
            if docstring_node
            else ""
        )

        # examine child for name, attributes, functions, etc.
        modifiers_node_list = children_of_type(interface_node, "modifiers")
        modifiers_attributes = self._parse_modifiers_node_list(modifiers_node_list)
        attributes.update(modifiers_attributes)

        name_node = children_of_type(interface_node, "identifier")[0]
        class_dict["name"] = (
            self.span_select(name_node, indent=False) if name_node else ""
        )

        # For interface inherits: public interface MyInterface extends SomeOtherInterface, Hereo
        extends_interfaces_node = children_of_type(interface_node, "extends_interfaces")
        if len(extends_interfaces_node) == 0:
            extends_interfaces = []
        else: 
            extends_interfaces = self._parse_super_interfaces_list(extends_interfaces_node)
        class_dict['extends_interfaces'] = extends_interfaces

        body_node = interface_node.child_by_field_name("body")

        class_dict["attributes"] = attributes

        class_dict["syntax_pass"] = has_correct_syntax(interface_node)

        class_dict["methods"] = [
            self._parse_method_node(m, body_node)
            for m in children_of_type(body_node, self.method_types)
        ]

        return class_dict

    def parse_record_node(self, record_node, parent_node=None):
        attributes = {}

        record_dict = {
            "original_string": self.span_select(record_node),
            "name": "",
            "fields": [],
            "constructors": "",
            "methods": [],
        }

        # look for javadoc directly preceeding the class
        docstring_node = self._get_docstring_before(record_node, parent_node)
        record_dict["class_docstring"] = (
            strip_c_style_comment_delimiters(self.span_select(docstring_node))
            if docstring_node
            else ""
        )

        # examine child for name, attributes, functions, etc.
        modifiers_node_list = children_of_type(record_node, "modifiers")
        modifiers_attributes = self._parse_modifiers_node_list(modifiers_node_list)
        attributes.update(modifiers_attributes)

        name_node = children_of_type(record_node, "identifier")[0]
        record_dict["name"] = (
            self.span_select(name_node, indent=False) if name_node else ""
        )

        parameters_node_list = children_of_type(record_node, "formal_parameters")[0]
        record_dict["fields"] = [
            self._parse_param_node(p) for p in parameters_node_list.children if p.type == "formal_parameter"
        ]
        
        body_node = record_node.child_by_field_name("body")

        record_dict["attributes"] = attributes

        record_dict["syntax_pass"] = has_correct_syntax(record_node)

        record_dict['constructors'] = [
            self._parse_record_constructor_node(m, body_node)
            for m in children_of_type(body_node, self.record_constructor_types)
        ]

        record_dict["methods"] = [
            self._parse_method_node(m, body_node)
            for m in children_of_type(body_node, self.method_types)
        ]

        return record_dict
    
    def _parse_record_constructor_node(self, method_node, parent_node=None):

        assert method_node.type in self.record_constructor_types

        method_dict = {
            "syntax_pass": has_correct_syntax(method_node),
            "original_string": self.span_select(method_node),
            # "byte_span": (method_node.start_byte, method_node.end_byte),
            # "start_point": (self.starting_point + method_node.start_point[0], method_node.start_point[1]),
            # "end_point": (self.starting_point + method_node.end_point[0], method_node.end_point[1]),
        }

        comment_node = self._get_docstring_before(method_node, parent_node)
        method_dict["docstring"] = (
            strip_c_style_comment_delimiters(
                self.span_select(comment_node, indent=False)
            )
            if comment_node
            else ""
        )

        modifiers_node_list = children_of_type(method_node, "modifiers")
        modifiers_attributes = self._parse_modifiers_node_list(modifiers_node_list)
        method_dict["attributes"] = modifiers_attributes

        # type_node = method_node.child_by_field_name("type")
        # method_dict["attributes"]["return_type"] = (
        #     self.span_select(type_node, indent=False) if type_node else ""
        # )

        # name_node = children_of_type(method_node, "identifier")[0]
        # method_dict["name"] = (
        #     self.span_select(name_node, indent=False) if name_node else ""
        # )

        # body_node = method_node.child_by_field_name("body")
        # method_dict["body"] = (
        #     self.span_select(body_node) if body_node else ""
        # )

        return method_dict
    
    def _parse_param_node(self, param_node, parent_node=None):
        return {
            "name": self.span_select(param_node.child_by_field_name("name"), indent=False),
            "type": self.span_select(param_node.child_by_field_name("type"), indent=False),
        }

    def _parse_method_node(self, method_node, parent_node=None):
        """See LanguageParser.parse_method_node for documentation"""

        assert method_node.type in self.method_types

        method_dict = {
            "syntax_pass": has_correct_syntax(method_node),
            "original_string": self.span_select(method_node),
            # "byte_span": (method_node.start_byte, method_node.end_byte),
            # "start_point": (self.starting_point + method_node.start_point[0], method_node.start_point[1]),
            # "end_point": (self.starting_point + method_node.end_point[0], method_node.end_point[1]),
        }

        comment_node = self._get_docstring_before(method_node, parent_node)
        method_dict["docstring"] = (
            strip_c_style_comment_delimiters(
                self.span_select(comment_node, indent=False)
            )
            if comment_node
            else ""
        )

        modifiers_node_list = children_of_type(method_node, "modifiers")
        modifiers_attributes = self._parse_modifiers_node_list(modifiers_node_list)
        method_dict["attributes"] = modifiers_attributes

        type_node = method_node.child_by_field_name("type")
        method_dict["attributes"]["return_type"] = (
            self.span_select(type_node, indent=False) if type_node else ""
        )

        name_node = children_of_type(method_node, "identifier")[0]
        method_dict["name"] = (
            self.span_select(name_node, indent=False) if name_node else ""
        )

        parameters_node_list = children_of_type(method_node, "formal_parameters")[0]
        method_dict["params"] = [
            self._parse_param_node(p) for p in parameters_node_list.children if p.type == "formal_parameter"
        ]

        body_node = method_node.child_by_field_name("body")
        method_dict["body"] = (
            self.span_select(body_node) if body_node else ""
        )

        method_dict["signature"] = self.span_select(
            method_node, children_of_type(method_node, "formal_parameters")[0],
            indent=False
        )

        # get nested classes
        classes = (
            [
                self._parse_class_node(c, body_node)
                for c in children_of_type(body_node, "class_declaration")
            ]
            if body_node
            else []
        )
        method_dict["attributes"]["classes"] = classes

        return method_dict

    def _parse_modifiers_node_list(self, modifiers_node_list):
        attributes = {}
        if len(modifiers_node_list) > 0:  # there should never be more than 1
            modifiers_node = modifiers_node_list[0]
            attributes["modifiers"] = self.span_select(modifiers_node, indent=False)
            attributes["marker_annotations"] = [
                self.span_select(m, indent=False)
                for m in children_of_type(modifiers_node, "marker_annotation")
            ]
            attributes["non_marker_annotations"] = self.select(
                children_not_of_type(
                    modifiers_node, ["marker_annotation",] + list(self._docstring_types)
                ),
                indent=False,
            )  # also not comments
            attributes["comments"] = self.select(
                children_of_type(modifiers_node, self._docstring_types), indent=False
            )
        else:
            attributes["modifiers"] = ""
            attributes["marker_annotations"] = []
            attributes["non_marker_annotations"] = []
            attributes["comments"] = []
        return attributes

    def _parse_superclass_node(self, superclass_node):
        superclass = children_of_type(superclass_node, "type_identifier")
        return self.span_select(superclass[0], indent=False) if superclass else ""

    def _parse_class_node(self, class_node, parent_node=None):
        class_dict = {}
        attributes = {}

        class_dict = {
            "original_string": self.span_select(class_node),
            "definition": self.span_select(*class_node.children[:-1]),
            # "byte_span": (class_node.start_byte, class_node.end_byte),
            # "start_point": (self.starting_point + class_node.start_point[0], class_node.start_point[1]),
            # "end_point": (self.starting_point + class_node.end_point[0], class_node.end_point[1])
        }

        # look for javadoc directly preceeding the class
        docstring_node = self._get_docstring_before(class_node, parent_node)
        class_dict["class_docstring"] = (
            strip_c_style_comment_delimiters(self.span_select(docstring_node))
            if docstring_node
            else ""
        )

        # examine child for name, attributes, functions, etc.
        modifiers_node_list = children_of_type(class_node, "modifiers")
        modifiers_attributes = self._parse_modifiers_node_list(modifiers_node_list)
        attributes.update(modifiers_attributes)

        name_node = children_of_type(class_node, "identifier")[0]
        class_dict["name"] = (
            self.span_select(name_node, indent=False) if name_node else ""
        )

        # For case: class UserRestClient implements DDD, EEEE
        super_interfaces_node_list = children_of_type(class_node, "super_interfaces")
        if len(super_interfaces_node_list) == 0:
            class_dict["super_interfaces"] = []
        else:
            super_interfaces = self._parse_super_interfaces_list(super_interfaces_node_list)
            class_dict["super_interfaces"] = super_interfaces

        # For case: class UserRestClient extends AAA
        superclass_node_list = children_of_type(class_node, "superclass")
        if len(superclass_node_list) == 0:
            class_dict["superclasses"] = ""
        else:
            class_dict["superclasses"] = self._parse_superclass_node(superclass_node_list[0])


        body_node = class_node.child_by_field_name("body")

        fields = []
        for f in children_of_type(body_node, "field_declaration"):
            field_dict = {}

            field_dict["attribute_expression"] = self.span_select(f, indent=False)

            comment_node = self._get_docstring_before(f, body_node)
            field_dict["docstring"] = (
                strip_c_style_comment_delimiters(
                    self.span_select(comment_node, indent=False)
                )
                if comment_node
                else ""
            )

            modifiers_node_list = children_of_type(f, "modifiers")
            modifiers_attributes = self._parse_modifiers_node_list(modifiers_node_list)
            field_dict.update(modifiers_attributes)

            type_node = f.child_by_field_name("type")
            field_dict["type"] = self.span_select(type_node, indent=False)

            declarator_node = f.child_by_field_name("declarator")
            field_dict["name"] = (
                self.span_select(declarator_node, indent=False)
                if declarator_node
                else ""
            )
            # not sure what a variable_declarator is vs the name identifier

            field_dict["syntax_pass"] = has_correct_syntax(f)

            fields.append(field_dict)
        attributes["fields"] = fields

        # get nested classes
        classes = (
            [
                self._parse_class_node(c, body_node)
                for c in children_of_type(body_node, "class_declaration")
            ]
            if body_node
            else []
        )
        attributes["classes"] = classes

        class_dict["attributes"] = attributes

        class_dict["syntax_pass"] = has_correct_syntax(class_node)

        class_dict["methods"] = [
            self._parse_method_node(m, body_node)
            for m in children_of_type(body_node, self.method_types)
        ]

        return class_dict
