
package net.hydromatic.morel.ast;

import org.apache.calcite.util.IntPair;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Pos_ofTest {

    @Test
    void testOf_SingleLine() {
        String ml = "abc\ndef\nghi";
        String file = "testFile";
        int startOffset = 4; // Position of 'd' in "def"
        int endOffset = 7;   // Position of 'f' in "def"

        Pos result = Pos.of(ml, file, startOffset, endOffset);

        assertEquals("testFile", result.file);
        assertEquals(2, result.startLine);
        assertEquals(2, result.startColumn);
        assertEquals(2, result.endLine);
        assertEquals(4, result.endColumn);
    }

    @Test
    void testOf_MultiLine() {
        String ml = "abc\ndef\nghi";
        String file = "testFile";
        int startOffset = 4; // Position of 'd' in "def"
        int endOffset = 11;  // Position of 'i' in "ghi"

        Pos result = Pos.of(ml, file, startOffset, endOffset);

        assertEquals("testFile", result.file);
        assertEquals(2, result.startLine);
        assertEquals(2, result.startColumn);
        assertEquals(3, result.endLine);
        assertEquals(3, result.endColumn);
    }

    @Test
    void testOf_SameLine() {
        String ml = "abcdefghi";
        String file = "testFile";
        int startOffset = 3; // Position of 'd' in "abcdefghi"
        int endOffset = 6;   // Position of 'g' in "abcdefghi"

        Pos result = Pos.of(ml, file, startOffset, endOffset);

        assertEquals("testFile", result.file);
        assertEquals(1, result.startLine);
        assertEquals(4, result.startColumn);
        assertEquals(1, result.endLine);
        assertEquals(7, result.endColumn);
    }

    @Test
    void testOf_InvalidOffset() {
        String ml = "abc\ndef\nghi";
        String file = "testFile";
        int startOffset = 20; // Invalid offset
        int endOffset = 25;   // Invalid offset

        assertThrows(IllegalArgumentException.class, () -> {
            Pos.of(ml, file, startOffset, endOffset);
        });
    }
}
