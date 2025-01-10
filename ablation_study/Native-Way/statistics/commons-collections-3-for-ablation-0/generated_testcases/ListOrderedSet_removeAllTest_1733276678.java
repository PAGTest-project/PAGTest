
package org.apache.commons.collections4.set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ListOrderedSet_removeAllTest {

    private ListOrderedSet<Integer> orderedSet;

    @BeforeEach
    public void setUp() {
        Set<Integer> set = new HashSet<>();
        List<Integer> list = new ArrayList<>();
        orderedSet = ListOrderedSet.listOrderedSet(set, list);
        for (int i = 0; i < 10; i++) {
            orderedSet.add(i);
        }
    }

    @Test
    public void testRemoveAll_AllElementsPresent() {
        Collection<Integer> toRemove = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            toRemove.add(i);
        }

        assertTrue(orderedSet.removeAll(toRemove));
        assertEquals(5, orderedSet.size());
    }

    @Test
    public void testRemoveAll_SomeElementsNotPresent() {
        Collection<Integer> toRemove = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            toRemove.add(i);
        }
        toRemove.add(10); // Element not present in the set

        assertTrue(orderedSet.removeAll(toRemove));
        assertEquals(5, orderedSet.size());
    }

    @Test
    public void testRemoveAll_NoElementsPresent() {
        Collection<Integer> toRemove = new ArrayList<>();
        for (int i = 10; i < 20; i++) {
            toRemove.add(i);
        }

        assertFalse(orderedSet.removeAll(toRemove));
        assertEquals(10, orderedSet.size());
    }

    @Test
    public void testRemoveAll_EmptyCollection() {
        Collection<Integer> toRemove = new ArrayList<>();

        assertFalse(orderedSet.removeAll(toRemove));
        assertEquals(10, orderedSet.size());
    }

    @Test
    public void testRemoveAll_NullCollection() {
        assertFalse(orderedSet.removeAll(null));
        assertEquals(10, orderedSet.size());
    }
}
