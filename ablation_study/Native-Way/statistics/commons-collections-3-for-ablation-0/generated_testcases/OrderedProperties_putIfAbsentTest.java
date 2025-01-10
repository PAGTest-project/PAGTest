
package org.apache.commons.collections4.properties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderedProperties_putIfAbsentTest {

    private OrderedProperties orderedProperties;

    @BeforeEach
    public void setUp() {
        orderedProperties = new OrderedProperties();
    }

    @Test
    public void testPutIfAbsentNewKey() {
        Object key = "newKey";
        Object value = "newValue";
        Object result = orderedProperties.putIfAbsent(key, value);

        assertNull(result);
        assertTrue(orderedProperties.keySet().contains(key));
        assertEquals(value, orderedProperties.get(key));
    }

    @Test
    public void testPutIfAbsentExistingKey() {
        Object key = "existingKey";
        Object value = "existingValue";
        orderedProperties.put(key, value);

        Object newValue = "newValue";
        Object result = orderedProperties.putIfAbsent(key, newValue);

        assertEquals(value, result);
        assertTrue(orderedProperties.keySet().contains(key));
        assertEquals(value, orderedProperties.get(key));
    }
}
