
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CollectionUtils_collateTest {

    @Test
    public void testCollateWithDuplicates() {
        List<Integer> listA = Arrays.asList(1, 3, 5);
        List<Integer> listB = Arrays.asList(2, 3, 4);
        List<Integer> expected = Arrays.asList(1, 2, 3, 3, 4, 5);

        List<Integer> result = CollectionUtils.collate(listA, listB, true);
        assertEquals(expected, result);
    }

    @Test
    public void testCollateWithoutDuplicates() {
        List<Integer> listA = Arrays.asList(1, 3, 5);
        List<Integer> listB = Arrays.asList(2, 3, 4);
        List<Integer> expected = Arrays.asList(1, 2, 3, 4, 5);

        List<Integer> result = CollectionUtils.collate(listA, listB, false);
        assertEquals(expected, result);
    }
}
