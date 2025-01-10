
package org.apache.commons.collections4;

import org.apache.commons.collections4.comparators.TransformingComparator;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ComparatorUtils_transformedComparatorTest {

    @Test
    public void testTransformedComparatorWithNullComparator() {
        // Given
        Transformer<String, Integer> transformer = Integer::parseInt;

        // When
        Comparator<String> result = ComparatorUtils.transformedComparator(null, transformer);

        // Then
        assertNotNull(result);
        assertEquals(TransformingComparator.class, result.getClass());
    }

    @Test
    public void testTransformedComparatorWithNonNullComparator() {
        // Given
        Comparator<Integer> comparator = Integer::compare;
        Transformer<String, Integer> transformer = Integer::parseInt;

        // When
        Comparator<String> result = ComparatorUtils.transformedComparator(comparator, transformer);

        // Then
        assertNotNull(result);
        assertEquals(TransformingComparator.class, result.getClass());
    }
}
