
package org.apache.commons.collections4.comparators;

import org.junit.jupiter.api.Test;
import java.util.Comparator;
import static org.junit.jupiter.api.Assertions.*;

class ComparatorChain_setComparatorTest {

    @Test
    void testSetComparator_ForwardSort() {
        // Given
        ComparatorChain<String> chain = new ComparatorChain<>();
        Comparator<String> comparator = Comparator.naturalOrder();
        chain.addComparator(comparator);

        // When
        chain.setComparator(0, comparator, false);

        // Then
        assertFalse(chain.orderingBits.get(0));
    }

    @Test
    void testSetComparator_ReverseSort() {
        // Given
        ComparatorChain<String> chain = new ComparatorChain<>();
        Comparator<String> comparator = Comparator.naturalOrder();
        chain.addComparator(comparator);

        // When
        chain.setComparator(0, comparator, true);

        // Then
        assertTrue(chain.orderingBits.get(0));
    }

    @Test
    void testSetComparator_LockedChain() {
        // Given
        ComparatorChain<String> chain = new ComparatorChain<>();
        Comparator<String> comparator = Comparator.naturalOrder();
        chain.addComparator(comparator);
        chain.compare("a", "b"); // This will lock the chain

        // When & Then
        assertThrows(UnsupportedOperationException.class, () -> {
            chain.setComparator(0, comparator, false);
        });
    }
}
