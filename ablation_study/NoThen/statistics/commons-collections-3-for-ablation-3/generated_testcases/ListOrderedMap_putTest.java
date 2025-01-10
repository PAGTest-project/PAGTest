
package org.apache.commons.collections4.map;

import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ListOrderedMap_putTest {

    @Test
    void testPutNewKey() {
        ListOrderedMap<String, String> map = new ListOrderedMap<>(new HashMap<>());
        map.put(0, "key1", "value1");
        assertEquals("value1", map.get("key1"));
        assertEquals(1, map.size());
        assertEquals("key1", map.keySet().iterator().next());
    }

    @Test
    void testPutExistingKey() {
        ListOrderedMap<String, String> map = new ListOrderedMap<>(new HashMap<>());
        map.put(0, "key1", "value1");
        String result = map.put(0, "key1", "newValue1");
        assertEquals("value1", result);
        assertEquals("newValue1", map.get("key1"));
        assertEquals(1, map.size());
        assertEquals("key1", map.keySet().iterator().next());
    }

    @Test
    void testPutIndexOutOfBounds() {
        ListOrderedMap<String, String> map = new ListOrderedMap<>(new HashMap<>());
        assertThrows(IndexOutOfBoundsException.class, () -> map.put(-1, "key1", "value1"));
        assertThrows(IndexOutOfBoundsException.class, () -> map.put(1, "key1", "value1"));
    }

    @Test
    void testPutExistingKeyWithDifferentIndex() {
        ListOrderedMap<String, String> map = new ListOrderedMap<>(new HashMap<>());
        map.put(0, "key1", "value1");
        map.put(1, "key2", "value2");
        String result = map.put(1, "key1", "newValue1");
        assertEquals("value1", result);
        assertEquals("newValue1", map.get("key1"));
        assertEquals(2, map.size());
        assertEquals("key2", map.keySet().iterator().next());
        assertEquals("key1", map.keySet().iterator().next());
    }
}
