
package org.apache.commons.collections4.set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ListOrderedSet_addAllTest {

    private ListOrderedSet<Integer> listOrderedSet;

    @BeforeEach
    public void setUp() {
        listOrderedSet = new ListOrderedSet<>();
    }

    @Test
    public void testAddAll() {
        List<Integer> collection = Arrays.asList(1, 2, 3);
        assertTrue(listOrderedSet.addAll(collection));
        assertEquals(3, listOrderedSet.size());
        assertEquals(Integer.valueOf(1), listOrderedSet.get(0));
        assertEquals(Integer.valueOf(2), listOrderedSet.get(1));
        assertEquals(Integer.valueOf(3), listOrderedSet.get(2));
    }

    @Test
    public void testAddAllWithDuplicates() {
        List<Integer> collection = Arrays.asList(1, 2, 2, 3);
        assertTrue(listOrderedSet.addAll(collection));
        assertEquals(3, listOrderedSet.size());
        assertEquals(Integer.valueOf(1), listOrderedSet.get(0));
        assertEquals(Integer.valueOf(2), listOrderedSet.get(1));
        assertEquals(Integer.valueOf(3), listOrderedSet.get(2));
    }

    @Test
    public void testAddAllEmptyCollection() {
        List<Integer> collection = new ArrayList<>();
        assertFalse(listOrderedSet.addAll(collection));
        assertEquals(0, listOrderedSet.size());
    }

    @Test
    public void testAddAllWithExistingElements() {
        listOrderedSet.add(1);
        listOrderedSet.add(2);
        List<Integer> collection = Arrays.asList(2, 3, 4);
        assertTrue(listOrderedSet.addAll(collection));
        assertEquals(4, listOrderedSet.size());
        assertEquals(Integer.valueOf(1), listOrderedSet.get(0));
        assertEquals(Integer.valueOf(2), listOrderedSet.get(1));
        assertEquals(Integer.valueOf(3), listOrderedSet.get(2));
        assertEquals(Integer.valueOf(4), listOrderedSet.get(3));
    }
}
