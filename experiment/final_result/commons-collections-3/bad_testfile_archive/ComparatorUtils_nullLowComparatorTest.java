
package org.apache.commons.collections4;

import org.apache.commons.collections4.comparators.NullComparator;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ComparatorUtils_nullLowComparatorTest {

    @Test
    public void testNullLowComparatorWithNonNullComparator() {
        Comparator<String> originalComparator = Comparator.naturalOrder();
        Comparator<String> nullLowComparator = ComparatorUtils.nullLowComparator(originalComparator);

        assertTrue(nullLowComparator instanceof NullComparator);
        assertEquals(originalComparator, ((NullComparator<String>) nullLowComparator).getComparator());
    }

    @Test
    public void testNullLowComparatorWithNullComparator() {
        Comparator<String> nullLowComparator = ComparatorUtils.nullLowComparator(null);

        assertTrue(nullLowComparator instanceof NullComparator);
        assertEquals(ComparatorUtils.NATURAL_COMPARATOR, ((NullComparator<String>) nullLowComparator).getComparator());
    }
}
