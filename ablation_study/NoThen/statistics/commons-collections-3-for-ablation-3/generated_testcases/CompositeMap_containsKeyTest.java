
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CompositeMap_containsKeyTest {

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
    public void testContainsKey_KeyInFirstMap() {
        assertTrue(compositeMap.containsKey("1"));
    }

    @Test
    public void testContainsKey_KeyInSecondMap() {
        assertTrue(compositeMap.containsKey("3"));
    }

    @Test
    public void testContainsKey_KeyNotPresent() {
        assertFalse(compositeMap.containsKey("5"));
    }

    @Test
    public void testContainsKey_AfterAddingNewMap() {
        Map<String, String> newMap = new HashMap<>();
        newMap.put("5", "five");
        compositeMap.addComposited(newMap);
        assertTrue(compositeMap.containsKey("5"));
    }

    @Test
    public void testContainsKey_AfterRemovingKey() {
        compositeMap.remove("1");
        assertFalse(compositeMap.containsKey("1"));
    }

    @Test
    public void testContainsKey_AfterRemovingCompositedMap() {
        compositeMap.removeComposited(mapOne);
        assertFalse(compositeMap.containsKey("1"));
    }

    @Test
    public void testContainsKey_NullKey() {
        assertFalse(compositeMap.containsKey(null));
    }

    @Test
    public void testContainsKey_EmptyCompositeMap() {
        compositeMap = new CompositeMap<>();
        assertFalse(compositeMap.containsKey("1"));
    }
}
