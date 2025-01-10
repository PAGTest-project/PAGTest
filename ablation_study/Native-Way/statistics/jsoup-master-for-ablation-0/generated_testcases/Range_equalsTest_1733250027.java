
package org.jsoup.nodes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Range_equalsTest {
    private Range range1;
    private Range range2;
    private Range range3;

    @BeforeEach
    public void setUp() {
        Position start1 = new Position(1, 1, 1);
        Position end1 = new Position(2, 2, 2);
        range1 = new Range(start1, end1);

        Position start2 = new Position(1, 1, 1);
        Position end2 = new Position(2, 2, 2);
        range2 = new Range(start2, end2);

        Position start3 = new Position(3, 3, 3);
        Position end3 = new Position(4, 4, 4);
        range3 = new Range(start3, end3);
    }

    @Test
    public void testEqualsSameObject() {
        assertTrue(range1.equals(range1));
    }

    @Test
    public void testEqualsNull() {
        assertFalse(range1.equals(null));
    }

    @Test
    public void testEqualsDifferentClass() {
        assertFalse(range1.equals("not a Range"));
    }

    @Test
    public void testEqualsSameValues() {
        assertTrue(range1.equals(range2));
    }

    @Test
    public void testEqualsDifferentValues() {
        assertFalse(range1.equals(range3));
    }

    @Test
    public void testHashCodeConsistency() {
        assertEquals(range1.hashCode(), range2.hashCode());
    }

    @Test
    public void testHashCodeDifferentValues() {
        assertNotEquals(range1.hashCode(), range3.hashCode());
    }
}
