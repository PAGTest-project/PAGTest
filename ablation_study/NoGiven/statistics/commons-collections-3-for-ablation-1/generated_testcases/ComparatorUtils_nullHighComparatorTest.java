
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
        Comparator<String> inputComparator = null;

        // When
        Comparator<String> result = ComparatorUtils.nullHighComparator(inputComparator);

        // Then
        assertTrue(result instanceof NullComparator);
        assertEquals(ComparatorUtils.NATURAL_COMPARATOR, ((NullComparator<String>) result).getNonNullComparator());
    }

    @Test
    public void testNullHighComparator_WithNonNullComparator() {
        // Given
        Comparator<String> inputComparator = Comparator.naturalOrder();

        // When
        Comparator<String> result = ComparatorUtils.nullHighComparator(inputComparator);

        // Then
        assertTrue(result instanceof NullComparator);
        assertEquals(inputComparator, ((NullComparator<String>) result).getNonNullComparator());
    }
}
