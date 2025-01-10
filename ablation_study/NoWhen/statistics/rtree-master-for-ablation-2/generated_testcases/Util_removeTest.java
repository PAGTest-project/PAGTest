
package com.github.davidmoten.rtree.internal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

public class Util_removeTest {

    @Test
    public void testRemoveAllElements() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> elements = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> result = Util.remove(list, elements);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testRemoveSomeElements() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> elements = Arrays.asList(2, 4);
        List<Integer> result = Util.remove(list, elements);
        assertEquals(Arrays.asList(1, 3, 5), result);
    }

    @Test
    public void testRemoveNoElements() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> elements = Collections.emptyList();
        List<Integer> result = Util.remove(list, elements);
        assertEquals(Arrays.asList(1, 2, 3, 4, 5), result);
    }

    @Test
    public void testRemoveElementsFromEmptyList() {
        List<Integer> list = Collections.emptyList();
        List<Integer> elements = Arrays.asList(1, 2, 3);
        List<Integer> result = Util.remove(list, elements);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testRemoveNonExistentElements() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> elements = Arrays.asList(6, 7);
        List<Integer> result = Util.remove(list, elements);
        assertEquals(Arrays.asList(1, 2, 3, 4, 5), result);
    }
}
