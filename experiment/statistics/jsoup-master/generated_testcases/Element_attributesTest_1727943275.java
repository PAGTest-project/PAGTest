
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
        assertTrue(attributes.isEmpty());
    }

    @Test
    public void testAttributesReuse() {
        Attributes attributes1 = element.attributes();
        Attributes attributes2 = element.attributes();
        assertSame(attributes1, attributes2);
    }
}
