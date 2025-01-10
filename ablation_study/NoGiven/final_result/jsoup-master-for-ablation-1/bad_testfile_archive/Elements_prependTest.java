
package org.jsoup.select;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Elements_prependTest {

    @Test
    public void testPrepend() {
        // Given
        Elements elements = new Elements();
        Element element = new Element("div");
        elements.add(element);
        String initialHtml = "<p>Initial Content</p>";
        String prependHtml = "<p>Prepended Content</p>";

        // When
        element.html(initialHtml);
        elements.prepend(prependHtml);

        // Then
        assertEquals(prependHtml + initialHtml, element.html());
    }
}
