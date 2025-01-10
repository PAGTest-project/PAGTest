
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashMap;

public class ListOrderedMap_nextKeyTest {
    private ListOrderedMap<String, String> listOrderedMap;

    @BeforeEach
    public void setUp() {
        listOrderedMap = new ListOrderedMap<>(new HashMap<>());
        listOrderedMap.put("key1", "value1");
        listOrderedMap.put("key2", "value2");
        listOrderedMap.put("key3", "value3");
    }

    @Test
    public void testNextKey_ValidKey() {
        assertEquals("key2", listOrderedMap.nextKey("key1"));
        assertEquals("key3", listOrderedMap.nextKey("key2"));
    }

    @Test
    public void testNextKey_LastKey() {
        assertNull(listOrderedMap.nextKey("key3"));
    }

    @Test
    public void testNextKey_NonExistentKey() {
        assertNull(listOrderedMap.nextKey("nonExistentKey"));
    }

    @Test
    public void testNextKey_EmptyMap() {
        ListOrderedMap<String, String> emptyMap = new ListOrderedMap<>(new HashMap<>());
        assertNull(emptyMap.nextKey("anyKey"));
    }
}
