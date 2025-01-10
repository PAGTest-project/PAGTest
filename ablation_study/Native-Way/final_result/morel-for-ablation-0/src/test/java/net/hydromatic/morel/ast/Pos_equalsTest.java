
package net.hydromatic.morel.ast;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Pos_equalsTest {
    private Pos pos1;
    private Pos pos2;
    private Pos pos3;

    @BeforeEach
    public void setUp() {
        pos1 = new Pos("file1", 1, 1, 1, 5);
        pos2 = new Pos("file1", 1, 1, 1, 5);
        pos3 = new Pos("file2", 2, 2, 2, 6);
    }

    @Test
    public void testEqualsSameObject() {
        assertTrue(pos1.equals(pos1));
    }

    @Test
    public void testEqualsDifferentType() {
        assertFalse(pos1.equals("not a Pos object"));
    }

    @Test
    public void testEqualsSameValues() {
        assertTrue(pos1.equals(pos2));
    }

    @Test
    public void testEqualsDifferentValues() {
        assertFalse(pos1.equals(pos3));
    }

    @Test
    public void testEqualsNull() {
        assertFalse(pos1.equals(null));
    }
}
