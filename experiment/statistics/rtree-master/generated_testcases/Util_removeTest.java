
package com.github.davidmoten.rtree.internal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

public class Util_removeTest {

    @Test
    public void testRemoveElementsFromList() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> elementsToRemove = Arrays.asList(2, 4);
        List<Integer> expected = Arrays.asList(1, 3, 5);

        List<Integer> result = Util.remove(list, elementsToRemove);

        assertEquals(expected, result);
    }

    @Test
    public void testRemoveAllElementsFromList() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> elementsToRemove = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> expected = Collections.emptyList();

        List<Integer> result = Util.remove(list, elementsToRemove);

        assertEquals(expected, result);
    }

    @Test
    public void testRemoveNoElementsFromList() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> elementsToRemove = Collections.emptyList();
        List<Integer> expected = Arrays.asList(1, 2, 3, 4, 5);

        List<Integer> result = Util.remove(list, elementsToRemove);

        assertEquals(expected, result);
    }

    @Test
    public void testRemoveNonExistentElementsFromList() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> elementsToRemove = Arrays.asList(6, 7);
        List<Integer> expected = Arrays.asList(1, 2, 3, 4, 5);

        List<Integer> result = Util.remove(list, elementsToRemove);

        assertEquals(expected, result);
    }
}
