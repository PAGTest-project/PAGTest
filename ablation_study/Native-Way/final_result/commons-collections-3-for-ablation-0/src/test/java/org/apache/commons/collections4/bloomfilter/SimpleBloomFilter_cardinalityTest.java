
package org.apache.commons.collections4.bloomfilter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SimpleBloomFilter_cardinalityTest {
    private SimpleBloomFilter filter;
    private Shape testShape;

    @BeforeEach
    public void setUp() {
        testShape = Shape.fromKM(1, 100); // Example shape
        filter = new SimpleBloomFilter(testShape);
    }

    @Test
    public void testCardinalityInitialState() {
        assertEquals(0, filter.cardinality());
    }

    @Test
    public void testCardinalityAfterMerge() {
        filter.merge((BitMapExtractor) predicate -> {
            predicate.test(2L);
            return true;
        });
        assertEquals(1, filter.cardinality());
    }

    @Test
    public void testCardinalityAfterMultipleMerges() {
        filter.merge((BitMapExtractor) predicate -> {
            predicate.test(2L);
            return true;
        });
        filter.merge((BitMapExtractor) predicate -> {
            predicate.test(4L);
            return true;
        });
        assertEquals(2, filter.cardinality());
    }

    @Test
    public void testCardinalityAfterClear() {
        filter.merge((BitMapExtractor) predicate -> {
            predicate.test(2L);
            return true;
        });
        filter.clear();
        assertEquals(0, filter.cardinality());
    }
}
