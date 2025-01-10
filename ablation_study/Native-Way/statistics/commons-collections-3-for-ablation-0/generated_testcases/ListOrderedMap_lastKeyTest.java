
package org.apache.commons.collections4.map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class ListOrderedMap_lastKeyTest {
    private ListOrderedMap<Integer, String> listOrderedMap;

    @BeforeEach
    public void setUp() {
        listOrderedMap = new ListOrderedMap<>();
    }

    @Test
    public void testLastKey_EmptyMap() {
        assertThrows(NoSuchElementException.class, () -> listOrderedMap.lastKey());
    }

    @Test
    public void testLastKey_NonEmptyMap() {
        listOrderedMap.put(1, "One");
        listOrderedMap.put(2, "Two");
        listOrderedMap.put(3, "Three");

        assertEquals(3, listOrderedMap.lastKey());
    }

    @Test
    public void testLastKey_AfterRemoval() {
        listOrderedMap.put(1, "One");
        listOrderedMap.put(2, "Two");
        listOrderedMap.put(3, "Three");

        listOrderedMap.remove(2); // Remove the key at index 2
        assertEquals(3, listOrderedMap.lastKey()); // The last key should now be 3
    }

    @Test
    public void testLastKey_AfterAddition() {
        listOrderedMap.put(1, "One");
        listOrderedMap.put(2, "Two");

        listOrderedMap.put(3, "Three");
        assertEquals(3, listOrderedMap.lastKey());
    }
}
