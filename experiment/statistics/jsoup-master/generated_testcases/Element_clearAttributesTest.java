
package org.jsoup.nodes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Element_clearAttributesTest {
    private Element element;

    @BeforeEach
    public void setUp() {
        element = new Element("div");
        element.attr("id", "testId");
        element.attr("class", "testClass");
    }

    @Test
    public void testClearAttributes() {
        // Given: Element with attributes
        assertNotNull(element.attributes());
        assertEquals(2, element.attributes().size());

        // When: clearAttributes is called
        Element clearedElement = element.clearAttributes();

        // Then: Attributes should be cleared
        assertNotNull(clearedElement.attributes());
        assertEquals(0, clearedElement.attributes().size());
    }

    @Test
    public void testClearAttributesWithInternalAttributes() {
        // Given: Element with attributes and internal attributes
        element.attr("internalKey", "internalValue");
        assertNotNull(element.attributes());
        assertEquals(3, element.attributes().size());

        // When: clearAttributes is called
        Element clearedElement = element.clearAttributes();

        // Then: Only non-internal attributes should be cleared
        assertNotNull(clearedElement.attributes());
        assertEquals(1, clearedElement.attributes().size());
        assertEquals("internalValue", clearedElement.attributes().get("internalKey"));
    }
}
