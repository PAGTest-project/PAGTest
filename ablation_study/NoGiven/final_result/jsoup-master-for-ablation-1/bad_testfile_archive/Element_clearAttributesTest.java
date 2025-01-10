
package org.jsoup.nodes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Element_clearAttributesTest {
    private Element element;

    @BeforeEach
    public void setUp() {
        element = new Element("div");
        element.attr("class", "test");
        element.attr("id", "testId");
    }

    @Test
    public void testClearAttributes() {
        element.clearAttributes();
        assertFalse(element.hasAttributes());
        assertNull(element.attributes());
    }

    @Test
    public void testClearAttributesWithInternalAttributes() {
        element.attr("data-internal", "value");
        element.clearAttributes();
        assertTrue(element.hasAttributes()); // Internal attributes should remain
        assertNotNull(element.attributes());
    }
}
