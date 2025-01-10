
package org.apache.commons.collections4.bloomfilter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Shape_estimateNTest {

    private Shape shape;

    @BeforeEach
    public void setUp() {
        shape = Shape.fromKM(3, 100);
    }

    @Test
    public void testEstimateN_ZeroCardinality() {
        assertEquals(0.0, shape.estimateN(0), 0.0001);
    }

    @Test
    public void testEstimateN_HalfCardinality() {
        assertEquals(69.3147, shape.estimateN(50), 0.0001);
    }

    @Test
    public void testEstimateN_FullCardinality() {
        assertEquals(Double.POSITIVE_INFINITY, shape.estimateN(100), 0.0001);
    }

    @Test
    public void testEstimateN_ExceedsCardinality() {
        assertTrue(Double.isNaN(shape.estimateN(101)));
    }

    @Test
    public void testEstimateN_DifferentShape() {
        Shape newShape = Shape.fromKM(5, 200);
        assertEquals(138.6294, newShape.estimateN(100), 0.0001);
    }
}
