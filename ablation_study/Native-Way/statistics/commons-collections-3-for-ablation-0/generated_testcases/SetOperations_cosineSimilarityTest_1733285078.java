
package org.apache.commons.collections4.bloomfilter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import java.util.function.LongPredicate;
import java.util.function.LongBinaryOperator;
import java.util.function.ToDoubleBiFunction;

public class SetOperations_cosineSimilarityTest {

    @Test
    public final void testCosineSimilarity() {
        BitMapExtractor extractor1 = createBitMapExtractor(1, 17);
        BitMapExtractor extractor2 = createBitMapExtractor(1, 17);

        int dotProduct = 17;
        int cardinalityA = 17;
        int cardinalityB = 17;
        double expected = 1.0;
        assertSymmetricOperation(expected, SetOperations::cosineSimilarity, extractor1, extractor2);

        dotProduct = 7;
        cardinalityA = 17;
        cardinalityB = 17;
        expected = dotProduct / Math.sqrt(cardinalityA * cardinalityB);
        extractor2 = createBitMapExtractor(11, 27);
        assertSymmetricOperation(expected, SetOperations::cosineSimilarity, extractor1, extractor2);

        // test no values
        extractor1 = new EmptyBitMapExtractor();
        extractor2 = new EmptyBitMapExtractor();
        // build a filter
        final BitMapExtractor extractor3 = createBitMapExtractor(1, 17);
        assertSymmetricOperation(0.0, SetOperations::cosineSimilarity, extractor1, extractor2);
        assertSymmetricOperation(0.0, SetOperations::cosineSimilarity, extractor1, extractor3);
    }

    private BitMapExtractor createBitMapExtractor(int start, int end) {
        // Mock implementation for BitMapExtractor creation
        return new BitMapExtractor() {
            @Override
            public void processBitMaps(LongPredicate predicate) {
                for (long i = start; i <= end; i++) {
                    predicate.test(i);
                }
            }

            @Override
            public void processBitMapPairs(BitMapExtractor other, LongBinaryOperator predicate) {
                // Mock implementation for processBitMapPairs
            }
        };
    }

    private static class EmptyBitMapExtractor extends BitMapExtractor {
        @Override
        public void processBitMaps(LongPredicate predicate) {
            // No-op
        }

        @Override
        public void processBitMapPairs(BitMapExtractor other, LongBinaryOperator predicate) {
            // No-op
        }
    }

    private <T> void assertSymmetricOperation(double expected, ToDoubleBiFunction<T, T> operation, T a, T b) {
        assertEquals(expected, operation.applyAsDouble(a, b), 0.0001);
        assertEquals(expected, operation.applyAsDouble(b, a), 0.0001);
    }
}
