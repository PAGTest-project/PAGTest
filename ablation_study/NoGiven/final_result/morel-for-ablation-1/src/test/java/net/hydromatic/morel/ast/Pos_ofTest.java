
package net.hydromatic.morel.ast;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Pos_ofTest {

    @Test
    void testOf() {
        String ml = "line1\nline2\nline3";
        String file = "testFile";
        int startOffset = 6; // Start of "line2"
        int endOffset = 11;  // End of "line2"

        Pos result = Pos.of(ml, file, startOffset, endOffset);

        assertEquals(file, result.file);
        assertEquals(2, result.startLine);
        assertEquals(1, result.startColumn);
        assertEquals(2, result.endLine);
        assertEquals(6, result.endColumn);
    }
}
