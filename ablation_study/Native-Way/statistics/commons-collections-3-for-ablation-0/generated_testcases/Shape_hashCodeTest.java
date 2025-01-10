
package org.apache.commons.collections4.bloomfilter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Shape_hashCodeTest {

    private Shape shape;

    @BeforeEach
    public void setUp() {
        shape = new Shape(5, 100);
    }

    @Test
    public void testHashCode() {
        int expectedHashCode = (31 + 100) * 31 + 5;
        assertEquals(expectedHashCode, shape.hashCode());
    }

    @Test
    public void testHashCodeWithDifferentValues() {
        Shape shape2 = new Shape(7, 150);
        int expectedHashCode2 = (31 + 150) * 31 + 7;
        assertEquals(expectedHashCode2, shape2.hashCode());
        assertNotEquals(shape.hashCode(), shape2.hashCode());
    }

    @Test
    public void testHashCodeWithSameValues() {
        Shape shape2 = new Shape(5, 100);
        assertEquals(shape.hashCode(), shape2.hashCode());
    }

    @Test
    public void testHashCodeWithZeroValues() {
        Shape shape2 = new Shape(0, 0);
        int expectedHashCode2 = (31 + 0) * 31 + 0;
        assertEquals(expectedHashCode2, shape2.hashCode());
    }

    @Test
    public void testHashCodeWithNegativeValues() {
        Shape shape2 = new Shape(-5, -100);
        int expectedHashCode2 = (31 + (-100)) * 31 + (-5);
        assertEquals(expectedHashCode2, shape2.hashCode());
    }
}
