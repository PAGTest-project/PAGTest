
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ListUtils_removeAllTest {

    @Test
    public void testRemoveAll_NormalCase() {
        List<Integer> collection = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> remove = Arrays.asList(2, 4);
        List<Integer> expected = Arrays.asList(1, 3, 5);

        List<Integer> result = ListUtils.removeAll(collection, remove);

        assertEquals(expected, result);
    }

    @Test
    public void testRemoveAll_NoElementsToRemove() {
        List<Integer> collection = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> remove = Collections.emptyList();
        List<Integer> expected = Arrays.asList(1, 2, 3, 4, 5);

        List<Integer> result = ListUtils.removeAll(collection, remove);

        assertEquals(expected, result);
    }

    @Test
    public void testRemoveAll_AllElementsToRemove() {
        List<Integer> collection = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> remove = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> expected = Collections.emptyList();

        List<Integer> result = ListUtils.removeAll(collection, remove);

        assertEquals(expected, result);
    }

    @Test
    public void testRemoveAll_NullCollection() {
        List<Integer> remove = Arrays.asList(1, 2, 3);

        assertThrows(NullPointerException.class, () -> {
            ListUtils.removeAll(null, remove);
        });
    }

    @Test
    public void testRemoveAll_NullRemove() {
        List<Integer> collection = Arrays.asList(1, 2, 3);

        assertThrows(NullPointerException.class, () -> {
            ListUtils.removeAll(collection, null);
        });
    }
}
