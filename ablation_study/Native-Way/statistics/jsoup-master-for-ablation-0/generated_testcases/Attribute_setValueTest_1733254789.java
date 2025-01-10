
package org.jsoup.nodes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Attribute_setValueTest {
    private Attribute attribute;

    @BeforeEach
    public void setUp() {
        attribute = new Attribute("key", "value", new Attributes());
    }

    @Test
    public void testSetValueWithParent() {
        String oldVal = attribute.setValue("newValue");
        assertEquals("value", oldVal);
        assertEquals("newValue", attribute.getValue());
    }

    @Test
    public void testSetValueWithoutParent() {
        attribute = new Attribute("key", "value");
        String oldVal = attribute.setValue("newValue");
        assertEquals("value", oldVal);
        assertEquals("newValue", attribute.getValue());
    }

    @Test
    public void testSetValueNull() {
        String oldVal = attribute.setValue(null);
        assertEquals("value", oldVal);
        assertNull(attribute.getValue());
    }

    @Test
    public void testSetValueEmptyString() {
        String oldVal = attribute.setValue("");
        assertEquals("value", oldVal);
        assertEquals("", attribute.getValue());
    }

    @Test
    public void testSetValueWithParentKeyNotFound() {
        Attributes parent = new Attributes();
        attribute = new Attribute("key", "value", parent);
        String oldVal = attribute.setValue("newValue");
        assertEquals("value", oldVal);
        assertEquals("newValue", attribute.getValue());
    }
}
