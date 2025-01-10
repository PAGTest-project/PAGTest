
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.*;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.collections4.KeyValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SingletonMap_equalsTest {

    private SingletonMap<String, String> singletonMap;

    @BeforeEach
    public void setUp() {
        singletonMap = new SingletonMap<>("key", "value");
    }

    @Test
    public void testEquals_SameInstance() {
        assertTrue(singletonMap.equals(singletonMap));
    }

    @Test
    public void testEquals_DifferentType() {
        assertFalse(singletonMap.equals("not a map"));
    }

    @Test
    public void testEquals_DifferentSizeMap() {
        Map<String, String> differentSizeMap = new HashMap<>();
        differentSizeMap.put("key1", "value1");
        differentSizeMap.put("key2", "value2");
        assertFalse(singletonMap.equals(differentSizeMap));
    }

    @Test
    public void testEquals_SameKeyDifferentValue() {
        Map<String, String> sameKeyDifferentValueMap = new HashMap<>();
        sameKeyDifferentValueMap.put("key", "differentValue");
        assertFalse(singletonMap.equals(sameKeyDifferentValueMap));
    }

    @Test
    public void testEquals_DifferentKeySameValue() {
        Map<String, String> differentKeySameValueMap = new HashMap<>();
        differentKeySameValueMap.put("differentKey", "value");
        assertFalse(singletonMap.equals(differentKeySameValueMap));
    }

    @Test
    public void testEquals_SameKeySameValue() {
        Map<String, String> sameKeySameValueMap = new HashMap<>();
        sameKeySameValueMap.put("key", "value");
        assertTrue(singletonMap.equals(sameKeySameValueMap));
    }

    @Test
    public void testEquals_NullKey() {
        SingletonMap<String, String> nullKeyMap = new SingletonMap<>(null, "value");
        Map<String, String> nullKeyHashMap = new HashMap<>();
        nullKeyHashMap.put(null, "value");
        assertTrue(nullKeyMap.equals(nullKeyHashMap));
    }

    @Test
    public void testEquals_NullValue() {
        SingletonMap<String, String> nullValueMap = new SingletonMap<>("key", null);
        Map<String, String> nullValueHashMap = new HashMap<>();
        nullValueHashMap.put("key", null);
        assertTrue(nullValueMap.equals(nullValueHashMap));
    }

    @Test
    public void testEquals_NullKeyAndValue() {
        SingletonMap<String, String> nullKeyAndValueMap = new SingletonMap<>(null, null);
        Map<String, String> nullKeyAndValueHashMap = new HashMap<>();
        nullKeyAndValueHashMap.put(null, null);
        assertTrue(nullKeyAndValueMap.equals(nullKeyAndValueHashMap));
    }
}
