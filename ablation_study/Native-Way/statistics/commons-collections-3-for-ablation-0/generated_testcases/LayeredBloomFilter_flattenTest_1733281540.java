
package org.apache.commons.collections4.bloomfilter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.apache.commons.collections4.bloomfilter.LayerManager.Cleanup;
import org.apache.commons.collections4.bloomfilter.LayerManager.ExtendCheck;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LayeredBloomFilter_flattenTest {

    private LayeredBloomFilter underTest;
    private LayerManager layerManager;

    @BeforeEach
    public void setUp() {
        final int[] sequence = {1};
        layerManager = LayerManager.builder()
                .setSupplier(() -> new NumberedBloomFilter(getTestShape(), 3, sequence[0]++))
                .setExtendCheck(ExtendCheck.neverAdvance())
                .setCleanup(ll -> ll.removeIf(f -> (((NumberedBloomFilter) f).value-- == 0))).get();
        underTest = new LayeredBloomFilter(getTestShape(), layerManager);
    }

    @Test
    public void testFlatten() {
        // Merge some BloomFilters into the LayeredBloomFilter
        underTest.merge(TestingHashers.randomHasher());
        underTest.next();
        underTest.merge(TestingHashers.randomHasher());

        // Flatten the LayeredBloomFilter
        BloomFilter flattened = underTest.flatten();

        // Verify that the flattened BloomFilter is not null and is a SimpleBloomFilter
        assertTrue(flattened instanceof SimpleBloomFilter);

        // Verify that the flattened BloomFilter has the correct shape
        assertEquals(getTestShape(), flattened.getShape());

        // Verify that the flattened BloomFilter has the correct cardinality
        assertEquals(underTest.estimateN(), flattened.estimateN());
    }

    private Shape getTestShape() {
        return Shape.fromNM(100, 1000);
    }
}
