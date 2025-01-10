
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListUtils_unionTest {

    @Test
    public void testUnion() {
        List<Integer> list1 = Arrays.asList(1, 2, 3);
        List<Integer> list2 = Arrays.asList(3, 4, 5);

        List<Integer> result = ListUtils.union(list1, list2);

        assertEquals(Arrays.asList(1, 2, 3, 3, 4, 5), result);
    }
}
