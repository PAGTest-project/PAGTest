
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CompositeMap_keySetTest {

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
    public void testKeySetWithMultipleMaps() {
        Set<String> keySet = compositeMap.keySet();
        assertEquals(4, keySet.size());
        assertTrue(keySet.contains("key1"));
        assertTrue(keySet.contains("key2"));
        assertTrue(keySet.contains("key3"));
        assertTrue(keySet.contains("key4"));
    }

    @Test
    public void testKeySetAfterAddingMap() {
        Map<String, String> map3 = new HashMap<>();
        map3.put("key5", "value5");
        compositeMap.addComposited(map3);

        Set<String> keySet = compositeMap.keySet();
        assertEquals(5, keySet.size());
        assertTrue(keySet.contains("key5"));
    }

    @Test
    public void testKeySetAfterRemovingMap() {
        compositeMap.removeComposited(map2);

        Set<String> keySet = compositeMap.keySet();
        assertEquals(2, keySet.size());
        assertTrue(keySet.contains("key1"));
        assertTrue(keySet.contains("key2"));
        assertFalse(keySet.contains("key3"));
        assertFalse(keySet.contains("key4"));
    }

    @Test
    public void testKeySetAfterClearingMap() {
        map1.clear();
        map2.clear();

        Set<String> keySet = compositeMap.keySet();
        assertTrue(keySet.isEmpty());
    }

    @Test
    public void testKeySetAfterPuttingNewEntry() {
        map1.put("key5", "value5");

        Set<String> keySet = compositeMap.keySet();
        assertEquals(5, keySet.size());
        assertTrue(keySet.contains("key5"));
    }

    @Test
    public void testKeySetWithEmptyCompositeMap() {
        CompositeMap<String, String> emptyMap = new CompositeMap<>();

        Set<String> keySet = emptyMap.keySet();
        assertTrue(keySet.isEmpty());
    }

    @Test
    public void testKeySetWithNullMap() {
        assertThrows(IllegalArgumentException.class, () -> {
            compositeMap.addComposited(null);
        });
    }
}
