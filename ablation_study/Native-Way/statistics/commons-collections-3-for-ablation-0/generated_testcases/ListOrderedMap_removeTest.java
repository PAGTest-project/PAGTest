
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ListOrderedMap_removeTest {
    private ListOrderedMap<String, String> listOrderedMap;

    @BeforeEach
    public void setUp() {
        listOrderedMap = new ListOrderedMap<>(new HashMap<>());
    }

    @Test
    public void testRemoveExistingKey() {
        listOrderedMap.put("key1", "value1");
        listOrderedMap.put("key2", "value2");

        assertEquals("value1", listOrderedMap.remove("key1"));
        assertNull(listOrderedMap.remove("key1"));
    }

    @Test
    public void testRemoveNonExistingKey() {
        listOrderedMap.put("key1", "value1");

        assertNull(listOrderedMap.remove("key2"));
    }

    @Test
    public void testRemoveFromEmptyMap() {
        assertNull(listOrderedMap.remove("key1"));
    }

    @Test
    public void testRemoveNullKey() {
        listOrderedMap.put(null, "value1");

        assertEquals("value1", listOrderedMap.remove(null));
        assertNull(listOrderedMap.remove(null));
    }

    @Test
    public void testRemoveAfterClear() {
        listOrderedMap.put("key1", "value1");
        listOrderedMap.clear();

        assertNull(listOrderedMap.remove("key1"));
    }
}
