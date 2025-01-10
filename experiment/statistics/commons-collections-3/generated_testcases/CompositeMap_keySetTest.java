
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

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
        map1.put("1", "one");
        map1.put("2", "two");

        map2 = new HashMap<>();
        map2.put("3", "three");
        map2.put("4", "four");

        compositeMap = new CompositeMap<>(map1, map2);
    }

    @Test
    public void testKeySet() {
        Set<String> keySet = compositeMap.keySet();

        // Verify that the keySet contains all keys from both maps
        assertTrue(keySet.contains("1"));
        assertTrue(keySet.contains("2"));
        assertTrue(keySet.contains("3"));
        assertTrue(keySet.contains("4"));

        // Verify the size of the keySet
        assertEquals(4, keySet.size());

        // Add a new map to the composite and verify the keySet is updated
        Map<String, String> map3 = new HashMap<>();
        map3.put("5", "five");
        compositeMap.addComposited(map3);

        keySet = compositeMap.keySet();
        assertTrue(keySet.contains("5"));
        assertEquals(5, keySet.size());

        // Remove a map from the composite and verify the keySet is updated
        compositeMap.removeComposited(map2);
        keySet = compositeMap.keySet();
        assertFalse(keySet.contains("3"));
        assertFalse(keySet.contains("4"));
        assertEquals(3, keySet.size());
    }
}
