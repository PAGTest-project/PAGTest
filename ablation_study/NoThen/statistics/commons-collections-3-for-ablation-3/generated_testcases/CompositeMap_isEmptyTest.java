
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
    public void testIsEmptyWhenAllMapsAreEmpty() {
        assertTrue(compositeMap.isEmpty());
    }

    @Test
    public void testIsEmptyWhenOneMapIsNotEmpty() {
        Map<String, String> map1 = new HashMap<>();
        map1.put("key1", "value1");
        compositeMap.addComposited(map1);
        assertFalse(compositeMap.isEmpty());
    }

    @Test
    public void testIsEmptyAfterClear() {
        Map<String, String> map1 = new HashMap<>();
        map1.put("key1", "value1");
        compositeMap.addComposited(map1);
        compositeMap.clear();
        assertTrue(compositeMap.isEmpty());
    }

    @Test
    public void testIsEmptyAfterAddingAndRemovingMap() {
        Map<String, String> map1 = new HashMap<>();
        map1.put("key1", "value1");
        compositeMap.addComposited(map1);
        compositeMap.removeComposited(map1);
        assertTrue(compositeMap.isEmpty());
    }

    @Test
    public void testIsEmptyWithMultipleMaps() {
        Map<String, String> map1 = new HashMap<>();
        Map<String, String> map2 = new HashMap<>();
        map1.put("key1", "value1");
        map2.put("key2", "value2");
        compositeMap.addComposited(map1);
        compositeMap.addComposited(map2);
        assertFalse(compositeMap.isEmpty());
    }
}
