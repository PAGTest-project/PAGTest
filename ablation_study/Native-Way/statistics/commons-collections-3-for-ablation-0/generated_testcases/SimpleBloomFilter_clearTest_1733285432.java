
package org.apache.commons.collections4.bloomfilter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SimpleBloomFilter_clearTest {
    private SimpleBloomFilter filter;
    private Shape testShape;

    @BeforeEach
    public void setUp() {
        testShape = Shape.fromKM(1, 64); // Example shape for testing
        filter = new SimpleBloomFilter(testShape);
    }

    @Test
    public void testClear() {
        // Set some bits in the filter
        filter.merge((BitMapExtractor) predicate -> {
            predicate.test(0xFFFFFFFFFFFFFFFFL);
            return true;
        });
        assertEquals(64, filter.cardinality());

        // Clear the filter
        filter.clear();

        // Verify that the filter is empty
        assertTrue(filter.isEmpty());
        assertEquals(0, filter.cardinality());
    }

    @Test
    public void testClearAfterMerge() {
        // Merge some indices into the filter
        filter.merge((IndexExtractor) idx -> {
            if (idx >= 0 && idx < testShape.getNumberOfBits()) {
                return true;
            }
            return false;
        });
        assertEquals(1, filter.cardinality());

        // Clear the filter
        filter.clear();

        // Verify that the filter is empty
        assertTrue(filter.isEmpty());
        assertEquals(0, filter.cardinality());
    }
}
