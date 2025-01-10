
package org.apache.commons.collections4.properties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderedProperties_toStringTest {
    private OrderedProperties orderedProperties;

    @BeforeEach
    public void setUp() {
        orderedProperties = new OrderedProperties();
    }

    @Test
    public void testToStringEmpty() {
        assertEquals("{}", orderedProperties.toString());
    }

    @Test
    public void testToStringSingleEntry() {
        orderedProperties.put("key1", "value1");
        assertEquals("{key1=value1}", orderedProperties.toString());
    }

    @Test
    public void testToStringMultipleEntries() {
        orderedProperties.put("key1", "value1");
        orderedProperties.put("key2", "value2");
        orderedProperties.put("key3", "value3");
        assertEquals("{key1=value1, key2=value2, key3=value3}", orderedProperties.toString());
    }

    @Test
    public void testToStringWithSelfReference() {
        orderedProperties.put("key1", "value1");
        orderedProperties.put("key2", orderedProperties);
        assertEquals("{key1=value1, key2=(this Map)}", orderedProperties.toString());
    }

    @Test
    public void testToStringAfterRemove() {
        orderedProperties.put("key1", "value1");
        orderedProperties.put("key2", "value2");
        orderedProperties.remove("key1");
        assertEquals("{key2=value2}", orderedProperties.toString());
    }
}
