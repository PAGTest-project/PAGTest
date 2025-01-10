
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
        Document doc = Jsoup.parse("<div id='test' data-attr='value'><p class='test'></p></div>");
        elements = new Elements(doc.select("div, p"));
    }

    @Test
    public void testHasAttrPresent() {
        assertTrue(elements.hasAttr("data-attr"));
    }

    @Test
    public void testHasAttrNotPresent() {
        assertFalse(elements.hasAttr("nonexistent-attr"));
    }

    @Test
    public void testHasAttrMixed() {
        Document doc = Jsoup.parse("<div data-attr='value'></div><p></p>");
        Elements mixedElements = new Elements(doc.select("div, p"));
        assertTrue(mixedElements.hasAttr("data-attr"));
    }

    @Test
    public void testHasAttrEmptyElements() {
        Elements emptyElements = new Elements();
        assertFalse(emptyElements.hasAttr("data-attr"));
    }
}
