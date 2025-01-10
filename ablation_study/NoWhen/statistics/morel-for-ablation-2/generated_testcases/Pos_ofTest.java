
package net.hydromatic.morel.ast;

import org.apache.calcite.util.Pair;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Pos_ofTest {

    @Test
    void testOf_SingleLine() {
        String ml = "abcdefgh";
        String file = "stdIn";
        int startOffset = 3;
        int endOffset = 6;

        Pos result = Pos.of(ml, file, startOffset, endOffset);

        assertEquals("stdIn", result.file);
        assertEquals(1, result.startLine);
        assertEquals(4, result.startColumn);
        assertEquals(1, result.endLine);
        assertEquals(7, result.endColumn);
    }

    @Test
    void testOf_MultiLine() {
        String ml = "abc\ndef\nghi";
        String file = "stdIn";
        int startOffset = 4;
        int endOffset = 8;

        Pos result = Pos.of(ml, file, startOffset, endOffset);

        assertEquals("stdIn", result.file);
        assertEquals(2, result.startLine);
        assertEquals(1, result.startColumn);
        assertEquals(3, result.endLine);
        assertEquals(2, result.endColumn);
    }

    @Test
    void testOf_StartAtBeginning() {
        String ml = "abcdefgh";
        String file = "stdIn";
        int startOffset = 0;
        int endOffset = 3;

        Pos result = Pos.of(ml, file, startOffset, endOffset);

        assertEquals("stdIn", result.file);
        assertEquals(1, result.startLine);
        assertEquals(1, result.startColumn);
        assertEquals(1, result.endLine);
        assertEquals(4, result.endColumn);
    }

    @Test
    void testOf_EndAtEnd() {
        String ml = "abcdefgh";
        String file = "stdIn";
        int startOffset = 5;
        int endOffset = 8;

        Pos result = Pos.of(ml, file, startOffset, endOffset);

        assertEquals("stdIn", result.file);
        assertEquals(1, result.startLine);
        assertEquals(6, result.startColumn);
        assertEquals(1, result.endLine);
        assertEquals(9, result.endColumn);
    }

    @Test
    void testOf_InvalidOffset() {
        String ml = "abcdefgh";
        String file = "stdIn";
        int startOffset = 10;
        int endOffset = 15;

        assertThrows(IllegalArgumentException.class, () -> {
            Pos.of(ml, file, startOffset, endOffset);
        });
    }
}
