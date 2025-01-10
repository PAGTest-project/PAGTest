
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MultiValueMap_containsValueTest {

    private MultiValueMap<String, String> map;

    @BeforeEach
    public void setUp() {
        map = new MultiValueMap<>();
    }

    @Test
    public void testContainsValue_ValuePresent() {
        map.put("key1", "value1");
        map.put("key1", "value2");
        map.put("key2", "value3");

        assertTrue(map.containsValue("value1"));
        assertTrue(map.containsValue("value2"));
        assertTrue(map.containsValue("value3"));
    }

    @Test
    public void testContainsValue_ValueNotPresent() {
        map.put("key1", "value1");
        map.put("key1", "value2");

        assertFalse(map.containsValue("value3"));
        assertFalse(map.containsValue("value4"));
    }

    @Test
    public void testContainsValue_EmptyMap() {
        assertFalse(map.containsValue("value1"));
        assertFalse(map.containsValue("value2"));
    }

    @Test
    public void testContainsValue_NullValue() {
        map.put("key1", null);
        assertTrue(map.containsValue(null));
    }

    @Test
    public void testContainsValue_MultipleKeys() {
        map.put("key1", "value1");
        map.put("key2", "value1");
        map.put("key3", "value2");

        assertTrue(map.containsValue("value1"));
        assertTrue(map.containsValue("value2"));
    }
}
