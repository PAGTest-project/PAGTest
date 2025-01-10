
package org.jsoup.nodes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Element_clearAttributesTest {
    private Element element;

    @BeforeEach
    public void setUp() {
        element = new Element("div");
        element.attributes().put("id", "testId");
        element.attributes().put("class", "testClass");
    }

    @Test
    public void testClearAttributes() {
        element.clearAttributes();
        assertNull(element.attributes);
    }

    @Test
    public void testClearAttributesWithInternalAttributes() {
        element.attributes().put("internal", "internalValue");
        element.clearAttributes();
        assertNotNull(element.attributes);
        assertEquals(0, element.attributes.size());
    }

    @Test
    public void testClearAttributesWithNoAttributes() {
        element.clearAttributes();
        element.clearAttributes();
        assertNull(element.attributes);
    }
}
