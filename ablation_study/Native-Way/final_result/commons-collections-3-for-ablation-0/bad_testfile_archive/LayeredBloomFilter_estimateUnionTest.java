
package org.apache.commons.collections4.bloomfilter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LayeredBloomFilter_estimateUnionTest {
    private LayeredBloomFilter<SimpleBloomFilter> filter;
    private SimpleBloomFilter otherFilter;

    @BeforeEach
    public void setUp() {
        Shape shape = new Shape(10, 5);
        filter = new LayeredBloomFilter<>(shape, new LayerManager<>(new SimpleBloomFilter(shape)));
        otherFilter = new SimpleBloomFilter(shape);
    }

    @Test
    public void testEstimateUnion() {
        filter.merge(TestingHashers.FROM1);
        otherFilter.merge(TestingHashers.FROM11);
        int expectedUnionEstimate = filter.flatten().estimateN() + otherFilter.estimateN();
        assertEquals(expectedUnionEstimate, filter.estimateUnion(otherFilter));
    }

    @Test
    public void testEstimateUnionWithNull() {
        assertThrows(NullPointerException.class, () -> filter.estimateUnion(null));
    }
}
