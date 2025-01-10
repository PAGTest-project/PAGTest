
package org.apache.commons.collections4;

import org.apache.commons.collections4.comparators.NullComparator;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ComparatorUtils_nullLowComparatorTest {

    @Test
    public void testNullLowComparatorWithNonNullComparator() {
        // Given
        Comparator<String> originalComparator = Comparator.naturalOrder();

        // When
        Comparator<String> result = ComparatorUtils.nullLowComparator(originalComparator);

        // Then
        assertTrue(result instanceof NullComparator);
        assertEquals(0, result.compare(null, null));
        assertEquals(-1, result.compare(null, "a"));
        assertEquals(1, result.compare("a", null));
        assertEquals(originalComparator.compare("a", "b"), result.compare("a", "b"));
    }

    @Test
    public void testNullLowComparatorWithNullComparator() {
        // Given
        Comparator<String> originalComparator = null;

        // When
        Comparator<String> result = ComparatorUtils.nullLowComparator(originalComparator);

        // Then
        assertTrue(result instanceof NullComparator);
        assertEquals(0, result.compare(null, null));
        assertEquals(-1, result.compare(null, "a"));
        assertEquals(1, result.compare("a", null));
        assertEquals(Comparator.naturalOrder().compare("a", "b"), result.compare("a", "b"));
    }
}
