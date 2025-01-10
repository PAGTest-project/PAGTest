
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MultiValueMap_putAllTest {

    private MultiValueMap<String, String> multiValueMap;

    @BeforeEach
    public void setUp() {
        multiValueMap = new MultiValueMap<>();
    }

    @Test
    public void testPutAll_NewKey() {
        Collection<String> values = new ArrayList<>(Arrays.asList("A", "B", "C"));
        assertTrue(multiValueMap.putAll("key1", values));
        assertEquals(3, multiValueMap.size("key1"));
    }

    @Test
    public void testPutAll_ExistingKey() {
        Collection<String> initialValues = new ArrayList<>(Arrays.asList("A", "B"));
        multiValueMap.putAll("key1", initialValues);
        Collection<String> additionalValues = new ArrayList<>(Arrays.asList("C", "D"));
        assertTrue(multiValueMap.putAll("key1", additionalValues));
        assertEquals(4, multiValueMap.size("key1"));
    }

    @Test
    public void testPutAll_NullValues() {
        assertFalse(multiValueMap.putAll("key1", null));
        assertNull(multiValueMap.getCollection("key1"));
    }

    @Test
    public void testPutAll_EmptyValues() {
        Collection<String> values = new ArrayList<>();
        assertFalse(multiValueMap.putAll("key1", values));
        assertNull(multiValueMap.getCollection("key1"));
    }

    @Test
    public void testPutAll_AfterRemoveMapping() {
        Collection<String> initialValues = new ArrayList<>(Arrays.asList("A", "B"));
        multiValueMap.putAll("key1", initialValues);
        assertTrue(multiValueMap.removeMapping("key1", "A"));
        Collection<String> additionalValues = new ArrayList<>(Arrays.asList("C", "D"));
        assertTrue(multiValueMap.putAll("key1", additionalValues));
        assertEquals(3, multiValueMap.size("key1"));
    }

    @Test
    public void testPutAll_WithPut() {
        multiValueMap.put("key1", "A");
        Collection<String> additionalValues = new ArrayList<>(Arrays.asList("B", "C"));
        assertTrue(multiValueMap.putAll("key1", additionalValues));
        assertEquals(3, multiValueMap.size("key1"));
    }
}
