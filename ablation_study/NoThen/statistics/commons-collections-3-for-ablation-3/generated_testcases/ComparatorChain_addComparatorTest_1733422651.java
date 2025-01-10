
package org.apache.commons.collections4.comparators;

import org.junit.jupiter.api.Test;
import java.util.Comparator;
import static org.junit.jupiter.api.Assertions.*;

class ComparatorChain_addComparatorTest {

    @Test
    void testAddComparator() {
        // Given
        ComparatorChain<String> chain = new ComparatorChain<>();
        Comparator<String> comparator = Comparator.naturalOrder();

        // When
        chain.addComparator(comparator, false);

        // Then
        assertEquals(1, chain.size());
        assertFalse(chain.orderingBits.get(0));

        // Given
        chain = new ComparatorChain<>();

        // When
        chain.addComparator(comparator, true);

        // Then
        assertEquals(1, chain.size());
        assertTrue(chain.orderingBits.get(0));
    }
}
