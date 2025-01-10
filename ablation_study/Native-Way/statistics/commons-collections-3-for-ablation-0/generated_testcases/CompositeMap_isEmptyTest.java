
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
    public void testIsEmptyWhenEmpty() {
        assertTrue(compositeMap.isEmpty());
    }

    @Test
    public void testIsEmptyWhenNotEmpty() {
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
        map1.put("key1", "value1");
        Map<String, String> map2 = new HashMap<>();
        map2.put("key2", "value2");
        compositeMap.addComposited(map1);
        compositeMap.addComposited(map2);
        assertFalse(compositeMap.isEmpty());
    }
}
