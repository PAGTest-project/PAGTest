
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Comparator;

public class ComparatorUtils_maxTest {

    @Test
    public void testMaxWithDefaultComparator() {
        Integer result = ComparatorUtils.max(1, 2, null);
        assertEquals(2, result);
    }

    @Test
    public void testMaxWithCustomComparator() {
        Comparator<Integer> reverseComparator = Comparator.reverseOrder();
        Integer result = ComparatorUtils.max(1, 2, reverseComparator);
        assertEquals(1, result);
    }
}
