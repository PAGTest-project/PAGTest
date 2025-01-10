
package com.github.davidmoten.rtree.internal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class Util_addTest {

    @Test
    public void testAddElementToList() {
        List<String> list = new ArrayList<>(Arrays.asList("a", "b", "c"));
        String element = "d";
        List<String> result = Util.add(list, element);
        assertEquals(4, result.size());
        assertTrue(result.contains(element));
    }

    @Test
    public void testAddElementToEmptyList() {
        List<String> list = new ArrayList<>();
        String element = "a";
        List<String> result = Util.add(list, element);
        assertEquals(1, result.size());
        assertTrue(result.contains(element));
    }

    @Test
    public void testAddElementToNullList() {
        List<String> list = null;
        String element = "a";
        try {
            Util.add(list, element);
        } catch (NullPointerException e) {
            assertTrue(true);
        }
    }

    @Test
    public void testAddNullElementToList() {
        List<String> list = new ArrayList<>(Arrays.asList("a", "b", "c"));
        String element = null;
        List<String> result = Util.add(list, element);
        assertEquals(4, result.size());
        assertTrue(result.contains(element));
    }
}
