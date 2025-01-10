
package net.hydromatic.morel.ast;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class AstWriter_appendTest {

    @Test
    void testAppendWithParentheses() {
        AstWriter writer = new AstWriter();
        AstNode mockNode = new MockAstNode(Op.PLUS);

        writer.append(mockNode, 1, 0);

        assertEquals("(mock)", writer.toString());
    }

    @Test
    void testAppendWithoutParentheses() {
        AstWriter writer = new AstWriter();
        AstNode mockNode = new MockAstNode(Op.PLUS);

        writer.append(mockNode, 0, 0);

        assertEquals("mock", writer.toString());
    }

    private static class MockAstNode extends AstNode {
        private final Op op;

        MockAstNode(Op op) {
            this.op = op;
        }

        @Override
        public void unparse(AstWriter writer, int left, int right) {
            writer.append("mock");
        }

        @Override
        public Op getOp() {
            return op;
        }
    }
}
