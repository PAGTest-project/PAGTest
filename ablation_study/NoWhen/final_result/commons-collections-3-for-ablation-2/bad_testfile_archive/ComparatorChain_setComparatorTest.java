
package org.apache.commons.collections4.comparators;

import org.junit.jupiter.api.Test;
import java.util.Comparator;
import static org.junit.jupiter.api.Assertions.*;

class ComparatorChain_setComparatorTest {

    @Test
    void testSetComparator() {
        // Given
        ComparatorChain<String> chain = new ComparatorChain<>();
        Comparator<String> comparator = Comparator.naturalOrder();
        chain.addComparator(comparator, false); // Add a comparator to set

        // When
        chain.setComparator(0, comparator, true);

        // Then
        assertTrue(chain.isLocked());
        assertEquals(comparator, chain.getComparators().get(0));
        assertTrue(chain.getBitSet().get(0));
    }
}
