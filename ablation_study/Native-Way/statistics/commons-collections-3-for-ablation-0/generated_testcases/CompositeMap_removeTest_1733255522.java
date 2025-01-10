
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CompositeMap_removeTest {

    private CompositeMap<String, String> compositeMap;
    private Map<String, String> map1;
    private Map<String, String> map2;

    @BeforeEach
    public void setUp() {
        map1 = new HashMap<>();
        map2 = new HashMap<>();
        compositeMap = new CompositeMap<>(map1, map2);
    }

    @Test
    public void testRemoveExistingKey() {
        map1.put("key1", "value1");
        map2.put("key2", "value2");

        String removedValue = compositeMap.remove("key1");
        assertEquals("value1", removedValue);
        assertNull(map1.get("key1"));
    }

    @Test
    public void testRemoveNonExistingKey() {
        map1.put("key1", "value1");
        map2.put("key2", "value2");

        String removedValue = compositeMap.remove("key3");
        assertNull(removedValue);
    }

    @Test
    public void testRemoveFromMultipleMaps() {
        map1.put("key1", "value1");
        map2.put("key1", "value2");

        String removedValue = compositeMap.remove("key1");
        assertEquals("value2", removedValue);
        assertNull(map2.get("key1"));
        assertEquals("value1", map1.get("key1"));
    }

    @Test
    public void testRemoveWithEmptyCompositeMap() {
        String removedValue = compositeMap.remove("key1");
        assertNull(removedValue);
    }

    @Test
    public void testRemoveWithNullKey() {
        map1.put(null, "value1");
        map2.put("key2", "value2");

        String removedValue = compositeMap.remove(null);
        assertEquals("value1", removedValue);
        assertNull(map1.get(null));
    }
}
