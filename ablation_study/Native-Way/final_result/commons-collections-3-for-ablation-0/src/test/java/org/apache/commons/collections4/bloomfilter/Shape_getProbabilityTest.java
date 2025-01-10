
package org.apache.commons.collections4.bloomfilter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Shape_getProbabilityTest {

    private Shape shape;

    @BeforeEach
    public void setUp() {
        shape = Shape.fromKM(5, 1000);
    }

    @Test
    public void testGetProbabilityWithNegativeNumberOfItems() {
        assertThrows(IllegalArgumentException.class, () -> {
            shape.getProbability(-1);
        });
    }

    @Test
    public void testGetProbabilityWithZeroNumberOfItems() {
        assertEquals(0.0, shape.getProbability(0));
    }

    @Test
    public void testGetProbabilityWithValidNumberOfItems() {
        double probability = shape.getProbability(100);
        assertTrue(probability > 0.0 && probability < 1.0);
    }

    @Test
    public void testGetProbabilityWithMaxNumberOfItems() {
        double probability = shape.getProbability(1000);
        assertTrue(probability > 0.0 && probability < 1.0);
    }

    @Test
    public void testGetProbabilityWithLargeNumberOfItems() {
        double probability = shape.getProbability(5000);
        assertTrue(probability > 0.0 && probability < 1.0);
    }
}
