
package org.jsoup.nodes;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class Attribute_setValueTest {

    @Test
    public void testSetValue_NoParent() {
        Attribute attribute = new Attribute("key", "oldValue", null);
        String result = attribute.setValue("newValue");
        assertEquals("oldValue", result);
        assertEquals("newValue", attribute.getValue());
    }

    @Test
    public void testSetValue_WithParent() {
        Attributes parent = Mockito.mock(Attributes.class);
        when(parent.indexOfKey("key")).thenReturn(0);
        when(parent.get("key")).thenReturn("parentValue");

        Attribute attribute = new Attribute("key", "oldValue", parent);
        String result = attribute.setValue("newValue");
        assertEquals("parentValue", result);
        assertEquals("newValue", attribute.getValue());
    }

    @Test
    public void testSetValue_WithParentNotFound() {
        Attributes parent = Mockito.mock(Attributes.class);
        when(parent.indexOfKey("key")).thenReturn(Attributes.NotFound);

        Attribute attribute = new Attribute("key", "oldValue", parent);
        String result = attribute.setValue("newValue");
        assertEquals("oldValue", result);
        assertEquals("newValue", attribute.getValue());
    }
}
