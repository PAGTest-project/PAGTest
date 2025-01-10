
package org.jsoup.select;

import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Elements_outerHtmlTest {

    @Test
    public void testOuterHtml() {
        // Given
        Elements elements = new Elements();
        Element element1 = new Element("div");
        element1.html("<p>Hello</p>");
        Element element2 = new Element("span");
        element2.html("<a href='#'>Link</a>");
        elements.add(element1);
        elements.add(element2);

        // When
        String result = elements.outerHtml();

        // Then
        String expected = "<div><p>Hello</p></div>\n<span><a href='#'>Link</a></span>";
        assertEquals(expected, result);
    }
}
