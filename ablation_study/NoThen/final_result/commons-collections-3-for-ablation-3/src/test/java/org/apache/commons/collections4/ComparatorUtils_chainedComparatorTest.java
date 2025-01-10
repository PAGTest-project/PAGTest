
package org.apache.commons.collections4;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Comparator;

import org.apache.commons.collections4.comparators.ComparatorChain;
import org.junit.jupiter.api.Test;

public class ComparatorUtils_chainedComparatorTest {

    @Test
    public void testChainedComparatorWithValidComparators() {
        Comparator<Integer> comparator1 = Comparator.naturalOrder();
        Comparator<Integer> comparator2 = Comparator.reverseOrder();

        Comparator<Integer> chainedComparator = ComparatorUtils.chainedComparator(comparator1, comparator2);

        assertTrue(chainedComparator instanceof ComparatorChain);
        assertEquals(-1, chainedComparator.compare(1, 2));
        assertEquals(1, chainedComparator.compare(2, 1));
    }

    @Test
    public void testChainedComparatorWithNullComparator() {
        Comparator<Integer> comparator1 = Comparator.naturalOrder();
        Comparator<Integer> nullComparator = null;

        assertThrows(NullPointerException.class, () -> {
            ComparatorUtils.chainedComparator(comparator1, nullComparator);
        });
    }

    @Test
    public void testChainedComparatorWithEmptyComparators() {
        Comparator<Integer>[] emptyComparators = new Comparator[0];

        Comparator<Integer> chainedComparator = ComparatorUtils.chainedComparator(emptyComparators);

        assertTrue(chainedComparator instanceof ComparatorChain);
    }

    @Test
    public void testChainedComparatorWithSingleComparator() {
        Comparator<Integer> comparator1 = Comparator.naturalOrder();

        Comparator<Integer> chainedComparator = ComparatorUtils.chainedComparator(comparator1);

        assertTrue(chainedComparator instanceof ComparatorChain);
        assertEquals(-1, chainedComparator.compare(1, 2));
        assertEquals(1, chainedComparator.compare(2, 1));
    }
}
