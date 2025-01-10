
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
        Comparator<String> comparator = ComparatorUtils.transformedComparator(null, transformer);

        // Then
        assertTrue(comparator instanceof TransformingComparator);
        assertEquals(0, comparator.compare("a", "b"));
    }

    @Test
    public void testTransformedComparatorWithNonNullComparator() {
        // Given
        Transformer<String, Integer> transformer = new ConstantTransformer<>(1);
        Comparator<Integer> integerComparator = Comparator.naturalOrder();

        // When
        Comparator<String> comparator = ComparatorUtils.transformedComparator(integerComparator, transformer);

        // Then
        assertTrue(comparator instanceof TransformingComparator);
        assertEquals(0, comparator.compare("a", "b"));
    }
}
