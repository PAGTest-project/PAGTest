
package org.jsoup.select;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Elements_hasAttrTest {
    private Elements elements;

    @BeforeEach
    public void setUp() {
        Document doc = Jsoup.parse("<p><a href='/foo'>Foo</a><a href='/bar'>Bar</a><a>None</a></p>");
        elements = new Elements(doc.select("a"));
    }

    @Test
    public void testHasAttrPresent() {
        assertTrue(elements.hasAttr("href"));
    }

    @Test
    public void testHasAttrAbsent() {
        assertFalse(elements.hasAttr("nonexistent"));
    }

    @Test
    public void testHasAttrMixed() {
        Document doc = Jsoup.parse("<p><a href='/foo'>Foo</a><a>None</a></p>");
        Elements mixedElements = new Elements(doc.select("a"));
        assertTrue(mixedElements.hasAttr("href"));
    }
}
