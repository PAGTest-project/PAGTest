
package org.apache.commons.collections4.comparators;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ComparatorChain_compareTest {

    private ComparatorChain<String> comparatorChain;
    private List<Comparator<String>> comparators;
    private BitSet orderingBits;

    @BeforeEach
    public void setUp() {
        comparators = new ArrayList<>();
        orderingBits = new BitSet();
        comparatorChain = new ComparatorChain<>(comparators, orderingBits);
    }

    @Test
    public void testCompare_SingleComparator_ForwardOrder() {
        comparators.add(Comparator.naturalOrder());
        assertEquals(1, comparatorChain.compare("b", "a"));
    }

    @Test
    public void testCompare_SingleComparator_ReverseOrder() {
        comparators.add(Comparator.naturalOrder());
        orderingBits.set(0);
        assertEquals(-1, comparatorChain.compare("b", "a"));
    }

    @Test
    public void testCompare_MultipleComparators_MixedOrder() {
        comparators.add(Comparator.naturalOrder());
        comparators.add(Comparator.reverseOrder());
        orderingBits.set(1);
        assertEquals(1, comparatorChain.compare("a", "b"));
    }

    @Test
    public void testCompare_EmptyChain_ThrowsException() {
        assertThrows(UnsupportedOperationException.class, () -> comparatorChain.compare("a", "b"));
    }
}
