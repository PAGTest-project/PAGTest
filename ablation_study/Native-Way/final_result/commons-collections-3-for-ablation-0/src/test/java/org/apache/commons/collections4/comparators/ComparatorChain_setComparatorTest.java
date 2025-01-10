
package org.apache.commons.collections4.comparators;

import org.junit.jupiter.api.Test;
import java.util.Comparator;
import static org.junit.jupiter.api.Assertions.*;

class ComparatorChain_setComparatorTest {

    @Test
    void testSetComparator() {
        ComparatorChain<String> chain = new ComparatorChain<>();
        Comparator<String> comparator = Comparator.naturalOrder();

        // Add a comparator to the chain before setting
        chain.addComparator(comparator);

        // Test setting comparator without reverse
        chain.setComparator(0, comparator, false);

        // Test setting comparator with reverse
        chain.setComparator(0, comparator, true);
    }

    @Test
    void testSetComparatorLocked() {
        ComparatorChain<String> chain = new ComparatorChain<>();
        Comparator<String> comparator = Comparator.naturalOrder();

        // Add a comparator to the chain before locking
        chain.addComparator(comparator);

        // Lock the chain by performing a comparison
        chain.compare("a", "b");

        // Attempt to set comparator when locked
        assertThrows(UnsupportedOperationException.class, () -> chain.setComparator(0, comparator, false));
    }
}
