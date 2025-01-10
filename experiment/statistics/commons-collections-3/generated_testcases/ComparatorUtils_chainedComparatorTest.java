
package org.apache.commons.collections4;

import org.apache.commons.collections4.comparators.ComparatorChain;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ComparatorUtils_chainedComparatorTest {

    @Test
    public void testChainedComparator_WithValidComparators() {
        Comparator<Integer> comparator1 = Comparator.naturalOrder();
        Comparator<Integer> comparator2 = Comparator.reverseOrder();

        Comparator<Integer> chainedComparator = ComparatorUtils.chainedComparator(comparator1, comparator2);

        assertTrue(chainedComparator instanceof ComparatorChain);
    }

    @Test
    public void testChainedComparator_WithNullComparator() {
        Comparator<Integer> comparator1 = Comparator.naturalOrder();
        Comparator<Integer> nullComparator = null;

        assertThrows(NullPointerException.class, () -> {
            ComparatorUtils.chainedComparator(comparator1, nullComparator);
        });
    }
}
