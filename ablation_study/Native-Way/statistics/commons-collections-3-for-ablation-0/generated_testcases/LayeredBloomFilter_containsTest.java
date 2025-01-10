
package org.apache.commons.collections4.bloomfilter;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.function.Predicate;

public class LayeredBloomFilter_containsTest {
    private LayeredBloomFilter<SimpleBloomFilter> layeredBloomFilter;
    private Shape shape;
    private LayerManager<SimpleBloomFilter> layerManager;

    @BeforeEach
    public void setUp() {
        shape = Shape.fromNM(4, 64);
        layerManager = new LayerManager<>(shape, SimpleBloomFilter::new);
        layeredBloomFilter = new LayeredBloomFilter<>(shape, layerManager);
    }

    @Test
    public void testContainsWithBloomFilterExtractor() {
        BloomFilterExtractor extractor = new BloomFilterExtractor(shape) {
            @Override
            public boolean processBloomFilters(Predicate<BloomFilter> predicate) {
                return true;
            }
        };
        assertTrue(layeredBloomFilter.contains(extractor));
    }

    @Test
    public void testContainsWithNonBloomFilterExtractor() {
        SimpleBloomFilter other = new SimpleBloomFilter(shape);
        assertFalse(layeredBloomFilter.contains(other));
    }

    @Test
    public void testContainsWithBitMapExtractor() {
        BitMapExtractor bitMapExtractor = new BitMapExtractor(shape) {
            @Override
            public void merge(BloomFilter bf) {
            }
        };
        assertTrue(layeredBloomFilter.contains(bitMapExtractor));
    }

    @Test
    public void testContainsWithHasher() {
        Hasher hasher = new Hasher(shape) {
            @Override
            public long[] getBits(int[] hashFunctions, int m) {
                return new long[0];
            }
        };
        assertTrue(layeredBloomFilter.contains(hasher));
    }

    @Test
    public void testContainsWithIndexExtractor() {
        IndexExtractor indexExtractor = new IndexExtractor(shape) {
            @Override
            public int[] getBits(int[] hashFunctions, int m) {
                return new int[0];
            }
        };
        assertTrue(layeredBloomFilter.contains(indexExtractor));
    }

    @Test
    public void testProcessBloomFilters() {
        Predicate<BloomFilter> predicate = BloomFilter::isEmpty;
        assertTrue(layeredBloomFilter.processBloomFilters(predicate));
    }

    @Test
    public void testMerge() {
        SimpleBloomFilter filter = new SimpleBloomFilter(shape);
        assertTrue(layeredBloomFilter.merge(filter));
    }

    @Test
    public void testFlatten() {
        BloomFilter flattened = layeredBloomFilter.flatten();
        assertNotNull(flattened);
    }

    @Test
    public void testEstimateN() {
        int estimate = layeredBloomFilter.estimateN();
        assertEquals(0, estimate);
    }

    @Test
    public void testIsEmpty() {
        assertTrue(layeredBloomFilter.isEmpty());
    }
}
