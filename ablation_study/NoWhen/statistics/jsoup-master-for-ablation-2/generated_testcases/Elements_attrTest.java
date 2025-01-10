
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
    public void testAttrPresent() {
        assertEquals("/foo", elements.attr("href"));
    }

    @Test
    public void testAttrNotPresent() {
        assertEquals("", elements.attr("nonexistent"));
    }

    @Test
    public void testAttrMultipleElements() {
        assertEquals("/foo", elements.attr("href"));
        elements.remove(0);
        assertEquals("/bar", elements.attr("href"));
    }
}
