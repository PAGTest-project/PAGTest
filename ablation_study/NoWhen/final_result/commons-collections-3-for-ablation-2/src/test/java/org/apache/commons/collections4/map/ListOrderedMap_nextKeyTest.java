
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ListOrderedMap_nextKeyTest {
    private ListOrderedMap<String, String> listOrderedMap;

    @BeforeEach
    public void setUp() {
        listOrderedMap = new ListOrderedMap<>();
        listOrderedMap.put("key1", "value1");
        listOrderedMap.put("key2", "value2");
        listOrderedMap.put("key3", "value3");
    }

    @Test
    public void testNextKeyWithValidKey() {
        assertEquals("key2", listOrderedMap.nextKey("key1"));
        assertEquals("key3", listOrderedMap.nextKey("key2"));
    }

    @Test
    public void testNextKeyWithLastKey() {
        assertNull(listOrderedMap.nextKey("key3"));
    }

    @Test
    public void testNextKeyWithInvalidKey() {
        assertNull(listOrderedMap.nextKey("nonexistentKey"));
    }

    @Test
    public void testNextKeyWithEmptyMap() {
        listOrderedMap.clear();
        assertNull(listOrderedMap.nextKey("key1"));
    }
}
