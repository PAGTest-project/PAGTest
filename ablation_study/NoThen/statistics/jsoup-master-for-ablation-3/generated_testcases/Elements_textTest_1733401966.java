
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
        assertEquals("Hello World", elements.text().trim());
    }

    @Test
    public void testTextWithNoElements() {
        elements = new Elements();
        assertEquals("", elements.text().trim());
    }

    @Test
    public void testTextWithSingleElement() {
        Document doc = Jsoup.parse("<div><p>Single</p></div>");
        elements = new Elements(doc.select("p"));
        assertEquals("Single", elements.text().trim());
    }

    @Test
    public void testTextWithEmptyTextNodes() {
        Document doc = Jsoup.parse("<div><p></p><p>World</p></div>");
        elements = new Elements(doc.select("p"));
        assertEquals("World", elements.text().trim());
    }

    @Test
    public void testTextWithMixedTextNodes() {
        Document doc = Jsoup.parse("<div><p>Hello</p><p></p><p>World</p></div>");
        elements = new Elements(doc.select("p"));
        assertEquals("Hello World", elements.text().trim());
    }
}
