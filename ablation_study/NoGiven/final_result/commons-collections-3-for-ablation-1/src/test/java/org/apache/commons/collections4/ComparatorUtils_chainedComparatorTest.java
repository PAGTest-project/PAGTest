
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import java.util.Comparator;
import static org.junit.jupiter.api.Assertions.*;

class ComparatorUtils_chainedComparatorTest {

    @Test
    void testChainedComparator_SingleComparator() {
        // Given
        Comparator<Integer> comparator = Comparator.naturalOrder();

        // When
        Comparator<Integer> chainedComparator = ComparatorUtils.chainedComparator(comparator);

        // Then
        assertNotNull(chainedComparator);
    }

    @Test
    void testChainedComparator_MultipleComparators() {
        // Given
        Comparator<Integer> comparator1 = Comparator.naturalOrder();
        Comparator<Integer> comparator2 = Comparator.reverseOrder();

        // When
        Comparator<Integer> chainedComparator = ComparatorUtils.chainedComparator(comparator1, comparator2);

        // Then
        assertNotNull(chainedComparator);
    }

    @Test
    void testChainedComparator_NullComparator() {
        // Given
        Comparator<Integer> comparator1 = Comparator.naturalOrder();
        Comparator<Integer> comparator2 = null;

        // When & Then
        assertThrows(NullPointerException.class, () -> {
            ComparatorUtils.chainedComparator(comparator1, comparator2);
        });
    }
}
