
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MultiValueMap_putAllTest {

    private MultiValueMap<String, String> map;

    @BeforeEach
    public void setUp() {
        map = new MultiValueMap<>();
    }

    @Test
    public void testPutAll_NullValues() {
        assertFalse(map.putAll("key", null));
    }

    @Test
    public void testPutAll_EmptyValues() {
        assertFalse(map.putAll("key", Arrays.asList()));
    }

    @Test
    public void testPutAll_NewKey() {
        assertTrue(map.putAll("key", Arrays.asList("value1", "value2")));
        assertEquals(2, map.size("key"));
    }

    @Test
    public void testPutAll_ExistingKey() {
        map.put("key", "value1");
        assertTrue(map.putAll("key", Arrays.asList("value2", "value3")));
        assertEquals(3, map.size("key"));
    }

    @Test
    public void testPutAll_NonEmptyCollectionCreation() {
        map.putAll("key", Arrays.asList("value1", "value2"));
        assertTrue(map.containsValue("key", "value1"));
        assertTrue(map.containsValue("key", "value2"));
    }

    @Test
    public void testPutAll_EmptyCollectionCreation() {
        map.putAll("key", Arrays.asList("value1", "value2"));
        assertFalse(map.containsValue("key", "value3"));
    }

    @Test
    public void testPutAll_StateChange() {
        map.putAll("key", Arrays.asList("value1", "value2"));
        map.clear();
        assertFalse(map.containsValue("key", "value1"));
        assertFalse(map.containsValue("key", "value2"));
    }

    @Test
    public void testPutAll_Substitutability() {
        MultiValueMap<String, String> otherMap = new MultiValueMap<>(new HashMap<>(), ArrayList::new);
        otherMap.putAll("key", Arrays.asList("value1", "value2"));
        map.putAll(otherMap);
        assertEquals(2, map.size("key"));
    }
}
