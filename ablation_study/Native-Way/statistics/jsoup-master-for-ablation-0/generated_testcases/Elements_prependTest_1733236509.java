
package org.jsoup.select;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Elements_prependTest {

    @Test
    public void testPrepend() {
        Elements elements = new Elements();
        Element element1 = new Element("div");
        Element element2 = new Element("div");
        elements.add(element1);
        elements.add(element2);

        elements.prepend("<p>Hello</p>");

        assertEquals("<p>Hello</p>", element1.html());
        assertEquals("<p>Hello</p>", element2.html());
    }
}
