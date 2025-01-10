
package com.github.davidmoten.rtree.internal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class Util_replaceTest {

    @Test
    public void testReplaceElementExists() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4);
        List<Integer> replacements = Arrays.asList(5, 6);
        List<Integer> result = Util.replace(list, 3, replacements);
        assertEquals(Arrays.asList(1, 2, 4, 5, 6), result);
    }

    @Test
    public void testReplaceElementDoesNotExist() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4);
        List<Integer> replacements = Arrays.asList(5, 6);
        List<Integer> result = Util.replace(list, 7, replacements);
        assertEquals(Arrays.asList(1, 2, 3, 4, 5, 6), result);
    }

    @Test
    public void testReplaceEmptyList() {
        List<Integer> list = Arrays.asList();
        List<Integer> replacements = Arrays.asList(5, 6);
        List<Integer> result = Util.replace(list, 3, replacements);
        assertEquals(Arrays.asList(5, 6), result);
    }

    @Test
    public void testReplaceEmptyReplacements() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4);
        List<Integer> replacements = Arrays.asList();
        List<Integer> result = Util.replace(list, 3, replacements);
        assertEquals(Arrays.asList(1, 2, 4), result);
    }

    @Test
    public void testReplaceNoChange() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4);
        List<Integer> replacements = Arrays.asList();
        List<Integer> result = Util.replace(list, 5, replacements);
        assertEquals(Arrays.asList(1, 2, 3, 4), result);
    }
}
