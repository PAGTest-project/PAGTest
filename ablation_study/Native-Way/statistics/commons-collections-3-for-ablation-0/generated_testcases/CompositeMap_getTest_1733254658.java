
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CompositeMap_getTest {

    private CompositeMap<String, String> compositeMap;
    private Map<String, String> map1;
    private Map<String, String> map2;

    @BeforeEach
    public void setUp() {
        map1 = new HashMap<>();
        map1.put("key1", "value1");
        map1.put("key2", "value2");

        map2 = new HashMap<>();
        map2.put("key3", "value3");
        map2.put("key4", "value4");

        compositeMap = new CompositeMap<>(map1, map2);
    }

    @Test
    public void testGetExistingKey() {
        assertEquals("value1", compositeMap.get("key1"));
        assertEquals("value3", compositeMap.get("key3"));
    }

    @Test
    public void testGetNonExistingKey() {
        assertNull(compositeMap.get("key5"));
    }

    @Test
    public void testGetAfterRemove() {
        compositeMap.remove("key1");
        assertNull(compositeMap.get("key1"));
    }

    @Test
    public void testGetAfterPut() {
        compositeMap.put("key5", "value5");
        assertEquals("value5", compositeMap.get("key5"));
    }

    @Test
    public void testGetAfterPutAll() {
        Map<String, String> newMap = new HashMap<>();
        newMap.put("key6", "value6");
        newMap.put("key7", "value7");
        compositeMap.putAll(newMap);
        assertEquals("value6", compositeMap.get("key6"));
        assertEquals("value7", compositeMap.get("key7"));
    }

    @Test
    public void testGetAfterClear() {
        compositeMap.clear();
        assertNull(compositeMap.get("key1"));
        assertNull(compositeMap.get("key3"));
    }

    @Test
    public void testGetAfterAddComposited() {
        Map<String, String> newMap = new HashMap<>();
        newMap.put("key8", "value8");
        compositeMap.addComposited(newMap);
        assertEquals("value8", compositeMap.get("key8"));
    }

    @Test
    public void testGetAfterRemoveComposited() {
        compositeMap.removeComposited(map1);
        assertNull(compositeMap.get("key1"));
        assertEquals("value3", compositeMap.get("key3"));
    }

    @Test
    public void testContainsKey() {
        assertTrue(compositeMap.containsKey("key1"));
        assertFalse(compositeMap.containsKey("key5"));
    }

    @Test
    public void testRemove() {
        assertEquals("value1", compositeMap.remove("key1"));
        assertNull(compositeMap.get("key1"));
    }

    @Test
    public void testPut() {
        assertEquals("value1", compositeMap.put("key1", "newValue1"));
        assertEquals("newValue1", compositeMap.get("key1"));
    }

    @Test
    public void testPutAll() {
        Map<String, String> newMap = new HashMap<>();
        newMap.put("key1", "newValue1");
        newMap.put("key5", "value5");
        compositeMap.putAll(newMap);
        assertEquals("newValue1", compositeMap.get("key1"));
        assertEquals("value5", compositeMap.get("key5"));
    }

    @Test
    public void testClear() {
        compositeMap.clear();
        assertTrue(compositeMap.isEmpty());
    }

    @Test
    public void testEntrySet() {
        assertEquals(4, compositeMap.entrySet().size());
    }

    @Test
    public void testKeySet() {
        assertEquals(4, compositeMap.keySet().size());
    }

    @Test
    public void testValues() {
        assertEquals(4, compositeMap.values().size());
    }
}
