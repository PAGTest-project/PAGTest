
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import java.util.Comparator;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ComparatorUtils_minTest {

    @Test
    public void testMinWithDefaultComparator() {
        Integer o1 = 5;
        Integer o2 = 10;
        Integer result = ComparatorUtils.min(o1, o2, null);
        assertEquals(o1, result);
    }

    @Test
    public void testMinWithCustomComparator() {
        Integer o1 = 5;
        Integer o2 = 10;
        Comparator<Integer> customComparator = Comparator.reverseOrder();
        Integer result = ComparatorUtils.min(o1, o2, customComparator);
        assertEquals(o2, result);
    }

    @Test
    public void testMinWithEqualObjects() {
        Integer o1 = 5;
        Integer o2 = 5;
        Integer result = ComparatorUtils.min(o1, o2, null);
        assertEquals(o2, result);
    }
}
