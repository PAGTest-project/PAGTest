
package org.jsoup.select;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Elements_tagNameTest {
    private Elements elements;

    @BeforeEach
    public void setUp() {
        Document doc = Jsoup.parse("<div><p>One</p><p>Two</p></div>");
        elements = new Elements(doc.select("p"));
    }

    @Test
    public void testTagName() {
        elements.tagName("span");
        for (Element element : elements) {
            assertEquals("span", element.tagName());
        }
        assertEquals(elements, elements.tagName("span"));
    }
}
