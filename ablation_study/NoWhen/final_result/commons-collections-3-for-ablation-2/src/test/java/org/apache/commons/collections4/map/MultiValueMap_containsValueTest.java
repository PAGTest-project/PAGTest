
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
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
    public void testContainsValue_ValuePresentInCollection() {
        multiValueMap.put("key1", "value1");
        multiValueMap.put("key1", "value2");
        assertTrue(multiValueMap.containsValue("value1"));
    }

    @Test
    public void testContainsValue_ValueNotPresentInCollection() {
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
    public void testContainsValue_MultipleKeysWithSameValue() {
        multiValueMap.put("key1", "value1");
        multiValueMap.put("key2", "value1");
        assertTrue(multiValueMap.containsValue("value1"));
    }

    @Test
    public void testContainsValue_ValuePresentInDifferentCollections() {
        multiValueMap.put("key1", "value1");
        multiValueMap.put("key2", "value2");
        assertTrue(multiValueMap.containsValue("value1"));
        assertTrue(multiValueMap.containsValue("value2"));
    }

    @Test
    public void testContainsValue_ValueRemovedFromCollection() {
        multiValueMap.put("key1", "value1");
        multiValueMap.removeMapping("key1", "value1");
        assertFalse(multiValueMap.containsValue("value1"));
    }

    @Test
    public void testContainsValue_ValueAddedToNewCollection() {
        multiValueMap.put("key1", "value1");
        multiValueMap.put("key2", "value2");
        assertTrue(multiValueMap.containsValue("value2"));
    }

    @Test
    public void testContainsValue_ValueAddedToExistingCollection() {
        multiValueMap.put("key1", "value1");
        multiValueMap.put("key1", "value2");
        assertTrue(multiValueMap.containsValue("value2"));
    }

    @Test
    public void testContainsValue_ValueRemovedFromAllCollections() {
        multiValueMap.put("key1", "value1");
        multiValueMap.put("key2", "value1");
        multiValueMap.removeMapping("key1", "value1");
        multiValueMap.removeMapping("key2", "value1");
        assertFalse(multiValueMap.containsValue("value1"));
    }
}
