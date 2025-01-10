
package org.jsoup.select;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Elements_attrTest {
    private Elements elements;

    @BeforeEach
    public void setUp() {
        Document doc = Jsoup.parse("<p><a href='/foo'>Foo</a><a href='/bar'>Bar</a><a>None</a></p>");
        elements = new Elements(doc.select("a"));
    }

    @Test
    public void testAttrFound() {
        String href = elements.attr("href");
        assertEquals("/foo", href);
    }

    @Test
    public void testAttrNotFound() {
        String href = elements.attr("nonexistent");
        assertEquals("", href);
    }
}
