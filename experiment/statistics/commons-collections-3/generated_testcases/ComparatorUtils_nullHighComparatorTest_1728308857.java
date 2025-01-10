
package org.apache.commons.collections4;

import org.apache.commons.collections4.comparators.NullComparator;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ComparatorUtils_nullHighComparatorTest {

    @Test
    public void testNullHighComparatorWithNull() {
        Comparator<String> comparator = ComparatorUtils.nullHighComparator(null);
        assertTrue(comparator instanceof NullComparator);
        assertEquals(ComparatorUtils.NATURAL_COMPARATOR, ((NullComparator<?>) comparator).getNonNullComparator());
    }

    @Test
    public void testNullHighComparatorWithNonNull() {
        Comparator<String> originalComparator = Comparator.naturalOrder();
        Comparator<String> comparator = ComparatorUtils.nullHighComparator(originalComparator);
        assertTrue(comparator instanceof NullComparator);
        assertEquals(originalComparator, ((NullComparator<?>) comparator).getNonNullComparator());
    }
}
