
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
    public void testAttributesInitialization() {
        Attributes attributes = element.attributes();
        assertNotNull(attributes);
    }

    @Test
    public void testAttributesNotNullAfterSetting() {
        element.attr("key", "value");
        Attributes attributes = element.attributes();
        assertNotNull(attributes);
        assertTrue(attributes.hasKey("key"));
    }

    @Test
    public void testAttributesNotNullAfterRemoving() {
        element.attr("key", "value");
        element.removeAttr("key");
        Attributes attributes = element.attributes();
        assertNotNull(attributes);
        assertFalse(attributes.hasKey("key"));
    }

    @Test
    public void testAttributesNotNullAfterClearing() {
        element.attr("key", "value");
        element.clearAttributes();
        Attributes attributes = element.attributes();
        assertNotNull(attributes);
        assertFalse(attributes.hasKey("key"));
    }
}
