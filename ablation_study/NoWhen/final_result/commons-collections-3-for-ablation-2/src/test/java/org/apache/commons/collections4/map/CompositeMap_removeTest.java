
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
        map1.put("key1", "value1");
        map1.put("key2", "value2");

        map2 = new HashMap<>();
        map2.put("key3", "value3");
        map2.put("key4", "value4");

        compositeMap = new CompositeMap<>(map1, map2);
    }

    @Test
    public void testRemoveExistingKey() {
        String removedValue = compositeMap.remove("key1");
        assertEquals("value1", removedValue);
        assertNull(compositeMap.get("key1"));
    }

    @Test
    public void testRemoveNonExistingKey() {
        String removedValue = compositeMap.remove("nonExistingKey");
        assertNull(removedValue);
    }

    @Test
    public void testRemoveFromSecondMap() {
        String removedValue = compositeMap.remove("key3");
        assertEquals("value3", removedValue);
        assertNull(compositeMap.get("key3"));
    }

    @Test
    public void testRemoveAllKeys() {
        compositeMap.remove("key1");
        compositeMap.remove("key2");
        compositeMap.remove("key3");
        compositeMap.remove("key4");

        assertTrue(compositeMap.isEmpty());
    }
}
