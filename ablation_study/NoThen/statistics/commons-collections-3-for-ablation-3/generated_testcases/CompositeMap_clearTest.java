
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CompositeMap_clearTest {

    private CompositeMap<String, String> compositeMap;
    private Map<String, String> map1;
    private Map<String, String> map2;

    @BeforeEach
    public void setUp() {
        map1 = new HashMap<>();
        map1.put("key1", "value1");
        map1.put("key2", "value2");

        map2 = new HashMap<>();
        map2.put("key3", "value3");
        map2.put("key4", "value4");

        compositeMap = new CompositeMap<>(map1, map2);
    }

    @Test
    public void testClear() {
        compositeMap.clear();
        assertTrue(map1.isEmpty());
        assertTrue(map2.isEmpty());
    }

    @Test
    public void testClearWithEmptyCompositeMap() {
        CompositeMap<String, String> emptyCompositeMap = new CompositeMap<>();
        emptyCompositeMap.clear();
        assertTrue(emptyCompositeMap.isEmpty());
    }

    @Test
    public void testClearWithSingleMap() {
        CompositeMap<String, String> singleMapComposite = new CompositeMap<>(map1);
        singleMapComposite.clear();
        assertTrue(map1.isEmpty());
    }

    @Test
    public void testClearWithMultipleMaps() {
        Map<String, String> map3 = new HashMap<>();
        map3.put("key5", "value5");
        map3.put("key6", "value6");

        compositeMap.addComposited(map3);
        compositeMap.clear();

        assertTrue(map1.isEmpty());
        assertTrue(map2.isEmpty());
        assertTrue(map3.isEmpty());
    }
}
