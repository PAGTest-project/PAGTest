
package org.apache.commons.collections4.bloomfilter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Shape_isSparseTest {

    private Shape shape;

    @BeforeEach
    public void setUp() {
        shape = Shape.fromNMK(5, 24, 3);
    }

    @Test
    public void testIsSparse_SparseCardinality() {
        assertTrue(shape.isSparse(10));
    }

    @Test
    public void testIsSparse_NonSparseCardinality() {
        assertFalse(shape.isSparse(50));
    }

    @Test
    public void testIsSparse_BoundaryCardinality() {
        assertTrue(shape.isSparse(48));
        assertFalse(shape.isSparse(49));
    }

    @Test
    public void testIsSparse_ZeroCardinality() {
        assertTrue(shape.isSparse(0));
    }

    @Test
    public void testIsSparse_MaxCardinality() {
        assertFalse(shape.isSparse(Integer.MAX_VALUE));
    }
}
