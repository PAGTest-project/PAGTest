
package org.apache.commons.collections4.properties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderedProperties_putAllTest {

    private OrderedProperties orderedProperties;

    @BeforeEach
    public void setUp() {
        orderedProperties = new OrderedProperties();
    }

    @Test
    public void testPutAll() {
        Map<Object, Object> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key3", "value3");

        orderedProperties.putAll(map);

        assertEquals(3, orderedProperties.size());
        assertTrue(orderedProperties.keySet().containsAll(map.keySet()));
    }

    @Test
    public void testPutAllEmptyMap() {
        Map<Object, Object> map = new HashMap<>();

        orderedProperties.putAll(map);

        assertEquals(0, orderedProperties.size());
    }

    @Test
    public void testPutAllWithExistingKeys() {
        orderedProperties.put("key1", "oldValue1");
        orderedProperties.put("key2", "oldValue2");

        Map<Object, Object> map = new HashMap<>();
        map.put("key1", "newValue1");
        map.put("key2", "newValue2");
        map.put("key3", "value3");

        orderedProperties.putAll(map);

        assertEquals(3, orderedProperties.size());
        assertEquals("newValue1", orderedProperties.get("key1"));
        assertEquals("newValue2", orderedProperties.get("key2"));
        assertEquals("value3", orderedProperties.get("key3"));
    }
}
