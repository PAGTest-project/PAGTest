
package org.jsoup.nodes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Attribute_getValueTest {
    private Attribute attribute;

    @BeforeEach
    public void setUp() {
        attribute = new Attribute("key", "value");
    }

    @Test
    public void testGetValueWithNonNullValue() {
        assertEquals("value", attribute.getValue());
    }

    @Test
    public void testGetValueWithNullValue() {
        attribute.setValue(null);
        assertEquals("", attribute.getValue());
    }
}
