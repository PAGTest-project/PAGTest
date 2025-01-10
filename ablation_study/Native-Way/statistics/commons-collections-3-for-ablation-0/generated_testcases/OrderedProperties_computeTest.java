
package org.apache.commons.collections4.properties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.function.BiFunction;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderedProperties_computeTest {

    private OrderedProperties orderedProperties;

    @BeforeEach
    public void setUp() {
        orderedProperties = new OrderedProperties();
    }

    @Test
    public void testComputeNewKey() {
        BiFunction<Object, Object, Object> remappingFunction = (k, v) -> "newValue";
        Object result = orderedProperties.compute("newKey", remappingFunction);
        assertEquals("newValue", result);
        assertTrue(orderedProperties.insertOrder.contains("newKey"));
    }

    @Test
    public void testComputeExistingKey() {
        orderedProperties.put("existingKey", "oldValue");
        BiFunction<Object, Object, Object> remappingFunction = (k, v) -> "newValue";
        Object result = orderedProperties.compute("existingKey", remappingFunction);
        assertEquals("newValue", result);
        assertTrue(orderedProperties.insertOrder.contains("existingKey"));
    }

    @Test
    public void testComputeNullValue() {
        BiFunction<Object, Object, Object> remappingFunction = (k, v) -> null;
        Object result = orderedProperties.compute("key", remappingFunction);
        assertNull(result);
        assertFalse(orderedProperties.insertOrder.contains("key"));
    }

    @Test
    public void testComputeNullKey() {
        BiFunction<Object, Object, Object> remappingFunction = (k, v) -> "newValue";
        Object result = orderedProperties.compute(null, remappingFunction);
        assertNull(result);
        assertFalse(orderedProperties.insertOrder.contains(null));
    }
}
