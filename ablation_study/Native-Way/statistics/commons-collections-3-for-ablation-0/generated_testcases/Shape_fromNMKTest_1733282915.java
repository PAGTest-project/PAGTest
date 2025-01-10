
package org.apache.commons.collections4.bloomfilter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class Shape_fromNMKTest {

    @Test
    public void testFromNMK_ValidInput() {
        Shape shape = Shape.fromNMK(1000, 10000, 5);
        assertEquals(5, shape.getNumberOfHashFunctions());
        assertEquals(10000, shape.getNumberOfBits());
    }

    @Test
    public void testFromNMK_InvalidNumberOfItems() {
        assertThrows(IllegalArgumentException.class, () -> Shape.fromNMK(0, 10000, 5));
    }

    @Test
    public void testFromNMK_InvalidNumberOfBits() {
        assertThrows(IllegalArgumentException.class, () -> Shape.fromNMK(1000, 0, 5));
    }

    @Test
    public void testFromNMK_InvalidNumberOfHashFunctions() {
        assertThrows(IllegalArgumentException.class, () -> Shape.fromNMK(1000, 10000, 0));
    }

    @Test
    public void testFromNMK_InvalidProbability() {
        assertThrows(IllegalArgumentException.class, () -> Shape.fromNMK(1000, 10000, 1));
    }
}
