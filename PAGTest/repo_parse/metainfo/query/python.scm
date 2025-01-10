(
module 
(expression_statement
(assignment
[
left: (identifier) @global_expression.assignment.single
left: (pattern_list) @global_expression.assignment.multiple
]
) @global_expression.assignment
) @global_expression
)


(
module 
[
  (import_from_statement
    [
    module_name: (dotted_name) @global_import_from.module_name
    name: (dotted_name) @global_import_from.name
    name: (aliased_import
     name: (dotted_name) @global_import_from.aliased_import.name
     alias: (identifier) @global_import_from.aliased_import.alias
    ) @global_import_from.aliased_import
    (wildcard_import) @global_import_from.wildcard_import
    ]
  ) @global_import_from

  (import_statement
  [
    name: (dotted_name)@global_import.name
    name: (
      aliased_import
      name: (dotted_name) @global_import.aliased_import.name
      alias: (identifier) @global_import.aliased_import.alias
    ) @global_import.aliased_import
  ]
  ) @global_import
]
)