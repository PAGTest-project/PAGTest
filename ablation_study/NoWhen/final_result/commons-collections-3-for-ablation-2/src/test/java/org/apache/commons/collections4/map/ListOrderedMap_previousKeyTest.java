
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ListOrderedMap_previousKeyTest {
    private ListOrderedMap<String, String> listOrderedMap;

    @BeforeEach
    public void setUp() {
        listOrderedMap = new ListOrderedMap<>();
        listOrderedMap.put("key1", "value1");
        listOrderedMap.put("key2", "value2");
        listOrderedMap.put("key3", "value3");
    }

    @Test
    public void testPreviousKeyWithExistingKey() {
        assertEquals("key2", listOrderedMap.previousKey("key3"));
    }

    @Test
    public void testPreviousKeyWithFirstKey() {
        assertNull(listOrderedMap.previousKey("key1"));
    }

    @Test
    public void testPreviousKeyWithNonExistingKey() {
        assertNull(listOrderedMap.previousKey("nonExistingKey"));
    }
}
