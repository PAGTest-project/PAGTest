
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

        // When
        Integer result = ComparatorUtils.max(o1, o2, null);

        // Then
        assertEquals(o2, result);
    }

    @Test
    public void testMaxWithCustomComparator() {
        // Given
        Integer o1 = 5;
        Integer o2 = 10;
        Comparator<Integer> reverseComparator = Comparator.reverseOrder();

        // When
        Integer result = ComparatorUtils.max(o1, o2, reverseComparator);

        // Then
        assertEquals(o1, result);
    }
}
