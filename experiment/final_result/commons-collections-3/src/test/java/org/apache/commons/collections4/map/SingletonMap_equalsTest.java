
package org.apache.commons.collections4.map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class SingletonMap_equalsTest {

    private SingletonMap<String, String> singletonMap;

    @BeforeEach
    public void setUp() {
        singletonMap = new SingletonMap<>("key", "value");
    }

    @Test
    public void testEqualsSameObject() {
        assertTrue(singletonMap.equals(singletonMap));
    }

    @Test
    public void testEqualsDifferentType() {
        assertFalse(singletonMap.equals("not a map"));
    }

    @Test
    public void testEqualsDifferentSizeMap() {
        Map<String, String> differentSizeMap = new HashMap<>();
        differentSizeMap.put("key1", "value1");
        differentSizeMap.put("key2", "value2");
        assertFalse(singletonMap.equals(differentSizeMap));
    }

    @Test
    public void testEqualsSameKeyAndValue() {
        Map<String, String> sameMap = new HashMap<>();
        sameMap.put("key", "value");
        assertTrue(singletonMap.equals(sameMap));
    }

    @Test
    public void testEqualsDifferentKey() {
        Map<String, String> differentKeyMap = new HashMap<>();
        differentKeyMap.put("differentKey", "value");
        assertFalse(singletonMap.equals(differentKeyMap));
    }

    @Test
    public void testEqualsDifferentValue() {
        Map<String, String> differentValueMap = new HashMap<>();
        differentValueMap.put("key", "differentValue");
        assertFalse(singletonMap.equals(differentValueMap));
    }
}
