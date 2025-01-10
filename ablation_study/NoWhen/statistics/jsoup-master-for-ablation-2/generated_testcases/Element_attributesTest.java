
package org.jsoup.nodes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Element_attributesTest {
    private Element element;

    @BeforeEach
    public void setUp() {
        element = new Element("div");
    }

    @Test
    public void testAttributesNotNull() {
        Attributes attributes = element.attributes();
        assertNotNull(attributes);
    }

    @Test
    public void testAttributesLazyInitialization() {
        assertNull(element.attributes);
        Attributes attributes = element.attributes();
        assertNotNull(attributes);
        assertNotNull(element.attributes);
    }

    @Test
    public void testAttributesAfterSetting() {
        element.attr("class", "testClass");
        Attributes attributes = element.attributes();
        assertTrue(attributes.hasKey("class"));
        assertEquals("testClass", attributes.get("class"));
    }

    @Test
    public void testAttributesAfterClearing() {
        element.attr("class", "testClass");
        element.attributes().clear();
        Attributes attributes = element.attributes();
        assertFalse(attributes.hasKey("class"));
    }
}
