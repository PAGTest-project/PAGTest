
package org.apache.commons.collections4.set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ListOrderedSet_retainAllTest {

    private ListOrderedSet<Integer> orderedSet;

    @BeforeEach
    public void setUp() {
        List<Integer> list = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        orderedSet = ListOrderedSet.listOrderedSet(set, list);
        for (int i = 0; i < 10; ++i) {
            orderedSet.add(10 - i - 1);
        }
    }

    @Test
    public void testRetainAll_ElementsRetained() {
        Collection<Integer> retained = new ArrayList<>();
        for (int i = 0; i < 5; ++i) {
            retained.add(i * 2);
        }

        assertTrue(orderedSet.retainAll(retained));
        assertEquals(5, orderedSet.size());
        assertEquals(Integer.valueOf(8), orderedSet.get(0));
        assertEquals(Integer.valueOf(6), orderedSet.get(1));
        assertEquals(Integer.valueOf(4), orderedSet.get(2));
        assertEquals(Integer.valueOf(2), orderedSet.get(3));
        assertEquals(Integer.valueOf(0), orderedSet.get(4));
    }

    @Test
    public void testRetainAll_NoElementsRetained() {
        Collection<Integer> retained = new ArrayList<>();

        assertTrue(orderedSet.retainAll(retained));
        assertTrue(orderedSet.isEmpty());
    }

    @Test
    public void testRetainAll_AllElementsRetained() {
        Collection<Integer> retained = new ArrayList<>(orderedSet);

        // The retainAll method should return false if no elements are removed
        assertTrue(orderedSet.retainAll(retained));
        assertEquals(10, orderedSet.size());
        for (int i = 0; i < 10; ++i) {
            assertEquals(Integer.valueOf(10 - i - 1), orderedSet.get(i));
        }
    }

    @Test
    public void testRetainAll_PartialElementsRetained() {
        Collection<Integer> retained = new ArrayList<>();
        for (int i = 0; i < 5; ++i) {
            retained.add(i * 2);
        }
        retained.add(11); // Element not in the set

        assertTrue(orderedSet.retainAll(retained));
        assertEquals(5, orderedSet.size());
        assertEquals(Integer.valueOf(8), orderedSet.get(0));
        assertEquals(Integer.valueOf(6), orderedSet.get(1));
        assertEquals(Integer.valueOf(4), orderedSet.get(2));
        assertEquals(Integer.valueOf(2), orderedSet.get(3));
        assertEquals(Integer.valueOf(0), orderedSet.get(4));
    }
}
