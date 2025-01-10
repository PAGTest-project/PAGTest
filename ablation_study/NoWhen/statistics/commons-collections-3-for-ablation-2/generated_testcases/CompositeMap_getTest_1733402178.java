
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
    public void testGetAfterAddingNewMap() {
        Map<String, String> newMap = new HashMap<>();
        newMap.put("key5", "value5");
        compositeMap.addComposited(newMap);

        assertEquals("value5", compositeMap.get("key5"));
    }

    @Test
    public void testGetAfterRemovingKey() {
        compositeMap.remove("key1");
        assertNull(compositeMap.get("key1"));
    }

    @Test
    public void testGetAfterPuttingNewKey() {
        compositeMap.put("key6", "value6");
        assertEquals("value6", compositeMap.get("key6"));
    }

    @Test
    public void testGetAfterPuttingExistingKey() {
        compositeMap.put("key1", "newValue1");
        assertEquals("newValue1", compositeMap.get("key1"));
    }

    @Test
    public void testGetWithNullKey() {
        assertNull(compositeMap.get(null));
    }

    @Test
    public void testGetWithEmptyCompositeMap() {
        CompositeMap<String, String> emptyMap = new CompositeMap<>();
        assertNull(emptyMap.get("key1"));
    }

    @Test
    public void testGetWithSingleMap() {
        CompositeMap<String, String> singleMap = new CompositeMap<>(map1);
        assertEquals("value1", singleMap.get("key1"));
    }

    @Test
    public void testGetWithMultipleMaps() {
        Map<String, String> map3 = new HashMap<>();
        map3.put("key7", "value7");
        compositeMap.addComposited(map3);

        assertEquals("value1", compositeMap.get("key1"));
        assertEquals("value3", compositeMap.get("key3"));
        assertEquals("value7", compositeMap.get("key7"));
    }

    @Test
    public void testGetWithOverlappingKeys() {
        Map<String, String> overlappingMap = new HashMap<>();
        overlappingMap.put("key1", "overlapValue1");
        compositeMap.addComposited(overlappingMap);

        assertEquals("overlapValue1", compositeMap.get("key1"));
    }
}
