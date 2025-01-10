
package org.apache.commons.collections4;

import org.apache.commons.collections4.comparators.NullComparator;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ComparatorUtils_nullLowComparatorTest {

    @Test
    public void testNullLowComparator_WithNullComparator() {
        // Given
        Comparator<String> comparator = null;

        // When
        Comparator<String> result = ComparatorUtils.nullLowComparator(comparator);

        // Then
        assertTrue(result instanceof NullComparator);
        assertEquals(ComparatorUtils.NATURAL_COMPARATOR, ((NullComparator<String>) result).getNonNullComparator());
    }

    @Test
    public void testNullLowComparator_WithNonNullComparator() {
        // Given
        Comparator<String> comparator = Comparator.naturalOrder();

        // When
        Comparator<String> result = ComparatorUtils.nullLowComparator(comparator);

        // Then
        assertTrue(result instanceof NullComparator);
        assertEquals(comparator, ((NullComparator<String>) result).getNonNullComparator());
    }
}
