
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
    public void testSizeWithEmptyMaps() {
        compositeMap = new CompositeMap<>();
        assertEquals(0, compositeMap.size());
    }

    @Test
    public void testSizeWithNonEmptyMaps() {
        assertEquals(4, compositeMap.size());
    }

    @Test
    public void testSizeAfterAddingMap() {
        Map<String, String> map3 = new HashMap<>();
        map3.put("5", "five");
        map3.put("6", "six");
        compositeMap.addComposited(map3);
        assertEquals(6, compositeMap.size());
    }

    @Test
    public void testSizeAfterRemovingMap() {
        compositeMap.removeComposited(map2);
        assertEquals(2, compositeMap.size());
    }

    @Test
    public void testSizeAfterClearingMap() {
        map1.clear();
        map2.clear();
        assertEquals(0, compositeMap.size());
    }
}
