
package com.github.davidmoten.rtree.internal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class Util_replaceTest {

    @Test
    public void testReplaceElementNotInList() {
        List<String> list = Arrays.asList("a", "b", "c");
        List<String> replacements = Arrays.asList("d", "e");
        List<String> result = Util.replace(list, "x", replacements);

        assertEquals(5, result.size());
        assertTrue(result.containsAll(Arrays.asList("a", "b", "c", "d", "e")));
    }

    @Test
    public void testReplaceElementInList() {
        List<String> list = Arrays.asList("a", "b", "c");
        List<String> replacements = Arrays.asList("d", "e");
        List<String> result = Util.replace(list, "b", replacements);

        assertEquals(4, result.size());
        assertTrue(result.containsAll(Arrays.asList("a", "c", "d", "e")));
    }

    @Test
    public void testReplaceWithEmptyReplacements() {
        List<String> list = Arrays.asList("a", "b", "c");
        List<String> replacements = Arrays.asList();
        List<String> result = Util.replace(list, "b", replacements);

        assertEquals(2, result.size());
        assertTrue(result.containsAll(Arrays.asList("a", "c")));
    }

    @Test
    public void testReplaceWithEmptyList() {
        List<String> list = Arrays.asList();
        List<String> replacements = Arrays.asList("d", "e");
        List<String> result = Util.replace(list, "x", replacements);

        assertEquals(2, result.size());
        assertTrue(result.containsAll(Arrays.asList("d", "e")));
    }
}
