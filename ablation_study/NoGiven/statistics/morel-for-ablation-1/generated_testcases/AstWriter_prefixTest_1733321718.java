
package net.hydromatic.morel.ast;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AstWriter_prefixTest {

    @Test
    void testPrefixWithParenthesize() {
        StringBuilder sb = new StringBuilder();
        AstWriter writer = new AstWriter(sb, true);
        Op op = new Op();
        AstNode node = new AstNode();

        writer.prefix(0, op, node, 0);

        assertEquals("(op padded)", sb.toString());
    }

    @Test
    void testPrefixWithoutParenthesize() {
        StringBuilder sb = new StringBuilder();
        AstWriter writer = new AstWriter(sb, false);
        Op op = new Op();
        AstNode node = new AstNode();

        writer.prefix(0, op, node, 0);

        assertEquals("op padded", sb.toString());
    }

    private static class Op {
        String padded = "op padded";
        int left = 0;
        int right = 0;
    }

    private static class AstNode {
        Op op = new Op();

        void unparse(AstWriter writer, int left, int right) {
            writer.append(op.padded);
        }
    }
}
