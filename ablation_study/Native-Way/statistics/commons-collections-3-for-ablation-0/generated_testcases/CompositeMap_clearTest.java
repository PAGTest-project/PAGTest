
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CompositeMap_clearTest {

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
    public void testClear() {
        compositeMap.clear();

        assertTrue(mapOne.isEmpty());
        assertTrue(mapTwo.isEmpty());
        assertTrue(compositeMap.isEmpty());
    }

    @Test
    public void testClearWithEmptyCompositeMap() {
        CompositeMap<String, String> emptyCompositeMap = new CompositeMap<>();
        emptyCompositeMap.clear();

        assertTrue(emptyCompositeMap.isEmpty());
    }

    @Test
    public void testClearWithSingleMap() {
        CompositeMap<String, String> singleMapComposite = new CompositeMap<>(mapOne);
        singleMapComposite.clear();

        assertTrue(mapOne.isEmpty());
        assertTrue(singleMapComposite.isEmpty());
    }

    @Test
    public void testClearWithMultipleMaps() {
        Map<String, String> mapThree = new HashMap<>();
        mapThree.put("5", "five");
        compositeMap.addComposited(mapThree);

        compositeMap.clear();

        assertTrue(mapOne.isEmpty());
        assertTrue(mapTwo.isEmpty());
        assertTrue(mapThree.isEmpty());
        assertTrue(compositeMap.isEmpty());
    }

    @Test
    public void testClearWithNullMap() {
        compositeMap.addComposited(null);
        compositeMap.clear();

        assertTrue(mapOne.isEmpty());
        assertTrue(mapTwo.isEmpty());
        assertTrue(compositeMap.isEmpty());
    }
}
