
package org.apache.commons.collections4.map;

import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class ListOrderedMap_putAllTest {

    @Test
    void testPutAll_IndexInBounds_NewKeys() {
        ListOrderedMap<String, String> map = new ListOrderedMap<>();
        Map<String, String> inputMap = new HashMap<>();
        inputMap.put("key1", "value1");
        inputMap.put("key2", "value2");

        map.putAll(0, inputMap);

        assertEquals(2, map.size());
        assertEquals("value1", map.get("key1"));
        assertEquals("value2", map.get("key2"));
    }

    @Test
    void testPutAll_IndexInBounds_ExistingKeys() {
        ListOrderedMap<String, String> map = new ListOrderedMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");

        Map<String, String> inputMap = new HashMap<>();
        inputMap.put("key1", "newValue1");
        inputMap.put("key3", "value3");

        map.putAll(1, inputMap);

        assertEquals(3, map.size());
        assertEquals("newValue1", map.get("key1"));
        assertEquals("value2", map.get("key2"));
        assertEquals("value3", map.get("key3"));
    }

    @Test
    void testPutAll_IndexOutOfBounds() {
        ListOrderedMap<String, String> map = new ListOrderedMap<>();
        Map<String, String> inputMap = new HashMap<>();
        inputMap.put("key1", "value1");

        assertThrows(IndexOutOfBoundsException.class, () -> map.putAll(2, inputMap));
    }
}
