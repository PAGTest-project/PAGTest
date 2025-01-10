
package org.apache.commons.collections4.list;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SetUniqueList_removeAllTest {

    private SetUniqueList<Integer> uniqueList;

    @BeforeEach
    public void setUp() {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        uniqueList = SetUniqueList.setUniqueList(list);
    }

    @Test
    public void testRemoveAll_AllElementsRemoved() {
        Collection<Integer> toRemove = Arrays.asList(1, 3, 5);
        assertTrue(uniqueList.removeAll(toRemove));
        assertEquals(2, uniqueList.size());
        assertFalse(uniqueList.contains(1));
        assertTrue(uniqueList.contains(2));
        assertFalse(uniqueList.contains(3));
        assertTrue(uniqueList.contains(4));
        assertFalse(uniqueList.contains(5));
    }

    @Test
    public void testRemoveAll_NoElementsRemoved() {
        Collection<Integer> toRemove = Arrays.asList(6, 7, 8);
        assertFalse(uniqueList.removeAll(toRemove));
        assertEquals(5, uniqueList.size());
        assertTrue(uniqueList.contains(1));
        assertTrue(uniqueList.contains(2));
        assertTrue(uniqueList.contains(3));
        assertTrue(uniqueList.contains(4));
        assertTrue(uniqueList.contains(5));
    }

    @Test
    public void testRemoveAll_SomeElementsRemoved() {
        Collection<Integer> toRemove = Arrays.asList(2, 4, 6);
        assertTrue(uniqueList.removeAll(toRemove));
        assertEquals(3, uniqueList.size());
        assertTrue(uniqueList.contains(1));
        assertFalse(uniqueList.contains(2));
        assertTrue(uniqueList.contains(3));
        assertFalse(uniqueList.contains(4));
        assertTrue(uniqueList.contains(5));
    }

    @Test
    public void testRemoveAll_EmptyCollection() {
        Collection<Integer> toRemove = new ArrayList<>();
        assertFalse(uniqueList.removeAll(toRemove));
        assertEquals(5, uniqueList.size());
        assertTrue(uniqueList.contains(1));
        assertTrue(uniqueList.contains(2));
        assertTrue(uniqueList.contains(3));
        assertTrue(uniqueList.contains(4));
        assertTrue(uniqueList.contains(5));
    }

    @Test
    public void testRemoveAll_NullCollection() {
        assertThrows(NullPointerException.class, () -> uniqueList.removeAll(null));
    }
}
