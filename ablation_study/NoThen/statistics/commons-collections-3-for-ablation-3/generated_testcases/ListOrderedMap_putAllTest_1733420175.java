
package org.apache.commons.collections4.map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ListOrderedMap_putAllTest {

    private ListOrderedMap<Integer, String> map;

    @BeforeEach
    void setUp() {
        map = new ListOrderedMap<>();
    }

    @Test
    void testPutAll_IndexOutOfBounds() {
        Map<Integer, String> inputMap = new HashMap<>();
        inputMap.put(1, "one");
        inputMap.put(2, "two");

        assertThrows(IndexOutOfBoundsException.class, () -> map.putAll(-1, inputMap));
        assertThrows(IndexOutOfBoundsException.class, () -> map.putAll(1, inputMap));
    }

    @Test
    void testPutAll_AddNewEntries() {
        Map<Integer, String> inputMap = new HashMap<>();
        inputMap.put(1, "one");
        inputMap.put(2, "two");

        map.putAll(0, inputMap);

        assertEquals(2, map.size());
        assertEquals("one", map.get(1));
        assertEquals("two", map.get(2));
    }

    @Test
    void testPutAll_ReplaceExistingEntries() {
        map.put(1, "oldOne");
        map.put(2, "oldTwo");

        Map<Integer, String> inputMap = new HashMap<>();
        inputMap.put(1, "newOne");
        inputMap.put(2, "newTwo");

        map.putAll(0, inputMap);

        assertEquals(2, map.size());
        assertEquals("newOne", map.get(1));
        assertEquals("newTwo", map.get(2));
    }
}
