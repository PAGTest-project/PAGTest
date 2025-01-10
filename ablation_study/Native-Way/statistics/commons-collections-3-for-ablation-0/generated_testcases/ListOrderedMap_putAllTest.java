
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ListOrderedMap_putAllTest {

    private ListOrderedMap<String, String> listOrderedMap;

    @BeforeEach
    public void setUp() {
        listOrderedMap = new ListOrderedMap<>();
    }

    @Test
    public void testPutAll_IndexOutOfBoundsException() {
        Map<String, String> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");

        assertAll(
            () -> assertThrows(IndexOutOfBoundsException.class, () -> listOrderedMap.putAll(-1, map)),
            () -> assertThrows(IndexOutOfBoundsException.class, () -> listOrderedMap.putAll(1, map))
        );
    }

    @Test
    public void testPutAll_ValidIndex() {
        Map<String, String> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");

        listOrderedMap.putAll(0, map);

        assertAll(
            () -> assertEquals(2, listOrderedMap.size()),
            () -> assertEquals("value1", listOrderedMap.get("key1")),
            () -> assertEquals("value2", listOrderedMap.get("key2"))
        );
    }

    @Test
    public void testPutAll_ReplaceExistingKey() {
        listOrderedMap.put("key1", "oldValue");
        Map<String, String> map = new HashMap<>();
        map.put("key1", "newValue");
        map.put("key2", "value2");

        listOrderedMap.putAll(0, map);

        assertAll(
            () -> assertEquals(2, listOrderedMap.size()),
            () -> assertEquals("newValue", listOrderedMap.get("key1")),
            () -> assertEquals("value2", listOrderedMap.get("key2"))
        );
    }

    @Test
    public void testPutAll_EmptyMap() {
        Map<String, String> map = new HashMap<>();

        listOrderedMap.putAll(0, map);

        assertTrue(listOrderedMap.isEmpty());
    }

    @Test
    public void testPutAll_MultipleCalls() {
        Map<String, String> map1 = new HashMap<>();
        map1.put("key1", "value1");
        map1.put("key2", "value2");

        Map<String, String> map2 = new HashMap<>();
        map2.put("key3", "value3");
        map2.put("key4", "value4");

        listOrderedMap.putAll(0, map1);
        listOrderedMap.putAll(2, map2);

        assertAll(
            () -> assertEquals(4, listOrderedMap.size()),
            () -> assertEquals("value1", listOrderedMap.get("key1")),
            () -> assertEquals("value2", listOrderedMap.get("key2")),
            () -> assertEquals("value3", listOrderedMap.get("key3")),
            () -> assertEquals("value4", listOrderedMap.get("key4"))
        );
    }

    @Test
    public void testPutAll_RemoveAfterPut() {
        Map<String, String> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");

        listOrderedMap.putAll(0, map);
        listOrderedMap.remove("key1");

        assertAll(
            () -> assertEquals(1, listOrderedMap.size()),
            () -> assertFalse(listOrderedMap.containsKey("key1")),
            () -> assertEquals("value2", listOrderedMap.get("key2"))
        );
    }
}
