
package org.apache.commons.collections4.properties;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class OrderedProperties_clearTest {

    private OrderedProperties orderedProperties;

    @BeforeEach
    public void setUp() {
        orderedProperties = new OrderedProperties();
    }

    @Test
    public void testClear() {
        // Add some elements to the properties
        orderedProperties.put("key1", "value1");
        orderedProperties.put("key2", "value2");
        orderedProperties.put("key3", "value3");

        // Ensure the properties are not empty before clearing
        assertFalse(orderedProperties.isEmpty());

        // Clear the properties
        orderedProperties.clear();

        // Ensure the properties are empty after clearing
        assertTrue(orderedProperties.isEmpty());
    }
}
