
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
        Comparator<String> comparator = null;
        Transformer<Integer, String> transformer = Object::toString;

        // When
        Comparator<Integer> result = ComparatorUtils.transformedComparator(comparator, transformer);

        // Then
        assertNotNull(result);
        assertEquals(TransformingComparator.class, result.getClass());
    }

    @Test
    public void testTransformedComparatorWithNonNullComparator() {
        // Given
        Comparator<String> comparator = Comparator.naturalOrder();
        Transformer<Integer, String> transformer = Object::toString;

        // When
        Comparator<Integer> result = ComparatorUtils.transformedComparator(comparator, transformer);

        // Then
        assertNotNull(result);
        assertEquals(TransformingComparator.class, result.getClass());
    }
}
