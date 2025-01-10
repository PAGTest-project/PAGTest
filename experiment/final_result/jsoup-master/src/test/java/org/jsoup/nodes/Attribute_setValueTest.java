
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
        parent.put("key", "value");
    }

    @Test
    public void testSetValueWithParent() {
        String result = attribute.setValue("newValue");
        assertEquals("value", result);
        assertEquals("newValue", parent.get("key"));
        assertEquals("newValue", attribute.getValue());
    }

    @Test
    public void testSetValueWithoutParent() {
        attribute.parent = null;
        String result = attribute.setValue("newValue");
        assertEquals("value", result);
        assertEquals("newValue", attribute.getValue());
    }

    @Test
    public void testSetValueWithNonExistentKey() {
        parent.remove("key");
        String result = attribute.setValue("newValue");
        assertEquals("value", result);
        assertEquals("newValue", attribute.getValue());
    }
}
