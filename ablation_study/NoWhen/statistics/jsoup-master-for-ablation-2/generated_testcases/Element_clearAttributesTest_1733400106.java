
package org.jsoup.nodes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Element_clearAttributesTest {
    private Element element;

    @BeforeEach
    public void setUp() {
        element = new Element("div");
    }

    @Test
    public void testClearAttributesWithAttributes() {
        element.attr("class", "test");
        element.attr("id", "testId");

        Element clearedElement = element.clearAttributes();

        assertFalse(clearedElement.hasAttributes());
        assertNull(clearedElement.attributes());
    }

    @Test
    public void testClearAttributesWithoutAttributes() {
        Element clearedElement = element.clearAttributes();

        assertFalse(clearedElement.hasAttributes());
        assertNull(clearedElement.attributes());
    }

    @Test
    public void testClearAttributesReturnsThis() {
        element.attr("class", "test");
        Element clearedElement = element.clearAttributes();

        assertSame(element, clearedElement);
    }
}
