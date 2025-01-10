
package net.hydromatic.morel.ast;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Pos_equalsTest {

    @Test
    void testEquals() {
        Pos pos1 = new Pos("file1", 1, 2, 3, 4);
        Pos pos2 = new Pos("file1", 1, 2, 3, 4);
        Pos pos3 = new Pos("file2", 5, 6, 7, 8);

        // Test same object
        assertTrue(pos1.equals(pos1));

        // Test equal objects
        assertTrue(pos1.equals(pos2));
        assertTrue(pos2.equals(pos1));

        // Test different objects
        assertFalse(pos1.equals(pos3));
        assertFalse(pos3.equals(pos1));

        // Test null
        assertFalse(pos1.equals(null));

        // Test different class
        assertFalse(pos1.equals("not a Pos object"));
    }
}
