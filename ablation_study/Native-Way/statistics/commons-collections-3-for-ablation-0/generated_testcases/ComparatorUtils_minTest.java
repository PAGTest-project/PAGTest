
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import java.util.Comparator;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ComparatorUtils_minTest {

    @Test
    public void testMinWithDefaultComparator() {
        Integer o1 = 5;
        Integer o2 = 10;
        Comparator<Integer> comparator = null;
        Integer result = ComparatorUtils.min(o1, o2, comparator);
        assertEquals(o1, result);
    }

    @Test
    public void testMinWithCustomComparator() {
        Integer o1 = 5;
        Integer o2 = 10;
        Comparator<Integer> comparator = Comparator.naturalOrder();
        Integer result = ComparatorUtils.min(o1, o2, comparator);
        assertEquals(o1, result);
    }

    @Test
    public void testMinWithReversedComparator() {
        Integer o1 = 5;
        Integer o2 = 10;
        Comparator<Integer> comparator = Comparator.reverseOrder();
        Integer result = ComparatorUtils.min(o1, o2, comparator);
        assertEquals(o2, result);
    }
}
