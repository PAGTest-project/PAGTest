
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
        assertTrue(chain.orderingBits.get(0));
        assertEquals(comparator, chain.comparatorChain.get(0));
    }
}
