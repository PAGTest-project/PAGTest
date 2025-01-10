
package net.hydromatic.morel.ast;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AstWriter_appendAllTest {

    @Test
    void testAppendAll_SingleNode() {
        AstWriter writer = new AstWriter();
        AstNode node = new AstNode();
        writer.appendAll(Lists.newArrayList(node), 1, Op.PLUS, 1);
        assertEquals("node", writer.toString());
    }

    @Test
    void testAppendAll_MultipleNodes() {
        AstWriter writer = new AstWriter();
        AstNode node1 = new AstNode();
        AstNode node2 = new AstNode();
        writer.appendAll(Lists.newArrayList(node1, node2), 1, Op.PLUS, 1);
        assertEquals("node + node", writer.toString());
    }

    @Test
    void testAppendAll_EmptyList() {
        AstWriter writer = new AstWriter();
        writer.appendAll(Lists.newArrayList(), 1, Op.PLUS, 1);
        assertEquals("", writer.toString());
    }

    private static class AstNode {
        Op op = Op.PLUS;
        void unparse(AstWriter writer, int left, int right) {
            writer.append("node");
        }
    }

    private static class Op {
        static final Op PLUS = new Op();
        int left = 1;
        int right = 1;
        String padded = " + ";
    }
}
