
package org.apache.commons.collections4;

import org.apache.commons.collections4.comparators.TransformingComparator;
import org.apache.commons.collections4.functors.ConstantTransformer;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ComparatorUtils_transformedComparatorTest {

    @Test
    public void testTransformedComparatorWithNullComparator() {
        // Given
        Transformer<String, Integer> transformer = new ConstantTransformer<>(1);

        // When
        Comparator<String> result = ComparatorUtils.transformedComparator(null, transformer);

        // Then
        assertTrue(result instanceof TransformingComparator);
        assertEquals(ComparatorUtils.NATURAL_COMPARATOR, ((TransformingComparator<?, ?>) result).getComparator());
    }

    @Test
    public void testTransformedComparatorWithNonNullComparator() {
        // Given
        Comparator<Integer> comparator = Comparator.naturalOrder();
        Transformer<String, Integer> transformer = new ConstantTransformer<>(1);

        // When
        Comparator<String> result = ComparatorUtils.transformedComparator(comparator, transformer);

        // Then
        assertTrue(result instanceof TransformingComparator);
        assertEquals(comparator, ((TransformingComparator<?, ?>) result).getComparator());
    }
}
