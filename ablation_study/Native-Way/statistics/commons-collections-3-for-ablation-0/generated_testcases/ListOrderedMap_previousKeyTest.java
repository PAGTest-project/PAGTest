
package org.apache.commons.collections4.map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

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
    public void testPreviousKey_ValidKey() {
        assertEquals("key2", listOrderedMap.previousKey("key3"));
        assertEquals("key1", listOrderedMap.previousKey("key2"));
    }

    @Test
    public void testPreviousKey_FirstKey() {
        assertNull(listOrderedMap.previousKey("key1"));
    }

    @Test
    public void testPreviousKey_NonExistentKey() {
        assertNull(listOrderedMap.previousKey("nonExistentKey"));
    }

    @Test
    public void testPreviousKey_EmptyMap() {
        listOrderedMap.clear();
        assertNull(listOrderedMap.previousKey("key1"));
    }
}
