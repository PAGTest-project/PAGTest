
package org.jsoup.select;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Elements_appendTest {

    @Test
    public void testAppend() {
        // Given
        Elements elements = new Elements();
        Element element1 = new Element("div");
        Element element2 = new Element("p");
        elements.add(element1);
        elements.add(element2);

        // When
        elements.append("<span>Test</span>");

        // Then
        assertEquals("<div><span>Test</span></div>", element1.outerHtml());
        assertEquals("<p><span>Test</span></p>", element2.outerHtml());
    }
}
