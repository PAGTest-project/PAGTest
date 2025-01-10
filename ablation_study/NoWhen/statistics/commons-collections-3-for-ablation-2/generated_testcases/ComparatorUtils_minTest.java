
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import java.util.Comparator;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ComparatorUtils_minTest {

    @Test
    public void testMinWithDefaultComparator() {
        // Given
        Integer o1 = 5;
        Integer o2 = 10;

        // When
        Integer result = ComparatorUtils.min(o1, o2, null);

        // Then
        assertEquals(o1, result);
    }

    @Test
    public void testMinWithCustomComparator() {
        // Given
        Integer o1 = 5;
        Integer o2 = 10;
        Comparator<Integer> reverseComparator = Comparator.reverseOrder();

        // When
        Integer result = ComparatorUtils.min(o1, o2, reverseComparator);

        // Then
        assertEquals(o2, result);
    }
}
