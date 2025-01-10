
package org.jsoup.nodes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Attribute_setValueTest {
    private Attribute attribute;
    private Attributes parent;

    @BeforeEach
    public void setUp() {
        parent = new Attributes();
        attribute = new Attribute("key", "value", parent);
    }

    @Test
    public void testSetValueWithParent() {
        String oldVal = attribute.setValue("newValue");
        assertEquals("value", oldVal);
        assertEquals("newValue", attribute.getValue());
        assertEquals("newValue", parent.get("key"));
    }

    @Test
    public void testSetValueWithoutParent() {
        attribute.parent = null;
        String oldVal = attribute.setValue("newValue");
        assertEquals("value", oldVal);
        assertEquals("newValue", attribute.getValue());
    }

    @Test
    public void testSetValueNull() {
        String oldVal = attribute.setValue(null);
        assertEquals("value", oldVal);
        assertNull(attribute.getValue());
        assertNull(parent.get("key"));
    }

    @Test
    public void testSetValueEmptyString() {
        String oldVal = attribute.setValue("");
        assertEquals("value", oldVal);
        assertEquals("", attribute.getValue());
        assertEquals("", parent.get("key"));
    }

    @Test
    public void testSetValueWithExistingKey() {
        parent.put("key", "existingValue");
        String oldVal = attribute.setValue("newValue");
        assertEquals("existingValue", oldVal);
        assertEquals("newValue", attribute.getValue());
        assertEquals("newValue", parent.get("key"));
    }

    @Test
    public void testSetValueWithNonExistingKey() {
        attribute.key = "newKey";
        String oldVal = attribute.setValue("newValue");
        assertNull(oldVal);
        assertEquals("newValue", attribute.getValue());
        assertNull(parent.get("newKey"));
    }

    @Test
    public void testSetValueWithParentAndNullOldValue() {
        attribute.val = null;
        String oldVal = attribute.setValue("newValue");
        assertEquals("", oldVal);
        assertEquals("newValue", attribute.getValue());
        assertEquals("newValue", parent.get("key"));
    }

    @Test
    public void testSetValueWithParentAndEmptyOldValue() {
        attribute.val = "";
        String oldVal = attribute.setValue("newValue");
        assertEquals("", oldVal);
        assertEquals("newValue", attribute.getValue());
        assertEquals("newValue", parent.get("key"));
    }
}
