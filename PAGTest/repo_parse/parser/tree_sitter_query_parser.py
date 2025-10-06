import warnings
from tree_sitter_languages import get_language, get_parser  # noqa: E402

warnings.filterwarnings("ignore", category=FutureWarning)

def build_query(query_str, old, new):
    return query_str.replace(old, new)

def extract_identifiers(original_string, invoker_name):
    language = get_language("java")
    parser = get_parser("java")
    scm_fname = "/home/zhangzhe/APT/repo_parse/parser/queries/java-ref-resolution.scm"
        
    with open (scm_fname, "r") as f:
        query_str = f.read()
        
    query_str = build_query(query_str, r"FieldAccessName", invoker_name)
    query_str = build_query(query_str, r"ClassName", invoker_name)

    tree = parser.parse(bytes(original_string, "utf-8"))
    # Run the tags queries
    query = language.query(query_str)
    captures = query.captures(tree.root_node)
    captures = list(captures)
        
    fields = []
    methods_invoked = []
    for node, tag in captures:        
        if tag.startswith("field.access.identifier"):
            # print(node.text.decode("utf-8"))
            fields.append(node.text.decode("utf-8"))
        if tag.startswith("method.invocation.identifier"):
            # print(node.text.decode("utf-8"))
            methods_invoked.append(node.text.decode("utf-8"))

    return methods_invoked, fields