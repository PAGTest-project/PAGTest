
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CompositeMap_equalsTest {

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
    public void testEqualsWithEqualMaps() {
        CompositeMap<String, String> otherCompositeMap = new CompositeMap<>(map1, map2);
        assertTrue(compositeMap.equals(otherCompositeMap));
    }

    @Test
    public void testEqualsWithDifferentMaps() {
        Map<String, String> differentMap1 = new HashMap<>();
        differentMap1.put("key1", "value1");
        differentMap1.put("key2", "value2");

        Map<String, String> differentMap2 = new HashMap<>();
        differentMap2.put("key3", "value3");
        differentMap2.put("key4", "value4");
        differentMap2.put("key5", "value5");

        CompositeMap<String, String> otherCompositeMap = new CompositeMap<>(differentMap1, differentMap2);
        assertFalse(compositeMap.equals(otherCompositeMap));
    }

    @Test
    public void testEqualsWithNonMapObject() {
        assertFalse(compositeMap.equals("Not a map"));
    }

    @Test
    public void testEqualsAfterModification() {
        Map<String, String> modifiedMap1 = new HashMap<>(map1);
        modifiedMap1.put("key5", "value5");
        CompositeMap<String, String> otherCompositeMap = new CompositeMap<>(modifiedMap1, map2);
        assertTrue(compositeMap.equals(otherCompositeMap));
    }

    @Test
    public void testEqualsAfterRemoval() {
        compositeMap.remove("key1");
        Map<String, String> modifiedMap1 = new HashMap<>(map1);
        modifiedMap1.remove("key1");
        CompositeMap<String, String> otherCompositeMap = new CompositeMap<>(modifiedMap1, map2);
        assertTrue(compositeMap.equals(otherCompositeMap));
    }
}
