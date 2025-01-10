
package org.apache.commons.collections4.properties;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class OrderedProperties_forEachTest {

    private OrderedProperties orderedProperties;

    @BeforeEach
    public void setUp() {
        orderedProperties = new OrderedProperties();
    }

    @Test
    public void testForEachWithEmptyProperties() {
        orderedProperties.forEach((k, v) -> {
            throw new AssertionError("Should not be called");
        });
    }

    @Test
    public void testForEachWithSingleEntry() {
        orderedProperties.put("key1", "value1");
        Map<Object, Object> result = new HashMap<>();
        orderedProperties.forEach((k, v) -> result.put(k, v));
        assertEquals(1, result.size());
        assertEquals("value1", result.get("key1"));
    }

    @Test
    public void testForEachWithMultipleEntries() {
        orderedProperties.put("key1", "value1");
        orderedProperties.put("key2", "value2");
        orderedProperties.put("key3", "value3");
        Map<Object, Object> result = new HashMap<>();
        orderedProperties.forEach((k, v) -> result.put(k, v));
        assertEquals(3, result.size());
        assertEquals("value1", result.get("key1"));
        assertEquals("value2", result.get("key2"));
        assertEquals("value3", result.get("key3"));
    }

    @Test
    public void testForEachWithNullAction() {
        BiConsumer<Object, Object> action = null;
        NullPointerException exception = assertThrows(NullPointerException.class, () -> {
            orderedProperties.forEach(action);
        });
        assertEquals("action", exception.getMessage());
    }
}
