
package org.jsoup.nodes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Attributes_attributeTest {
    private Attributes attributes;

    @BeforeEach
    public void setUp() {
        attributes = new Attributes();
    }

    @Test
    public void testAttributeFound() {
        attributes.put("key1", "value1");
        Attribute attr = attributes.attribute("key1");
        assertNotNull(attr);
        assertEquals("key1", attr.getKey());
        assertEquals("value1", attr.getValue());
    }

    @Test
    public void testAttributeNotFound() {
        Attribute attr = attributes.attribute("nonexistentKey");
        assertNull(attr);
    }
}
