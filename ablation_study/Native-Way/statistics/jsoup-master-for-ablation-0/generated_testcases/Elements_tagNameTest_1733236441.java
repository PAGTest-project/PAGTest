
package org.jsoup.select;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Elements_tagNameTest {

    @Test
    public void testTagName() {
        Elements elements = new Elements();
        Element element1 = new Element(Jsoup.parse("<div></div>").body().child(0));
        Element element2 = new Element(Jsoup.parse("<span></span>").body().child(0));
        elements.add(element1);
        elements.add(element2);

        elements.tagName("p");

        assertEquals("p", element1.tagName());
        assertEquals("p", element2.tagName());
    }
}
