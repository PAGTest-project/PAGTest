
package org.apache.commons.collections4.bloomfilter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.function.IntPredicate;
import static org.junit.jupiter.api.Assertions.*;

public class EnhancedDoubleHasher_indicesTest {

    private EnhancedDoubleHasher hasher;
    private Shape shape;

    @BeforeEach
    public void setUp() {
        hasher = new EnhancedDoubleHasher(123456789L, 987654321L);
        shape = Shape.fromNMK(5, 24, 3);
    }

    @Test
    public void testIndicesNonNullShape() {
        assertThrows(NullPointerException.class, () -> hasher.indices(null));
    }

    @Test
    public void testAsIndexArray() {
        IndexExtractor extractor = hasher.indices(shape);
        int[] indices = extractor.asIndexArray();
        assertEquals(shape.getNumberOfHashFunctions(), indices.length);
        // Ensure all indices are within the valid range [0, shape.getNumberOfBits())
        for (int index : indices) {
            assertTrue(index >= 0 && index < shape.getNumberOfBits());
        }
    }

    @Test
    public void testProcessIndicesNonNullConsumer() {
        IndexExtractor extractor = hasher.indices(shape);
        assertThrows(NullPointerException.class, () -> extractor.processIndices(null));
    }

    @Test
    public void testProcessIndicesValidConsumer() {
        IndexExtractor extractor = hasher.indices(shape);
        boolean result = extractor.processIndices(i -> i >= 0 && i < shape.getNumberOfBits());
        assertTrue(result);
    }

    @Test
    public void testProcessIndicesInvalidConsumer() {
        IndexExtractor extractor = hasher.indices(shape);
        boolean result = extractor.processIndices(i -> i < 0);
        assertFalse(result);
    }

    @Test
    public void testProcessIndicesWithKGreaterThanBits() {
        Shape largeShape = Shape.fromNMK(5, 24, 25);
        IndexExtractor extractor = hasher.indices(largeShape);
        boolean result = extractor.processIndices(i -> i >= 0 && i < largeShape.getNumberOfBits());
        assertTrue(result);
    }

    @Test
    public void testProcessIndicesWithKLessThanBits() {
        Shape smallShape = Shape.fromNMK(5, 24, 2);
        IndexExtractor extractor = hasher.indices(smallShape);
        boolean result = extractor.processIndices(i -> i >= 0 && i < smallShape.getNumberOfBits());
        assertTrue(result);
    }
}
