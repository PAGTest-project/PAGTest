
package org.jsoup.nodes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Attribute_setValueTest {
    private Attribute attribute;

    @BeforeEach
    public void setUp() {
        attribute = new Attribute("key", "value", null);
    }

    @Test
    public void testSetValueWithParent() {
        Attributes parent = new Attributes();
        parent.put("key", "oldValue");
        attribute = new Attribute("key", "value", parent);

        String oldVal = attribute.setValue("newValue");
        assertEquals("oldValue", oldVal);
        assertEquals("newValue", attribute.getValue());
        assertEquals("newValue", parent.get("key"));
    }

    @Test
    public void testSetValueWithoutParent() {
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
    public void testSetValueSameValue() {
        String oldVal = attribute.setValue("value");
        assertEquals("value", oldVal);
        assertEquals("value", attribute.getValue());
    }

    @Test
    public void testSetValueWithParentNoKey() {
        Attributes parent = new Attributes();
        attribute = new Attribute("key", "value", parent);

        String oldVal = attribute.setValue("newValue");
        assertEquals("value", oldVal);
        assertEquals("newValue", attribute.getValue());
        assertNull(parent.get("key"));
    }

    @Test
    public void testSetValueWithParentDifferentKey() {
        Attributes parent = new Attributes();
        parent.put("otherKey", "otherValue");
        attribute = new Attribute("key", "value", parent);

        String oldVal = attribute.setValue("newValue");
        assertEquals("value", oldVal);
        assertEquals("newValue", attribute.getValue());
        assertNull(parent.get("key"));
        assertEquals("otherValue", parent.get("otherKey"));
    }
}
