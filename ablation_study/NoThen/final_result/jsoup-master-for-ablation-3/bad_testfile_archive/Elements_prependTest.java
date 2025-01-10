
package org.jsoup.select;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Elements_prependTest {

    @Test
    public void testPrepend() {
        Elements elements = new Elements();
        Element element = new Element("div");
        elements.add(element);

        elements.prepend("<p>Hello</p>");

        assertEquals("<p>Hello</p>", element.child(0).outerHtml());
    }
}
