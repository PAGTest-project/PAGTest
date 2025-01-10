
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
    private Map<String, String> mapOne;
    private Map<String, String> mapTwo;

    @BeforeEach
    public void setUp() {
        mapOne = new HashMap<>();
        mapOne.put("1", "one");
        mapOne.put("2", "two");

        mapTwo = new HashMap<>();
        mapTwo.put("3", "three");
        mapTwo.put("4", "four");

        compositeMap = new CompositeMap<>(mapOne, mapTwo);
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
    }

    @Test
    public void testKeySetAfterAddComposited() {
        Map<String, String> mapThree = new HashMap<>();
        mapThree.put("5", "five");
        mapThree.put("6", "six");

        compositeMap.addComposited(mapThree);
        Set<String> keySet = compositeMap.keySet();

        // Verify that the keySet contains all keys from all three maps
        assertTrue(keySet.contains("1"));
        assertTrue(keySet.contains("2"));
        assertTrue(keySet.contains("3"));
        assertTrue(keySet.contains("4"));
        assertTrue(keySet.contains("5"));
        assertTrue(keySet.contains("6"));

        // Verify the size of the keySet
        assertEquals(6, keySet.size());
    }

    @Test
    public void testKeySetAfterRemoveComposited() {
        compositeMap.removeComposited(mapTwo);
        Set<String> keySet = compositeMap.keySet();

        // Verify that the keySet contains only keys from mapOne
        assertTrue(keySet.contains("1"));
        assertTrue(keySet.contains("2"));
        assertFalse(keySet.contains("3"));
        assertFalse(keySet.contains("4"));

        // Verify the size of the keySet
        assertEquals(2, keySet.size());
    }

    @Test
    public void testKeySetWithEmptyCompositeMap() {
        CompositeMap<String, String> emptyMap = new CompositeMap<>();
        Set<String> keySet = emptyMap.keySet();

        // Verify that the keySet is empty
        assertTrue(keySet.isEmpty());
    }

    @Test
    public void testKeySetWithNullAddComposited() {
        assertThrows(IllegalArgumentException.class, () -> compositeMap.addComposited(null));
    }
}
