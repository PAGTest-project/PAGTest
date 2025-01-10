
package org.apache.commons.collections4.properties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderedProperties_putTest {

    private OrderedProperties orderedProperties;

    @BeforeEach
    public void setUp() {
        orderedProperties = new OrderedProperties();
    }

    @Test
    public void testPutNewKey() {
        Object key = "newKey";
        Object value = "newValue";
        Object result = orderedProperties.put(key, value);
        assertNull(result);
        assertTrue(orderedProperties.keySet().contains(key));
    }

    @Test
    public void testPutExistingKey() {
        Object key = "existingKey";
        Object value1 = "value1";
        Object value2 = "value2";
        orderedProperties.put(key, value1);
        Object result = orderedProperties.put(key, value2);
        assertEquals(value1, result);
        assertTrue(orderedProperties.keySet().contains(key));
    }

    @Test
    public void testPutNullKey() {
        Object key = null;
        Object value = "nullValue";
        Object result = orderedProperties.put(key, value);
        assertNull(result);
        assertTrue(orderedProperties.keySet().contains(key));
    }

    @Test
    public void testPutNullValue() {
        Object key = "nullValueKey";
        Object value = null;
        Object result = orderedProperties.put(key, value);
        assertNull(result);
        assertTrue(orderedProperties.keySet().contains(key));
    }

    @Test
    public void testPutAll() {
        OrderedProperties sourceProperties = new OrderedProperties();
        int first = 1;
        int last = 11;
        for (int i = first; i <= last; i++) {
            sourceProperties.put("key" + i, "value" + i);
        }
        orderedProperties.putAll(sourceProperties);
        assertAscendingOrder(orderedProperties);
        orderedProperties.clear();
        first = 11;
        last = 1;
        for (int i = first; i >= last; i--) {
            orderedProperties.put("key" + i, "value" + i);
        }
        assertDescendingOrder(orderedProperties);
    }

    private void assertAscendingOrder(OrderedProperties properties) {
        int expectedKey = 1;
        for (Object key : properties.keySet()) {
            assertEquals("key" + expectedKey, key);
            expectedKey++;
        }
    }

    private void assertDescendingOrder(OrderedProperties properties) {
        int expectedKey = 11;
        for (Object key : properties.keySet()) {
            assertEquals("key" + expectedKey, key);
            expectedKey--;
        }
    }
}
