
package org.apache.commons.collections4;

import org.apache.commons.collections4.comparators.NullComparator;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ComparatorUtils_nullHighComparatorTest {

    @Test
    public void testNullHighComparator_WithNullComparator() {
        // Given
        Comparator<String> nullComparator = null;

        // When
        Comparator<String> result = ComparatorUtils.nullHighComparator(nullComparator);

        // Then
        assertTrue(result instanceof NullComparator);
        assertEquals(ComparatorUtils.NATURAL_COMPARATOR, ((NullComparator<?>) result).getNonNullComparator());
    }

    @Test
    public void testNullHighComparator_WithNonNullComparator() {
        // Given
        Comparator<String> nonNullComparator = Comparator.naturalOrder();

        // When
        Comparator<String> result = ComparatorUtils.nullHighComparator(nonNullComparator);

        // Then
        assertTrue(result instanceof NullComparator);
        assertEquals(nonNullComparator, ((NullComparator<?>) result).getNonNullComparator());
    }
}
