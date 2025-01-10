
package org.apache.commons.collections4.properties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.function.Function;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderedProperties_computeIfAbsentTest {

    private OrderedProperties orderedProperties;

    @BeforeEach
    public void setUp() {
        orderedProperties = new OrderedProperties();
    }

    @Test
    public void testComputeIfAbsentWithExistingKey() {
        orderedProperties.put("key1", "value1");
        Function<Object, Object> mappingFunction = key -> "newValue";
        Object result = orderedProperties.computeIfAbsent("key1", mappingFunction);
        assertEquals("value1", result);
        assertTrue(orderedProperties.containsKey("key1"));
    }

    @Test
    public void testComputeIfAbsentWithNewKey() {
        Function<Object, Object> mappingFunction = key -> "newValue";
        Object result = orderedProperties.computeIfAbsent("key2", mappingFunction);
        assertEquals("newValue", result);
        assertTrue(orderedProperties.containsKey("key2"));
    }

    @Test
    public void testComputeIfAbsentWithNullMappingFunction() {
        Function<Object, Object> mappingFunction = key -> null;
        Object result = orderedProperties.computeIfAbsent("key3", mappingFunction);
        assertNull(result);
        assertFalse(orderedProperties.containsKey("key3"));
    }
}
