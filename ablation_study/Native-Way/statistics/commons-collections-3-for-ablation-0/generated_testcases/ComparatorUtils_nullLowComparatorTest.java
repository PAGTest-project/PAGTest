
package org.apache.commons.collections4;

import org.apache.commons.collections4.comparators.NullComparator;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ComparatorUtils_nullLowComparatorTest {

    @Test
    public void testNullLowComparatorWithNullComparator() {
        Comparator<String> comparator = ComparatorUtils.nullLowComparator(null);
        assertTrue(comparator instanceof NullComparator);
        assertEquals(ComparatorUtils.NATURAL_COMPARATOR, ((NullComparator<?>) comparator).getNonNullComparator());
    }

    @Test
    public void testNullLowComparatorWithNonNullComparator() {
        Comparator<String> customComparator = Comparator.naturalOrder();
        Comparator<String> comparator = ComparatorUtils.nullLowComparator(customComparator);
        assertTrue(comparator instanceof NullComparator);
        assertEquals(customComparator, ((NullComparator<?>) comparator).getNonNullComparator());
    }
}
