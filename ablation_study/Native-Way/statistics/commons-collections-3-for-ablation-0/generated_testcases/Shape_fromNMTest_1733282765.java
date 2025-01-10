
package org.apache.commons.collections4.bloomfilter;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Shape_fromNMTest {

    @Test
    public void testFromNM_ValidParameters() {
        Shape shape = Shape.fromNM(1000, 10000);
        assertNotNull(shape);
        assertEquals(7, shape.getNumberOfHashFunctions());
        assertEquals(10000, shape.getNumberOfBits());
    }

    @Test
    public void testFromNM_InvalidNumberOfItems() {
        assertThrows(IllegalArgumentException.class, () -> Shape.fromNM(0, 10000));
    }

    @Test
    public void testFromNM_InvalidNumberOfBits() {
        assertThrows(IllegalArgumentException.class, () -> Shape.fromNM(1000, 0));
    }

    @Test
    public void testFromNM_InvalidProbability() {
        assertThrows(IllegalArgumentException.class, () -> Shape.fromNM(1, 1));
    }

    @Test
    public void testFromNM_MaxValues() {
        Shape shape = Shape.fromNM(Integer.MAX_VALUE, Integer.MAX_VALUE);
        assertNotNull(shape);
        assertEquals(1, shape.getNumberOfHashFunctions());
        assertEquals(Integer.MAX_VALUE, shape.getNumberOfBits());
    }

    @Test
    public void testFromNM_MinValues() {
        Shape shape = Shape.fromNM(1, 1);
        assertNotNull(shape);
        assertEquals(1, shape.getNumberOfHashFunctions());
        assertEquals(1, shape.getNumberOfBits());
    }
}
