
package org.apache.commons.collections4.bloomfilter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SimpleBloomFilter_processBitMapPairsTest {
    private SimpleBloomFilter filter;
    private BitMapExtractor bitMapExtractor;
    private LongBiPredicate func;

    @BeforeEach
    public void setUp() {
        filter = new SimpleBloomFilter(Shape.fromKM(1, 64));
        bitMapExtractor = new BitMapExtractor() {
            @Override
            public boolean processBitMaps(LongPredicate consumer) {
                return consumer.test(0x3L) && consumer.test(0x5L);
            }
        };
        func = (a, b) -> (a & b) != 0;
    }

    @Test
    public void testProcessBitMapPairsSuccess() {
        assertTrue(filter.processBitMapPairs(bitMapExtractor, func));
    }

    @Test
    public void testProcessBitMapPairsFailure() {
        bitMapExtractor = new BitMapExtractor() {
            @Override
            public boolean processBitMaps(LongPredicate consumer) {
                return consumer.test(0x3L) && !consumer.test(0x5L);
            }
        };
        assertFalse(filter.processBitMapPairs(bitMapExtractor, func));
    }

    @Test
    public void testProcessBitMapPairsProcessRemainingFailure() {
        func = (a, b) -> false;
        assertFalse(filter.processBitMapPairs(bitMapExtractor, func));
    }
}
