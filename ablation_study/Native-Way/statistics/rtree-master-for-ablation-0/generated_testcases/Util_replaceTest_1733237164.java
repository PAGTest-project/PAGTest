
package com.github.davidmoten.rtree.internal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class Util_replaceTest {

    @Test
    public void testReplaceElementNotInList() {
        List<Integer> list = Arrays.asList(1, 2, 3);
        List<Integer> replacements = Arrays.asList(4, 5);
        List<Integer> result = Util.replace(list, 6, replacements);
        assertEquals(Arrays.asList(1, 2, 3, 4, 5), result);
    }

    @Test
    public void testReplaceElementInList() {
        List<Integer> list = Arrays.asList(1, 2, 3);
        List<Integer> replacements = Arrays.asList(4, 5);
        List<Integer> result = Util.replace(list, 2, replacements);
        assertEquals(Arrays.asList(1, 3, 4, 5), result);
    }

    @Test
    public void testReplaceElementWithEmptyReplacements() {
        List<Integer> list = Arrays.asList(1, 2, 3);
        List<Integer> replacements = Arrays.asList();
        List<Integer> result = Util.replace(list, 2, replacements);
        assertEquals(Arrays.asList(1, 3), result);
    }

    @Test
    public void testReplaceElementWithNullReplacements() {
        List<Integer> list = Arrays.asList(1, 2, 3);
        List<Integer> replacements = null;
        List<Integer> result = Util.replace(list, 2, replacements);
        assertEquals(Arrays.asList(1, 3), result);
    }

    @Test
    public void testReplaceElementWithNullList() {
        List<Integer> list = null;
        List<Integer> replacements = Arrays.asList(4, 5);
        List<Integer> result = Util.replace(list, 2, replacements);
        assertTrue(result.isEmpty());
    }
}
