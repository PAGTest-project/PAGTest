
package org.apache.commons.collections4.comparators;

import org.junit.jupiter.api.Test;
import java.util.Comparator;
import static org.junit.jupiter.api.Assertions.*;

class ComparatorChain_addComparatorTest {

    @Test
    void testAddComparator_ForwardOrder() {
        ComparatorChain<String> chain = new ComparatorChain<>();
        Comparator<String> comparator = Comparator.naturalOrder();
        chain.addComparator(comparator, false);
        assertEquals(1, chain.size());
    }

    @Test
    void testAddComparator_ReverseOrder() {
        ComparatorChain<String> chain = new ComparatorChain<>();
        Comparator<String> comparator = Comparator.naturalOrder();
        chain.addComparator(comparator, true);
        assertEquals(1, chain.size());
    }

    @Test
    void testAddComparator_LockedChain() {
        ComparatorChain<String> chain = new ComparatorChain<>();
        chain.compare("a", "b"); // This will lock the chain
        Comparator<String> comparator = Comparator.naturalOrder();
        assertThrows(UnsupportedOperationException.class, () -> chain.addComparator(comparator, false));
    }
}
