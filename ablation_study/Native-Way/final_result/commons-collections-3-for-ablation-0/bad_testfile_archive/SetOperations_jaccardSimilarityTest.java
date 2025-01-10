
package org.apache.commons.collections4.bloomfilter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class SetOperations_jaccardSimilarityTest {

    @Test
    public final void testJaccardSimilarityWithEqualBitMaps() {
        final BitMapExtractor p1 = BitMapExtractor.fromBitMapArray(0x3L, 0x5L);
        final BitMapExtractor p2 = BitMapExtractor.fromBitMapArray(0x3L, 0x5L);

        assertEquals(1.0, SetOperations.jaccardSimilarity(p1, p2));
    }

    @Test
    public final void testJaccardSimilarityWithNoIntersection() {
        final BitMapExtractor p1 = BitMapExtractor.fromBitMapArray(0x3L, 0x5L);
        final BitMapExtractor p2 = BitMapExtractor.fromBitMapArray(0x8L, 0x10L);

        assertEquals(0.0, SetOperations.jaccardSimilarity(p1, p2));
    }

    @Test
    public final void testJaccardSimilarityWithPartialIntersection() {
        final BitMapExtractor p1 = BitMapExtractor.fromBitMapArray(0x3L, 0x5L);
        final BitMapExtractor p2 = BitMapExtractor.fromBitMapArray(0x1L, 0x5L);

        assertEquals(0.5, SetOperations.jaccardSimilarity(p1, p2), 0.0001);
    }

    @Test
    public final void testJaccardSimilarityWithEmptyBitMap() {
        final BitMapExtractor p1 = BitMapExtractor.fromBitMapArray(0x0L, 0x0L);
        final BitMapExtractor p2 = BitMapExtractor.fromBitMapArray(0x3L, 0x5L);

        assertEquals(0.0, SetOperations.jaccardSimilarity(p1, p2));
    }

    @Test
    public final void testJaccardSimilarityWithCommutativity() {
        final BitMapExtractor p1 = BitMapExtractor.fromBitMapArray(0x3L, 0x5L);
        final BitMapExtractor p2 = BitMapExtractor.fromBitMapArray(0x1L, 0x5L);

        assertEquals(SetOperations.jaccardSimilarity(p1, p2), SetOperations.jaccardSimilarity(p2, p1), 0.0001);
    }
}
