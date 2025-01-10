
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ListUtils_partitionTest {

    @Test
    void testPartition_ValidInput() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        int size = 2;

        List<List<Integer>> result = ListUtils.partition(list, size);

        assertEquals(3, result.size());
        assertEquals(Arrays.asList(1, 2), result.get(0));
        assertEquals(Arrays.asList(3, 4), result.get(1));
        assertEquals(Arrays.asList(5), result.get(2));
    }

    @Test
    void testPartition_NullList() {
        List<Integer> list = null;
        int size = 2;

        Exception exception = assertThrows(NullPointerException.class, () -> {
            ListUtils.partition(list, size);
        });

        assertEquals("list", exception.getMessage());
    }

    @Test
    void testPartition_InvalidSize() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        int size = 0;

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            ListUtils.partition(list, size);
        });

        assertEquals("Size must be greater than 0", exception.getMessage());
    }
}
