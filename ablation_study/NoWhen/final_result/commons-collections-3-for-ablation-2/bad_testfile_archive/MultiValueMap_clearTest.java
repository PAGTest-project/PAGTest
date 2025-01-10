
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections4.Factory;
import org.apache.commons.collections4.map.MultiValueMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MultiValueMap_clearTest {

    private MultiValueMap<String, String> multiValueMap;

    @BeforeEach
    public void setUp() {
        multiValueMap = new MultiValueMap<>();
    }

    @Test
    public void testClear() {
        // Given
        multiValueMap.put("key1", "value1");
        multiValueMap.put("key1", "value2");
        multiValueMap.put("key2", "value3");

        // When
        multiValueMap.clear();

        // Then
        assertTrue(multiValueMap.isEmpty());
        assertEquals(0, multiValueMap.size());
    }

    @Test
    public void testClearWithEmptyMap() {
        // Given
        // Map is already empty

        // When
        multiValueMap.clear();

        // Then
        assertTrue(multiValueMap.isEmpty());
        assertEquals(0, multiValueMap.size());
    }

    @Test
    public void testClearWithMultipleKeys() {
        // Given
        multiValueMap.put("key1", "value1");
        multiValueMap.put("key1", "value2");
        multiValueMap.put("key2", "value3");
        multiValueMap.put("key2", "value4");

        // When
        multiValueMap.clear();

        // Then
        assertTrue(multiValueMap.isEmpty());
        assertEquals(0, multiValueMap.size());
    }

    @Test
    public void testClearWithDifferentCollectionTypes() {
        // Given
        Map<String, Collection<String>> map = new HashMap<>();
        map.put("key1", new ArrayList<>(java.util.Arrays.asList("value1", "value2")));
        map.put("key2", new ArrayList<>(java.util.Arrays.asList("value3", "value4")));
        Factory<Collection<String>> factory = Factory.instantiateFactory(ArrayList.class, Collection.class);
        MultiValueMap<String, String> customMap = MultiValueMap.multiValueMap(map, factory);

        // When
        customMap.clear();

        // Then
        assertTrue(customMap.isEmpty());
        assertEquals(0, customMap.size());
    }
}
