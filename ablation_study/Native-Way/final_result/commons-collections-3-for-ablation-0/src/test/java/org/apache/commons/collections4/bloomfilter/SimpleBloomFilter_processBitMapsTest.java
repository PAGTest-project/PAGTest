
package org.apache.commons.collections4.bloomfilter;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SimpleBloomFilter_processBitMapsTest {
    private SimpleBloomFilter filter;

    @BeforeEach
    public void setUp() {
        filter = new SimpleBloomFilter(getTestShape());
    }

    private Shape getTestShape() {
        // Assuming a method to create a test shape
        return Shape.fromKM(1, 64); // Example shape
    }

    @Test
    public void testProcessBitMapsAllTrue() {
        filter.merge((BitMapExtractor) predicate -> {
            predicate.test(0xFFFFFFFFFFFFFFFFL);
            return true;
        });
        assertTrue(filter.processBitMaps(l -> l == 0xFFFFFFFFFFFFFFFFL));
    }

    @Test
    public void testProcessBitMapsOneFalse() {
        filter.merge((BitMapExtractor) predicate -> {
            predicate.test(0xFFFFFFFFFFFFFFFFL);
            return true;
        });
        assertFalse(filter.processBitMaps(l -> l == 0xFFFFFFFFFFFFFFFEL));
    }

    @Test
    public void testProcessBitMapsEmptyFilter() {
        assertTrue(filter.processBitMaps(l -> l == 0L));
    }

    @Test
    public void testProcessBitMapsNullConsumer() {
        assertThrows(NullPointerException.class, () -> filter.processBitMaps(null));
    }
}
