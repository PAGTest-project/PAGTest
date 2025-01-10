
package org.apache.commons.collections4.properties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Properties;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderedProperties_removeTest {

    private OrderedProperties orderedProperties;

    @BeforeEach
    public void setUp() {
        orderedProperties = new OrderedProperties();
    }

    @Test
    public void testRemoveExistingKey() {
        orderedProperties.put("key1", "value1");
        Object removedValue = orderedProperties.remove("key1");
        assertEquals("value1", removedValue);
        assertNull(orderedProperties.get("key1"));
        assertTrue(orderedProperties.isEmpty());
    }

    @Test
    public void testRemoveNonExistingKey() {
        Object removedValue = orderedProperties.remove("nonExistingKey");
        assertNull(removedValue);
        assertTrue(orderedProperties.isEmpty());
    }

    @Test
    public void testRemoveAfterMultiplePuts() {
        orderedProperties.put("key1", "value1");
        orderedProperties.put("key2", "value2");
        orderedProperties.put("key3", "value3");

        Object removedValue = orderedProperties.remove("key2");
        assertEquals("value2", removedValue);
        assertNull(orderedProperties.get("key2"));
        assertEquals(2, orderedProperties.size());
        assertTrue(orderedProperties.containsKey("key1"));
        assertTrue(orderedProperties.containsKey("key3"));
    }

    @Test
    public void testRemoveAllKeys() {
        orderedProperties.put("key1", "value1");
        orderedProperties.put("key2", "value2");
        orderedProperties.put("key3", "value3");

        orderedProperties.remove("key1");
        orderedProperties.remove("key2");
        orderedProperties.remove("key3");

        assertTrue(orderedProperties.isEmpty());
    }
}
