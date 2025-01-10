
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CompositeMap_equalsTest {

    private Map<String, String> one;
    private Map<String, String> two;

    @BeforeEach
    public void setUp() {
        one = new HashMap<>();
        one.put("1", "one");
        one.put("2", "two");

        two = new HashMap<>();
        two.put("3", "three");
        two.put("4", "four");
    }

    @Test
    public void testEqualsWithEqualMaps() {
        CompositeMap<String, String> compositeMap = new CompositeMap<>(one, two);
        Map<String, String> combinedMap = new HashMap<>(one);
        combinedMap.putAll(two);

        assertTrue(compositeMap.equals(combinedMap));
    }

    @Test
    public void testEqualsWithDifferentMaps() {
        CompositeMap<String, String> compositeMap = new CompositeMap<>(one, two);
        Map<String, String> differentMap = new HashMap<>(one);
        differentMap.put("5", "five");

        assertFalse(compositeMap.equals(differentMap));
    }

    @Test
    public void testEqualsWithNonMapObject() {
        CompositeMap<String, String> compositeMap = new CompositeMap<>(one, two);
        Object nonMapObject = new Object();

        assertFalse(compositeMap.equals(nonMapObject));
    }

    @Test
    public void testEqualsWithEmptyMap() {
        CompositeMap<String, String> compositeMap = new CompositeMap<>(one, two);
        Map<String, String> emptyMap = new HashMap<>();

        assertFalse(compositeMap.equals(emptyMap));
    }

    @Test
    public void testEqualsWithNull() {
        CompositeMap<String, String> compositeMap = new CompositeMap<>(one, two);

        assertFalse(compositeMap.equals(null));
    }
}
