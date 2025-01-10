from repo_parse.scope_graph.utils import TextRange

epsilon = 0.1


class Scope:
    def __init__(self, range: TextRange, node_id: str):
        self.start, self.end = range.line_range()

        self.node_id = node_id
