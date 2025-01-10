
package org.jsoup.select;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Elements_htmlTest {
    private Elements elements;

    @BeforeEach
    public void setUp() {
        Document doc = Jsoup.parse("<div><p>First</p><p>Second</p></div>");
        elements = new Elements(doc.select("p"));
    }

    @Test
    public void testHtml() {
        String expectedHtml = "First\nSecond";
        assertEquals(expectedHtml, elements.html());
    }

    @Test
    public void testHtmlWithEmptyElements() {
        elements = new Elements();
        assertEquals("", elements.html());
    }

    @Test
    public void testHtmlWithSingleElement() {
        Document doc = Jsoup.parse("<div><p>Single</p></div>");
        elements = new Elements(doc.select("p"));
        assertEquals("Single", elements.html());
    }

    @Test
    public void testHtmlWithNestedElements() {
        Document doc = Jsoup.parse("<div><p>Outer<span>Inner</span></p></div>");
        elements = new Elements(doc.select("p"));
        assertEquals("Outer<span>Inner</span>", elements.html());
    }
}
