
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ListOrderedMap_removeTest {

    private ListOrderedMap<String, String> listOrderedMap;

    @BeforeEach
    public void setUp() {
        Map<String, String> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        listOrderedMap = new ListOrderedMap<>(map);
    }

    @Test
    public void testRemoveExistingKey() {
        String result = listOrderedMap.remove("key1");
        assertEquals("value1", result);
        assertNull(listOrderedMap.get("key1"));
    }

    @Test
    public void testRemoveNonExistingKey() {
        String result = listOrderedMap.remove("key3");
        assertNull(result);
    }
}
