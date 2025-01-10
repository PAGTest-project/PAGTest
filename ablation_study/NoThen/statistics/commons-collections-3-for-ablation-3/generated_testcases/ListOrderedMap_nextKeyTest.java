
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ListOrderedMap_nextKeyTest {
    private ListOrderedMap<String, String> listOrderedMap;

    @BeforeEach
    public void setUp() {
        listOrderedMap = new ListOrderedMap<>();
    }

    @Test
    public void testNextKey_ValidKey() {
        listOrderedMap.put("key1", "value1");
        listOrderedMap.put("key2", "value2");
        listOrderedMap.put("key3", "value3");

        assertEquals("key2", listOrderedMap.nextKey("key1"));
        assertEquals("key3", listOrderedMap.nextKey("key2"));
    }

    @Test
    public void testNextKey_LastKey() {
        listOrderedMap.put("key1", "value1");
        listOrderedMap.put("key2", "value2");

        assertNull(listOrderedMap.nextKey("key2"));
    }

    @Test
    public void testNextKey_NonExistentKey() {
        listOrderedMap.put("key1", "value1");
        listOrderedMap.put("key2", "value2");

        assertNull(listOrderedMap.nextKey("key3"));
    }

    @Test
    public void testNextKey_EmptyMap() {
        assertNull(listOrderedMap.nextKey("key1"));
    }

    @Test
    public void testNextKey_AfterRemove() {
        listOrderedMap.put("key1", "value1");
        listOrderedMap.put("key2", "value2");
        listOrderedMap.put("key3", "value3");

        listOrderedMap.remove("key2");

        assertEquals("key3", listOrderedMap.nextKey("key1"));
        assertNull(listOrderedMap.nextKey("key3"));
    }
}
