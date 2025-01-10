
package org.apache.commons.collections4.comparators;

import org.junit.jupiter.api.Test;
import java.util.Comparator;
import static org.junit.jupiter.api.Assertions.*;

class ComparatorChain_setComparatorTest {

    @Test
    void testSetComparator() {
        ComparatorChain<String> chain = new ComparatorChain<>();
        Comparator<String> comparator = Comparator.naturalOrder();

        // Test setting comparator without reverse
        chain.setComparator(0, comparator, false);
        assertFalse(chain.orderingBits.get(0));

        // Test setting comparator with reverse
        chain.setComparator(0, comparator, true);
        assertTrue(chain.orderingBits.get(0));
    }

    @Test
    void testSetComparatorLocked() {
        ComparatorChain<String> chain = new ComparatorChain<>();
        Comparator<String> comparator = Comparator.naturalOrder();

        // Lock the chain by performing a comparison
        chain.compare("a", "b");

        // Attempt to set comparator when locked
        assertThrows(UnsupportedOperationException.class, () -> chain.setComparator(0, comparator, false));
    }
}
