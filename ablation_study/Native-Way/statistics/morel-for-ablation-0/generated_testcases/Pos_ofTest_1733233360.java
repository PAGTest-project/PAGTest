
package net.hydromatic.morel.ast;

import org.apache.calcite.util.IntPair;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Pos_ofTest {

    @Test
    public void testOf_SingleLine() {
        String ml = "abc\ndef\nghi";
        String file = "testFile";
        int startOffset = 4; // 'd' in "def"
        int endOffset = 7;   // 'f' in "def"

        Pos pos = Pos.of(ml, file, startOffset, endOffset);

        assertEquals("testFile", pos.file);
        assertEquals(2, pos.startLine);
        assertEquals(2, pos.startColumn);
        assertEquals(2, pos.endLine);
        assertEquals(4, pos.endColumn);
    }

    @Test
    public void testOf_MultiLine() {
        String ml = "abc\ndef\nghi";
        String file = "testFile";
        int startOffset = 4; // 'd' in "def"
        int endOffset = 11;  // 'i' in "ghi"

        Pos pos = Pos.of(ml, file, startOffset, endOffset);

        assertEquals("testFile", pos.file);
        assertEquals(2, pos.startLine);
        assertEquals(2, pos.startColumn);
        assertEquals(3, pos.endLine);
        assertEquals(2, pos.endColumn);
    }

    @Test
    public void testOf_SameLine() {
        String ml = "abc\ndef\nghi";
        String file = "testFile";
        int startOffset = 0; // 'a' in "abc"
        int endOffset = 2;   // 'c' in "abc"

        Pos pos = Pos.of(ml, file, startOffset, endOffset);

        assertEquals("testFile", pos.file);
        assertEquals(1, pos.startLine);
        assertEquals(1, pos.startColumn);
        assertEquals(1, pos.endLine);
        assertEquals(3, pos.endColumn);
    }

    @Test
    public void testOf_InvalidOffset() {
        String ml = "abc\ndef\nghi";
        String file = "testFile";
        int startOffset = 15; // Out of bounds
        int endOffset = 20;   // Out of bounds

        assertThrows(IllegalArgumentException.class, () -> {
            Pos.of(ml, file, startOffset, endOffset);
        });
    }

    @Test
    public void testOf_EmptyString() {
        String ml = "";
        String file = "testFile";
        int startOffset = 0;
        int endOffset = 0;

        Pos pos = Pos.of(ml, file, startOffset, endOffset);

        assertEquals("testFile", pos.file);
        assertEquals(1, pos.startLine);
        assertEquals(1, pos.startColumn);
        assertEquals(1, pos.endLine);
        assertEquals(1, pos.endColumn);
    }
}
