
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CompositeMap_sizeTest {

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

        compositeMap = new CompositeMap<>(mapOne, mapTwo, null);
    }

    @Test
    public void testSizeWithInitialMaps() {
        assertEquals(4, compositeMap.size());
    }

    @Test
    public void testSizeAfterAddingMap() {
        Map<String, String> mapThree = new HashMap<>();
        mapThree.put("5", "five");
        mapThree.put("6", "six");

        compositeMap.addComposited(mapThree);
        assertEquals(6, compositeMap.size());
    }

    @Test
    public void testSizeAfterRemovingMap() {
        compositeMap.removeComposited(mapTwo);
        assertEquals(2, compositeMap.size());
    }

    @Test
    public void testSizeAfterAddingKeyValue() {
        Map<String, String> newMap = new HashMap<>();
        newMap.put("5", "five");
        compositeMap.addComposited(newMap);
        assertEquals(5, compositeMap.size());
    }

    @Test
    public void testSizeAfterRemovingKeyValue() {
        compositeMap.remove("3");
        assertEquals(3, compositeMap.size());
    }

    @Test
    public void testSizeAfterClearingMaps() {
        compositeMap.clear();
        assertEquals(0, compositeMap.size());
    }
}
