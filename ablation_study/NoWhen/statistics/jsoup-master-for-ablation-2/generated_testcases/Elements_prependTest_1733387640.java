
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
        Element element1 = new Element("div");
        Element element2 = new Element("div");
        elements.add(element1);
        elements.add(element2);

        // When
        elements.prepend("<p>Hello</p>");

        // Then
        assertEquals("<p>Hello</p>", element1.child(0).outerHtml());
        assertEquals("<p>Hello</p>", element2.child(0).outerHtml());
    }
}
