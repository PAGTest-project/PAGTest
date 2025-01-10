
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Comparator;

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
        Comparator<Integer> reverseComparator = Comparator.reverseOrder();
        Integer result = ComparatorUtils.min(o1, o2, reverseComparator);
        assertEquals(o2, result);
    }
}
