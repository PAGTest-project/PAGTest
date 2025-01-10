
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CompositeMap_isEmptyTest {

    private CompositeMap<String, String> compositeMap;

    @BeforeEach
    public void setUp() {
        compositeMap = new CompositeMap<>();
    }

    @Test
    public void testIsEmpty_WhenNoMapsAdded() {
        assertTrue(compositeMap.isEmpty());
    }

    @Test
    public void testIsEmpty_WhenMapsAddedButEmpty() {
        Map<String, String> map1 = new HashMap<>();
        Map<String, String> map2 = new HashMap<>();
        compositeMap.addComposited(map1);
        compositeMap.addComposited(map2);
        assertTrue(compositeMap.isEmpty());
    }

    @Test
    public void testIsEmpty_WhenMapsAddedWithEntries() {
        Map<String, String> map1 = new HashMap<>();
        map1.put("key1", "value1");
        compositeMap.addComposited(map1);
        assertFalse(compositeMap.isEmpty());
    }

    @Test
    public void testIsEmpty_AfterClear() {
        Map<String, String> map1 = new HashMap<>();
        map1.put("key1", "value1");
        compositeMap.addComposited(map1);
        compositeMap.clear();
        assertTrue(compositeMap.isEmpty());
    }

    @Test
    public void testIsEmpty_AfterRemovingMap() {
        Map<String, String> map1 = new HashMap<>();
        map1.put("key1", "value1");
        compositeMap.addComposited(map1);
        compositeMap.removeComposited(map1);
        assertTrue(compositeMap.isEmpty());
    }
}
