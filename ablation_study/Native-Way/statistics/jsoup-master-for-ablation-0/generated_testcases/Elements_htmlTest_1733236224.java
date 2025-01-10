
package org.jsoup.select;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Elements_htmlTest {
    private Elements elements;

    @BeforeEach
    public void setUp() {
        Document doc = Jsoup.parse("<div><p>Paragraph 1</p><p>Paragraph 2</p></div>");
        elements = doc.select("p");
    }

    @Test
    public void testHtml() {
        String expectedHtml = "<p>Paragraph 1</p>\n<p>Paragraph 2</p>";
        assertEquals(expectedHtml, elements.outerHtml());
    }

    @Test
    public void testHtmlWithEmptyElements() {
        Document doc = Jsoup.parse("<div></div>");
        Elements emptyElements = doc.select("p");
        assertEquals("", emptyElements.outerHtml());
    }

    @Test
    public void testHtmlWithSingleElement() {
        Document doc = Jsoup.parse("<div><p>Single Paragraph</p></div>");
        Elements singleElement = doc.select("p");
        assertEquals("<p>Single Paragraph</p>", singleElement.outerHtml());
    }

    @Test
    public void testHtmlWithNestedElements() {
        Document doc = Jsoup.parse("<div><p>Paragraph <span>with span</span></p></div>");
        Elements nestedElements = doc.select("p");
        assertEquals("<p>Paragraph <span>with span</span></p>", nestedElements.outerHtml());
    }
}
