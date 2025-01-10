
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
    public void testPutAllAtIndexValid() {
        Map<String, String> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");

        listOrderedMap.putAll(0, map);

        assertAll("Check map contents",
            () -> assertEquals("value1", listOrderedMap.get("key1")),
            () -> assertEquals("value2", listOrderedMap.get("key2")),
            () -> assertEquals(2, listOrderedMap.size())
        );
    }

    @Test
    public void testPutAllAtIndexInvalid() {
        Map<String, String> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");

        assertThrows(IndexOutOfBoundsException.class, () -> listOrderedMap.putAll(-1, map));
        assertThrows(IndexOutOfBoundsException.class, () -> listOrderedMap.putAll(1, map));
    }

    @Test
    public void testPutAllAtIndexWithExistingKeys() {
        listOrderedMap.put("key1", "value1");
        listOrderedMap.put("key2", "value2");

        Map<String, String> map = new HashMap<>();
        map.put("key1", "newValue1");
        map.put("key3", "value3");

        listOrderedMap.putAll(1, map);

        assertAll("Check map contents",
            () -> assertEquals("newValue1", listOrderedMap.get("key1")),
            () -> assertEquals("value2", listOrderedMap.get("key2")),
            () -> assertEquals("value3", listOrderedMap.get("key3")),
            () -> assertEquals(3, listOrderedMap.size())
        );
    }

    @Test
    public void testPutAllAtIndexEmptyMap() {
        Map<String, String> map = new HashMap<>();

        listOrderedMap.putAll(0, map);

        assertTrue(listOrderedMap.isEmpty());
    }
}
