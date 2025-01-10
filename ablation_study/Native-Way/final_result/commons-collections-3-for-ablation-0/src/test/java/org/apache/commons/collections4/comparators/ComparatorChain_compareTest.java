
package org.apache.commons.collections4.comparators;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Comparator;

public class ComparatorChain_compareTest {

    private ComparatorChain<Integer> comparatorChain;

    @BeforeEach
    public void setUp() {
        comparatorChain = new ComparatorChain<>();
    }

    @Test
    public void testCompareWithSingleComparatorForwardOrder() {
        comparatorChain.addComparator(Comparator.naturalOrder());
        assertEquals(1, comparatorChain.compare(2, 1));
        assertEquals(-1, comparatorChain.compare(1, 2));
        assertEquals(0, comparatorChain.compare(1, 1));
    }

    @Test
    public void testCompareWithSingleComparatorReverseOrder() {
        comparatorChain.addComparator(Comparator.naturalOrder(), true);
        assertEquals(-1, comparatorChain.compare(2, 1));
        assertEquals(1, comparatorChain.compare(1, 2));
        assertEquals(0, comparatorChain.compare(1, 1));
    }

    @Test
    public void testCompareWithMultipleComparators() {
        comparatorChain.addComparator(Comparator.naturalOrder());
        comparatorChain.addComparator((a, b) -> a.compareTo(b) * -1, true);
        assertEquals(1, comparatorChain.compare(2, 1));
        assertEquals(-1, comparatorChain.compare(1, 2));
        assertEquals(0, comparatorChain.compare(1, 1));
    }

    @Test
    public void testCompareWithEmptyChain() {
        assertThrows(UnsupportedOperationException.class, () -> {
            comparatorChain.compare(1, 2);
        });
    }

    @Test
    public void testCompareAfterModification() {
        comparatorChain.addComparator(Comparator.naturalOrder());
        comparatorChain.compare(1, 2); // This should lock the chain
        assertThrows(UnsupportedOperationException.class, () -> {
            comparatorChain.addComparator(Comparator.naturalOrder());
        });
    }

    @Test
    public void testCompareWithMinValuedComparator() {
        comparatorChain.addComparator((a, b) -> {
            final int result = a.compareTo(b);
            if (result < 0) {
                return Integer.MIN_VALUE;
            }
            if (result > 0) {
                return Integer.MAX_VALUE;
            }
            return 0;
        }, true);

        assertTrue(comparatorChain.compare(4, 5) > 0);
        assertTrue(comparatorChain.compare(5, 4) < 0);
        assertEquals(0, comparatorChain.compare(4, 4));
    }
}
