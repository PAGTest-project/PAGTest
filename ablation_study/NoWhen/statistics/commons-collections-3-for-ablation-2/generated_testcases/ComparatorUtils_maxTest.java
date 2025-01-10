
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import java.util.Comparator;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ComparatorUtils_maxTest {

    @Test
    public void testMaxWithDefaultComparator() {
        // Given
        Integer o1 = 5;
        Integer o2 = 10;
        Comparator<Integer> comparator = null;

        // When
        Integer result = ComparatorUtils.max(o1, o2, comparator);

        // Then
        assertEquals(o2, result);
    }

    @Test
    public void testMaxWithCustomComparator() {
        // Given
        Integer o1 = 5;
        Integer o2 = 10;
        Comparator<Integer> comparator = Comparator.naturalOrder();

        // When
        Integer result = ComparatorUtils.max(o1, o2, comparator);

        // Then
        assertEquals(o2, result);
    }

    @Test
    public void testMaxWithCustomComparatorReversed() {
        // Given
        Integer o1 = 5;
        Integer o2 = 10;
        Comparator<Integer> comparator = Comparator.reverseOrder();

        // When
        Integer result = ComparatorUtils.max(o1, o2, comparator);

        // Then
        assertEquals(o1, result);
    }
}
