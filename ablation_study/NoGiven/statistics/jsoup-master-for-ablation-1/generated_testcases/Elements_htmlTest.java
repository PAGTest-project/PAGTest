
package org.jsoup.select;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Elements_htmlTest {
    private Elements elements;

    @BeforeEach
    public void setUp() {
        Document doc = Jsoup.parse("<div><p>First</p><p>Second</p></div>");
        elements = new Elements(doc.select("p"));
    }

    @Test
    public void testHtml() {
        String expectedHtml = "<p>First</p>\n<p>Second</p>";
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
        assertEquals("<p>Single</p>", elements.html());
    }
}
