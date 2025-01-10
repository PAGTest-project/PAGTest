
package org.apache.commons.collections4.comparators;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ComparatorChain_compareTest {

    private ComparatorChain<String> comparatorChain;

    @BeforeEach
    void setUp() {
        comparatorChain = new ComparatorChain<>();
    }

    @Test
    void testCompare_SingleComparator_ForwardOrder() {
        comparatorChain.addComparator(Comparator.naturalOrder());
        assertEquals(1, comparatorChain.compare("b", "a"));
    }

    @Test
    void testCompare_SingleComparator_ReverseOrder() {
        comparatorChain.addComparator(Comparator.naturalOrder(), true);
        assertEquals(-1, comparatorChain.compare("b", "a"));
    }

    @Test
    void testCompare_MultipleComparators_MixedOrder() {
        comparatorChain.addComparator(Comparator.naturalOrder());
        comparatorChain.addComparator(Comparator.reverseOrder(), true);
        assertEquals(-1, comparatorChain.compare("a", "b"));
    }

    @Test
    void testCompare_NoComparators_ThrowsException() {
        assertThrows(UnsupportedOperationException.class, () -> comparatorChain.compare("a", "b"));
    }
}
