
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import java.util.Comparator;
import static org.junit.jupiter.api.Assertions.*;

class ComparatorUtils_chainedComparatorTest {

    @Test
    void testChainedComparator() {
        // Given
        Comparator<Integer> comparator1 = Comparator.naturalOrder();
        Comparator<Integer> comparator2 = Comparator.reverseOrder();

        // When
        Comparator<Integer> chainedComparator = ComparatorUtils.chainedComparator(comparator1, comparator2);

        // Then
        assertEquals(-1, chainedComparator.compare(1, 2)); // comparator1: 1 < 2, comparator2: 1 > 2
        assertEquals(1, chainedComparator.compare(2, 1)); // comparator1: 2 > 1, comparator2: 2 < 1
        assertEquals(0, chainedComparator.compare(1, 1)); // comparator1: 1 == 1, comparator2: 1 == 1
    }

    @Test
    void testChainedComparatorWithNull() {
        // Given
        Comparator<Integer> comparator1 = Comparator.naturalOrder();
        Comparator<Integer> nullComparator = null;

        // When & Then
        assertThrows(NullPointerException.class, () -> {
            ComparatorUtils.chainedComparator(comparator1, nullComparator);
        });
    }
}
