
package net.hydromatic.morel.parse;

import net.hydromatic.morel.ast.AstNode;
import net.hydromatic.morel.ast.Pos;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Span_posTest {

    @Test
    void testPosWithOnePosition() {
        Span span = Span.of(new Pos("file", 1, 1, 1, 1));
        Pos result = span.pos();
        assertEquals(new Pos("file", 1, 1, 1, 1), result);
    }

    @Test
    void testPosWithMultiplePositions() {
        Span span = Span.of();
        span.add(new Pos("file", 1, 1, 1, 1))
            .add(new Pos("file", 2, 2, 2, 2));
        Pos result = span.pos();
        assertEquals(Pos.sum(span.posList), result);
    }

    @Test
    void testPosWithNoPositions() {
        Span span = Span.of();
        assertThrows(AssertionError.class, span::pos);
    }
}
