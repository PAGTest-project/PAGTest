
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListUtils_intersectionTest {

    @Test
    public void testIntersection() {
        List<Integer> list1 = Arrays.asList(1, 2, 3, 4);
        List<Integer> list2 = Arrays.asList(3, 4, 5, 6);

        List<Integer> result = ListUtils.intersection(list1, list2);

        assertEquals(Arrays.asList(3, 4), result);
    }
}
