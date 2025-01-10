
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CompositeMap_removeCompositedTest {

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
    public void testRemoveCompositedExistingMap() {
        Map<String, String> removedMap = compositeMap.removeComposited(mapOne);
        assertEquals(mapOne, removedMap);
        assertEquals(1, compositeMap.size());
        assertFalse(compositeMap.containsKey("1"));
        assertFalse(compositeMap.containsKey("2"));
    }

    @Test
    public void testRemoveCompositedNonExistingMap() {
        Map<String, String> nonExistingMap = new HashMap<>();
        nonExistingMap.put("5", "five");
        Map<String, String> removedMap = compositeMap.removeComposited(nonExistingMap);
        assertNull(removedMap);
        assertEquals(2, compositeMap.size());
    }

    @Test
    public void testRemoveCompositedEmptyComposite() {
        CompositeMap<String, String> emptyCompositeMap = new CompositeMap<>();
        Map<String, String> removedMap = emptyCompositeMap.removeComposited(mapOne);
        assertNull(removedMap);
        assertTrue(emptyCompositeMap.isEmpty());
    }
}
