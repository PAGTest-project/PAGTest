
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CompositeMap_containsValueTest {

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
    public void testContainsValue_ValuePresentInFirstMap() {
        assertTrue(compositeMap.containsValue("one"));
    }

    @Test
    public void testContainsValue_ValuePresentInSecondMap() {
        assertTrue(compositeMap.containsValue("three"));
    }

    @Test
    public void testContainsValue_ValueNotPresent() {
        assertFalse(compositeMap.containsValue("five"));
    }

    @Test
    public void testContainsValue_AfterAddingNewMap() {
        Map<String, String> newMap = new HashMap<>();
        newMap.put("5", "five");
        compositeMap.addComposited(newMap);
        assertTrue(compositeMap.containsValue("five"));
    }

    @Test
    public void testContainsValue_AfterRemovingValue() {
        compositeMap.remove("1");
        assertFalse(compositeMap.containsValue("one"));
    }

    @Test
    public void testContainsValue_AfterPuttingNewValue() {
        Map<String, String> newMap = new HashMap<>();
        newMap.put("6", "six");
        compositeMap.addComposited(newMap);
        assertTrue(compositeMap.containsValue("six"));
    }
}
