
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ListUtils_partitionTest {

    @Test
    void testPartition_NormalCase() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        List<List<Integer>> result = ListUtils.partition(list, 2);
        assertEquals(3, result.size());
        assertEquals(Arrays.asList(1, 2), result.get(0));
        assertEquals(Arrays.asList(3, 4), result.get(1));
        assertEquals(Arrays.asList(5), result.get(2));
    }

    @Test
    void testPartition_NullList() {
        assertThrows(NullPointerException.class, () -> ListUtils.partition(null, 2));
    }

    @Test
    void testPartition_InvalidSize() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        assertThrows(IllegalArgumentException.class, () -> ListUtils.partition(list, 0));
        assertThrows(IllegalArgumentException.class, () -> ListUtils.partition(list, -1));
    }
}
