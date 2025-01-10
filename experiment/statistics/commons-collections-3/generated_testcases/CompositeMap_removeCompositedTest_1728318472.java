
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
    public void testRemoveComposited_MapExists() {
        Map<String, String> removedMap = compositeMap.removeComposited(map1);
        assertEquals(map1, removedMap);
        assertTrue(compositeMap.composite.length == 1);
        assertTrue(compositeMap.composite[0].equals(map2));
    }

    @Test
    public void testRemoveComposited_MapDoesNotExist() {
        Map<String, String> nonExistentMap = new HashMap<>();
        nonExistentMap.put("5", "five");
        Map<String, String> removedMap = compositeMap.removeComposited(nonExistentMap);
        assertNull(removedMap);
        assertTrue(compositeMap.composite.length == 2);
    }
}
