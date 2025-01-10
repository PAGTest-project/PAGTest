
package org.apache.commons.collections4.bloomfilter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SetOperations_cardinalityTest {

    private BitMapExtractor bitMapExtractor;

    @BeforeEach
    public void setUp() {
        bitMapExtractor = BitMapExtractor.fromBitMapArray(0x3L, 0x5L);
    }

    @Test
    public void testCardinalityWithNonZeroBitCount() {
        int expectedCardinality = 4; // Bit count of 0x3L is 2, and bit count of 0x5L is 2, total is 4
        assertEquals(expectedCardinality, SetOperations.cardinality(bitMapExtractor));
    }

    @Test
    public void testCardinalityWithZeroBitCount() {
        bitMapExtractor = BitMapExtractor.fromBitMapArray(0x0L);
        int expectedCardinality = 0; // Bit count of 0x0L is 0
        assertEquals(expectedCardinality, SetOperations.cardinality(bitMapExtractor));
    }

    @Test
    public void testCardinalityWithMixedBitCounts() {
        bitMapExtractor = BitMapExtractor.fromBitMapArray(0x3L, 0x0L, 0x5L);
        int expectedCardinality = 4; // Bit count of 0x3L is 2, 0x0L is 0, and 0x5L is 2, total is 4
        assertEquals(expectedCardinality, SetOperations.cardinality(bitMapExtractor));
    }
}
