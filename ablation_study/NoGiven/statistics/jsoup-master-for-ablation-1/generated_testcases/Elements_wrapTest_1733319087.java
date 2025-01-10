
package org.jsoup.select;

import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Elements_wrapTest {

    @Test
    void testWrap() {
        // Given
        Elements elements = new Elements();
        Element element = new Element("div");
        elements.add(element);
        String html = "<p>Test</p>";

        // When
        Elements wrappedElements = elements.wrap(html);

        // Then
        assertEquals(elements, wrappedElements);
        assertEquals("<p>Test</p>", element.html());
    }

    @Test
    void testWrapWithEmptyHtml() {
        // Given
        Elements elements = new Elements();
        Element element = new Element("div");
        elements.add(element);
        String html = "";

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> elements.wrap(html));
    }
}
