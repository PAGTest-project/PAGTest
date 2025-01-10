
package org.apache.commons.collections4.properties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;
import java.util.function.BiFunction;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderedProperties_mergeTest {

    private OrderedProperties orderedProperties;

    @BeforeEach
    public void setUp() {
        orderedProperties = new OrderedProperties();
    }

    @Test
    public void testMergeNewKey() {
        BiFunction<Object, Object, Object> remappingFunction = (oldValue, newValue) -> newValue;
        Object result = orderedProperties.merge("key1", "value1", remappingFunction);
        assertEquals("value1", result);
        assertTrue(orderedProperties.keySet().contains("key1"));
    }

    @Test
    public void testMergeExistingKey() {
        orderedProperties.put("key1", "oldValue");
        BiFunction<Object, Object, Object> remappingFunction = (oldValue, newValue) -> "newValue";
        Object result = orderedProperties.merge("key1", "value1", remappingFunction);
        assertEquals("newValue", result);
        assertTrue(orderedProperties.keySet().contains("key1"));
    }

    @Test
    public void testMergeNullValue() {
        orderedProperties.put("key1", "oldValue");
        BiFunction<Object, Object, Object> remappingFunction = (oldValue, newValue) -> null;
        Object result = orderedProperties.merge("key1", "value1", remappingFunction);
        assertNull(result);
        assertTrue(orderedProperties.keySet().contains("key1"));
    }

    @Test
    public void testMergeWithEmptyProperties() {
        BiFunction<Object, Object, Object> remappingFunction = (oldValue, newValue) -> newValue;
        Object result = orderedProperties.merge("key1", "value1", remappingFunction);
        assertEquals("value1", result);
        assertTrue(orderedProperties.keySet().contains("key1"));
    }

    @Test
    public void testMergeWithMultipleKeys() {
        orderedProperties.put("key1", "oldValue1");
        orderedProperties.put("key2", "oldValue2");
        BiFunction<Object, Object, Object> remappingFunction = (oldValue, newValue) -> newValue;
        Object result1 = orderedProperties.merge("key1", "newValue1", remappingFunction);
        Object result2 = orderedProperties.merge("key2", "newValue2", remappingFunction);
        assertEquals("newValue1", result1);
        assertEquals("newValue2", result2);
        assertTrue(orderedProperties.keySet().contains("key1"));
        assertTrue(orderedProperties.keySet().contains("key2"));
    }
}
