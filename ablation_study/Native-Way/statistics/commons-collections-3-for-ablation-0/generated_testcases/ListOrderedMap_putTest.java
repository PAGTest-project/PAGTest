
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;

public class ListOrderedMap_putTest {
    private ListOrderedMap<String, String> listOrderedMap;

    @BeforeEach
    public void setUp() {
        listOrderedMap = new ListOrderedMap<>();
    }

    @Test
    public void testPutAtIndexValidIndex() {
        listOrderedMap.put("key1", "value1");
        listOrderedMap.put("key2", "value2");
        assertEquals("value1", listOrderedMap.put(0, "key1", "newValue1"));
        assertEquals("newValue1", listOrderedMap.get("key1"));
    }

    @Test
    public void testPutAtIndexInvalidIndex() {
        listOrderedMap.put("key1", "value1");
        assertThrows(IndexOutOfBoundsException.class, () -> listOrderedMap.put(2, "key2", "value2"));
    }

    @Test
    public void testPutAtIndexNewKey() {
        listOrderedMap.put("key1", "value1");
        assertNull(listOrderedMap.put(1, "key2", "value2"));
        assertEquals("value2", listOrderedMap.get("key2"));
    }

    @Test
    public void testPutAtIndexExistingKey() {
        listOrderedMap.put("key1", "value1");
        listOrderedMap.put("key2", "value2");
        assertEquals("value2", listOrderedMap.put(1, "key2", "newValue2"));
        assertEquals("newValue2", listOrderedMap.get("key2"));
    }

    @Test
    public void testPutAtIndexExistingKeyBeforeIndex() {
        listOrderedMap.put("key1", "value1");
        listOrderedMap.put("key2", "value2");
        assertEquals("value1", listOrderedMap.put(1, "key1", "newValue1"));
        assertEquals("newValue1", listOrderedMap.get("key1"));
    }

    @Test
    public void testPutAtIndexExistingKeyAtSameIndex() {
        listOrderedMap.put("key1", "value1");
        listOrderedMap.put("key2", "value2");
        assertEquals("value2", listOrderedMap.put(1, "key2", "newValue2"));
        assertEquals("newValue2", listOrderedMap.get("key2"));
    }

    @Test
    public void testPutAtIndexExistingKeyAfterIndex() {
        listOrderedMap.put("key1", "value1");
        listOrderedMap.put("key2", "value2");
        assertEquals("value2", listOrderedMap.put(0, "key2", "newValue2"));
        assertEquals("newValue2", listOrderedMap.get("key2"));
    }
}
