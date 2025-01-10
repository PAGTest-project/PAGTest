g
package net.hydromatic.morel.ast;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Pos_plusTest {

    @Test
    void testPlus() {
        Pos pos1 = new Pos("file1", 1, 2, 3, 4);
        Pos pos2 = new Pos("file1", 0, 1, 4, 5);

        Pos result = pos1.plus(pos2);

        assertEquals("file1", result.file);
        assertEquals(0, result.startLine);
        assertEquals(1, result.startColumn);
        assertEquals(4, result.endLine);
        assertEquals(5, result.endColumn);
    }
}
