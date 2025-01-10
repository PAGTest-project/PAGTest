
package org.apache.commons.collections4.bloomfilter;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SimpleBloomFilter_mergeTest {
    private SimpleBloomFilter filter;
    private Shape shape;

    @BeforeEach
    public void setUp() {
        shape = Shape.fromKM(1, 64); // Example shape with 1 hash function and 64 bits
        filter = new SimpleBloomFilter(shape);
    }

    @Test
    public void testMergeValidBitMapExtractor() {
        BitMapExtractor bitMapExtractor = p -> {
            p.test(0xFFFFFFFFFFFFFFFFL); // Set all bits in the first long
            return true;
        };
        assertTrue(filter.merge(bitMapExtractor));
        assertEquals(64, filter.cardinality());
    }

    @Test
    public void testMergeInvalidBitMapExtractor() {
        BitMapExtractor bitMapExtractor = p -> {
            p.test(0xFFFFFFFFFFFFFFFFL); // Set all bits in the first long
            p.test(0xFFFFFFFFFFFFFFFFL); // Set all bits in the second long, exceeding the limit
            return true;
        };
        assertThrows(IllegalArgumentException.class, () -> filter.merge(bitMapExtractor));
    }

    @Test
    public void testMergeBitMapExtractorWithExcessBits() {
        BitMapExtractor bitMapExtractor = p -> {
            p.test(0xFFFFFFFFFFFFFFFFL); // Set all bits in the first long
            p.test(0x1L << 64); // Set a bit higher than the limit for the shape
            return true;
        };
        assertThrows(IllegalArgumentException.class, () -> filter.merge(bitMapExtractor));
    }

    @Test
    public void testMergeBitMapExtractorWithIndexOutOfBounds() {
        BitMapExtractor bitMapExtractor = p -> {
            p.test(0xFFFFFFFFFFFFFFFFL); // Set all bits in the first long
            p.test(0xFFFFFFFFFFFFFFFFL); // Set all bits in the second long, exceeding the limit
            p.test(0xFFFFFFFFFFFFFFFFL); // This should throw IndexOutOfBoundsException
            return true;
        };
        assertThrows(IllegalArgumentException.class, () -> filter.merge(bitMapExtractor));
    }
}
