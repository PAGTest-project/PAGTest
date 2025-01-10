
package org.apache.commons.collections4.map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class ListOrderedMap_nextKeyTest {

    private ListOrderedMap<String, String> map;

    @BeforeEach
    public void setUp() {
        map = new ListOrderedMap<>();
    }

    @Test
    public void testNextKey_ValidKey_ReturnsNextKey() {
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key3", "value3");

        assertEquals("key2", map.nextKey("key1"));
        assertEquals("key3", map.nextKey("key2"));
    }

    @Test
    public void testNextKey_LastKey_ReturnsNull() {
        map.put("key1", "value1");
        map.put("key2", "value2");

        assertNull(map.nextKey("key2"));
    }

    @Test
    public void testNextKey_InvalidKey_ReturnsNull() {
        map.put("key1", "value1");
        map.put("key2", "value2");

        assertNull(map.nextKey("key3"));
    }

    @Test
    public void testNextKey_EmptyMap_ReturnsNull() {
        assertNull(map.nextKey("key1"));
    }
}
