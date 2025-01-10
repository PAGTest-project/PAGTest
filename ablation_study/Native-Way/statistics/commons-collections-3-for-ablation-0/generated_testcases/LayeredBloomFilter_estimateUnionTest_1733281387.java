
package org.apache.commons.collections4.bloomfilter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LayeredBloomFilter_estimateUnionTest {
    private LayeredBloomFilter<BloomFilter> filter;
    private BloomFilter otherFilter;

    @BeforeEach
    public void setUp() {
        filter = new LayeredBloomFilter<>(new SimpleShape(10, 5), new SimpleLayerManager<>(new SimpleBloomFilter(new SimpleShape(10, 5))));
        otherFilter = new SimpleBloomFilter(new SimpleShape(10, 5));
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
