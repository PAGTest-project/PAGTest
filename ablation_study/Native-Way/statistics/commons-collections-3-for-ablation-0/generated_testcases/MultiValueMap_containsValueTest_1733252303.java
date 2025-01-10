
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MultiValueMap_containsValueTest {

    private MultiValueMap<String, String> multiValueMap;

    @BeforeEach
    public void setUp() {
        multiValueMap = new MultiValueMap<>();
    }

    @Test
    public void testContainsValue_ValuePresent() {
        multiValueMap.put("key1", "value1");
        multiValueMap.put("key1", "value2");
        assertTrue(multiValueMap.containsValue("value1"));
        assertTrue(multiValueMap.containsValue("value2"));
    }

    @Test
    public void testContainsValue_ValueNotPresent() {
        multiValueMap.put("key1", "value1");
        assertFalse(multiValueMap.containsValue("value3"));
    }

    @Test
    public void testContainsValue_EmptyMap() {
        assertFalse(multiValueMap.containsValue("value1"));
    }

    @Test
    public void testContainsValue_NullValue() {
        multiValueMap.put("key1", null);
        assertTrue(multiValueMap.containsValue(null));
    }

    @Test
    public void testContainsValue_MultipleKeys() {
        multiValueMap.put("key1", "value1");
        multiValueMap.put("key2", "value2");
        assertTrue(multiValueMap.containsValue("value1"));
        assertTrue(multiValueMap.containsValue("value2"));
    }

    @Test
    public void testContainsValue_MultipleValuesInOneKey() {
        multiValueMap.put("key1", "value1");
        multiValueMap.put("key1", "value2");
        multiValueMap.put("key2", "value3");
        assertTrue(multiValueMap.containsValue("value1"));
        assertTrue(multiValueMap.containsValue("value2"));
        assertTrue(multiValueMap.containsValue("value3"));
    }

    @Test
    public void testContainsValue_ValueInDifferentKeys() {
        multiValueMap.put("key1", "value1");
        multiValueMap.put("key2", "value1");
        assertTrue(multiValueMap.containsValue("value1"));
    }

    @Test
    public void testContainsValue_ValueInCollection() {
        multiValueMap.put("key1", new ArrayList<>(Arrays.asList("value1", "value2")));
        assertTrue(multiValueMap.containsValue("value1"));
        assertTrue(multiValueMap.containsValue("value2"));
    }

    @Test
    public void testContainsValue_ValueInNestedCollection() {
        Collection<String> nestedCollection = new ArrayList<>(Arrays.asList("value1", "value2"));
        multiValueMap.put("key1", nestedCollection);
        assertTrue(multiValueMap.containsValue("value1"));
        assertTrue(multiValueMap.containsValue("value2"));
    }

    @Test
    public void testContainsValue_ValueInMultipleCollections() {
        multiValueMap.put("key1", new ArrayList<>(Arrays.asList("value1", "value2")));
        multiValueMap.put("key2", new ArrayList<>(Arrays.asList("value3", "value4")));
        assertTrue(multiValueMap.containsValue("value1"));
        assertTrue(multiValueMap.containsValue("value2"));
        assertTrue(multiValueMap.containsValue("value3"));
        assertTrue(multiValueMap.containsValue("value4"));
    }
}
