(method_invocation
 (identifier) @method.invocation.identifier
 (#eq? @method.invocation.identifier "ClassName")
) @method.invocation

(field_access
    (identifier) @field.access.identifier
    (#eq? @field.access.identifier "FieldAccessName")
)@field.access