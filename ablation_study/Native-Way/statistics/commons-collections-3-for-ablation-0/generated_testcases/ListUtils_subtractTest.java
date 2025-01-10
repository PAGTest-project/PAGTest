
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListUtils_subtractTest {

    @Test
    public void testSubtract() {
        List<Integer> list1 = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> list2 = Arrays.asList(3, 4);
        List<Integer> expected = Arrays.asList(1, 2, 5);

        List<Integer> result = ListUtils.subtract(list1, list2);

        assertEquals(expected, result);
    }
}
