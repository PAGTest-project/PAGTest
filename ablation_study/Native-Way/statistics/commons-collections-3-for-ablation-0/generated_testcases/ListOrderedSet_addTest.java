
package org.apache.commons.collections4.set;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

public class ListOrderedSet_addTest {

    private ListOrderedSet<String> listOrderedSet;

    @BeforeEach
    public void setUp() {
        listOrderedSet = new ListOrderedSet<>();
    }

    @Test
    public void testAddSuccess() {
        assertTrue(listOrderedSet.add("A"));
        assertEquals(1, listOrderedSet.size());
        assertTrue(listOrderedSet.contains("A"));
    }

    @Test
    public void testAddDuplicate() {
        listOrderedSet.add("A");
        assertFalse(listOrderedSet.add("A"));
        assertEquals(1, listOrderedSet.size());
    }

    @Test
    public void testAddMultiple() {
        assertTrue(listOrderedSet.add("A"));
        assertTrue(listOrderedSet.add("B"));
        assertTrue(listOrderedSet.add("C"));
        assertEquals(3, listOrderedSet.size());
        assertTrue(listOrderedSet.contains("A"));
        assertTrue(listOrderedSet.contains("B"));
        assertTrue(listOrderedSet.contains("C"));
    }

    @Test
    public void testAddNull() {
        assertThrows(NullPointerException.class, () -> listOrderedSet.add(null));
    }

    @Test
    public void testAddAll() {
        List<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");
        assertTrue(listOrderedSet.addAll(list));
        assertEquals(3, listOrderedSet.size());
        assertTrue(listOrderedSet.contains("A"));
        assertTrue(listOrderedSet.contains("B"));
        assertTrue(listOrderedSet.contains("C"));
    }

    @Test
    public void testAddAllWithDuplicates() {
        List<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("A");
        assertTrue(listOrderedSet.addAll(list));
        assertEquals(2, listOrderedSet.size());
        assertTrue(listOrderedSet.contains("A"));
        assertTrue(listOrderedSet.contains("B"));
    }

    @Test
    public void testAddAllEmptyCollection() {
        List<String> list = new ArrayList<>();
        assertFalse(listOrderedSet.addAll(list));
        assertEquals(0, listOrderedSet.size());
    }
}
