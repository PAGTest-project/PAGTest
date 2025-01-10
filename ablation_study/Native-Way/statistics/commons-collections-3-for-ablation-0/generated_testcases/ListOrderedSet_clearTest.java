
package org.apache.commons.collections4.set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ListOrderedSet_clearTest {

    private ListOrderedSet<Integer> listOrderedSet;

    @BeforeEach
    public void setUp() {
        Set<Integer> set = new HashSet<>();
        List<Integer> list = new ArrayList<>();
        listOrderedSet = new ListOrderedSet<>(set, list);
    }

    @Test
    public void testClear() {
        // Add elements to the set
        listOrderedSet.add(1);
        listOrderedSet.add(2);
        listOrderedSet.add(3);

        // Ensure the set is not empty before clearing
        assertEquals(3, listOrderedSet.size());

        // Clear the set
        listOrderedSet.clear();

        // Ensure the set is empty after clearing
        assertTrue(listOrderedSet.isEmpty());
        assertEquals(0, listOrderedSet.size());
    }

    @Test
    public void testClearEmptySet() {
        // Ensure the set is empty before clearing
        assertTrue(listOrderedSet.isEmpty());

        // Clear the set
        listOrderedSet.clear();

        // Ensure the set remains empty after clearing
        assertTrue(listOrderedSet.isEmpty());
        assertEquals(0, listOrderedSet.size());
    }
}
