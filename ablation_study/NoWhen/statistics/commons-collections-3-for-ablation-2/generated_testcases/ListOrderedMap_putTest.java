
package org.apache.commons.collections4.map;

import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class ListOrderedMap_putTest {

    @Test
    void testPut_NewKey() {
        ListOrderedMap<String, String> map = new ListOrderedMap<>(new HashMap<>());
        map.put(0, "key1", "value1");
        assertEquals("value1", map.get("key1"));
        assertEquals(1, map.size());
    }

    @Test
    void testPut_ExistingKey() {
        ListOrderedMap<String, String> map = new ListOrderedMap<>(new HashMap<>());
        map.put(0, "key1", "value1");
        String result = map.put(0, "key1", "newValue1");
        assertEquals("value1", result);
        assertEquals("newValue1", map.get("key1"));
        assertEquals(1, map.size());
    }

    @Test
    void testPut_IndexOutOfBounds() {
        ListOrderedMap<String, String> map = new ListOrderedMap<>(new HashMap<>());
        assertThrows(IndexOutOfBoundsException.class, () -> map.put(-1, "key1", "value1"));
        assertThrows(IndexOutOfBoundsException.class, () -> map.put(1, "key1", "value1"));
    }

    @Test
    void testPut_ExistingKeyWithDifferentIndex() {
        ListOrderedMap<String, String> map = new ListOrderedMap<>(new HashMap<>());
        map.put(0, "key1", "value1");
        map.put(1, "key2", "value2");
        String result = map.put(1, "key1", "newValue1");
        assertEquals("value1", result);
        assertEquals("newValue1", map.get("key1"));
        assertEquals("value2", map.get("key2"));
        assertEquals(2, map.size());
    }
}
