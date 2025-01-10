
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
        // Cannot directly access orderingBits due to private access

        // Test setting comparator with reverse
        chain.setComparator(0, comparator, true);
        // Cannot directly access orderingBits due to private access
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
