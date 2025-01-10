
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
        multiValueMap = new MultiValueMap<>(new HashMap<>(), ArrayList::new);
    }

    @Test
    public void testPutAll_Success() {
        Collection<String> values = Arrays.asList("value1", "value2");
        assertTrue(multiValueMap.putAll("key1", values));
        assertEquals(2, multiValueMap.size("key1"));
        assertEquals(2, multiValueMap.totalSize());
    }

    @Test
    public void testPutAll_NullValues() {
        assertFalse(multiValueMap.putAll("key1", null));
        assertNull(multiValueMap.getCollection("key1"));
        assertEquals(0, multiValueMap.totalSize());
    }

    @Test
    public void testPutAll_EmptyValues() {
        Collection<String> values = new ArrayList<>();
        assertFalse(multiValueMap.putAll("key1", values));
        assertNull(multiValueMap.getCollection("key1"));
        assertEquals(0, multiValueMap.totalSize());
    }

    @Test
    public void testPutAll_ExistingKey() {
        Collection<String> initialValues = Arrays.asList("value1", "value2");
        Collection<String> additionalValues = Arrays.asList("value3", "value4");
        assertTrue(multiValueMap.putAll("key1", initialValues));
        assertTrue(multiValueMap.putAll("key1", additionalValues));
        assertEquals(4, multiValueMap.size("key1"));
        assertEquals(4, multiValueMap.totalSize());
    }

    @Test
    public void testPutAll_MultipleKeys() {
        Collection<String> values1 = Arrays.asList("value1", "value2");
        Collection<String> values2 = Arrays.asList("value3", "value4");
        assertTrue(multiValueMap.putAll("key1", values1));
        assertTrue(multiValueMap.putAll("key2", values2));
        assertEquals(2, multiValueMap.size("key1"));
        assertEquals(2, multiValueMap.size("key2"));
        assertEquals(4, multiValueMap.totalSize());
    }
}
