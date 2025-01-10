
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
    public void testRemoveCompositedExistingMap() {
        Map<String, String> removedMap = compositeMap.removeComposited(map1);
        assertEquals(map1, removedMap);
        assertEquals(1, compositeMap.getComposited().length);
        assertTrue(compositeMap.getComposited()[0].equals(map2));
    }

    @Test
    public void testRemoveCompositedNonExistingMap() {
        Map<String, String> nonExistingMap = new HashMap<>();
        nonExistingMap.put("5", "five");
        Map<String, String> removedMap = compositeMap.removeComposited(nonExistingMap);
        assertNull(removedMap);
        assertEquals(2, compositeMap.getComposited().length);
    }

    @Test
    public void testRemoveCompositedLastMap() {
        Map<String, String> removedMap = compositeMap.removeComposited(map2);
        assertEquals(map2, removedMap);
        assertEquals(1, compositeMap.getComposited().length);
        assertTrue(compositeMap.getComposited()[0].equals(map1));
    }

    @Test
    public void testRemoveCompositedEmptyComposite() {
        CompositeMap<String, String> emptyCompositeMap = new CompositeMap<>();
        Map<String, String> removedMap = emptyCompositeMap.removeComposited(map1);
        assertNull(removedMap);
        assertEquals(0, emptyCompositeMap.getComposited().length);
    }
}
