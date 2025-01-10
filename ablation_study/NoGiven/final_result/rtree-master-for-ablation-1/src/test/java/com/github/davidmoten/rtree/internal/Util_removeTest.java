
package com.github.davidmoten.rtree.internal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class Util_removeTest {

    @Test
    public void testRemove() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> elements = Arrays.asList(2, 4);
        List<Integer> expected = Arrays.asList(1, 3, 5);

        List<Integer> result = Util.remove(list, elements);

        assertEquals(expected, result);
    }

    @Test
    public void testRemoveWithNoElementsToRemove() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> elements = Arrays.asList(6, 7);

        List<Integer> result = Util.remove(list, elements);

        assertEquals(list, result);
    }

    @Test
    public void testRemoveWithEmptyList() {
        List<Integer> list = Arrays.asList();
        List<Integer> elements = Arrays.asList(1, 2);

        List<Integer> result = Util.remove(list, elements);

        assertTrue(result.isEmpty());
    }
}
