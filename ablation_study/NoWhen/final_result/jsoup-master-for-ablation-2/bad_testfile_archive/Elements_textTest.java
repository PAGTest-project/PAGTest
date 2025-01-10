
package org.jsoup.select;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Elements_textTest {
    private Elements elements;

    @BeforeEach
    public void setUp() {
        Document doc = Jsoup.parse("<div><p>Hello</p><p>World</p></div>");
        elements = new Elements(doc.select("p"));
    }

    @Test
    public void testText() {
        assertEquals("Hello World", elements.text());
    }

    @Test
    public void testTextWithEmptyElements() {
        elements = new Elements();
        assertEquals("", elements.text());
    }

    @Test
    public void testTextWithSingleElement() {
        Document doc = Jsoup.parse("<div><p>Single</p></div>");
        elements = new Elements(doc.select("p"));
        assertEquals("Single", elements.text());
    }

    @Test
    public void testTextWithNoTextNodes() {
        Document doc = Jsoup.parse("<div><p></p></div>");
        elements = new Elements(doc.select("p"));
        assertEquals("", elements.text());
    }

    @Test
    public void testTextWithMixedContent() {
        Document doc = Jsoup.parse("<div><p>Hello</p><p><span>World</span></p></div>");
        elements = new Elements(doc.select("p"));
        assertEquals("Hello World", elements.text());
    }
}
