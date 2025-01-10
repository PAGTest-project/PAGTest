
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
        Document doc = Jsoup.parse("<div><p>Paragraph 1</p><p>Paragraph 2</p></div>");
        elements = doc.select("p");
    }

    @Test
    public void testHtml() {
        String expectedHtml = "Paragraph 1\nParagraph 2";
        assertEquals(expectedHtml, elements.html());
    }

    @Test
    public void testHtmlWithEmptyElements() {
        Document doc = Jsoup.parse("<div></div>");
        Elements emptyElements = doc.select("p");
        assertEquals("", emptyElements.html());
    }

    @Test
    public void testHtmlWithSingleElement() {
        Document doc = Jsoup.parse("<div><p>Single Paragraph</p></div>");
        Elements singleElement = doc.select("p");
        assertEquals("Single Paragraph", singleElement.html());
    }

    @Test
    public void testHtmlWithNestedElements() {
        Document doc = Jsoup.parse("<div><p>Paragraph <span>with span</span></p></div>");
        Elements nestedElements = doc.select("p");
        assertEquals("Paragraph <span>with span</span>", nestedElements.html());
    }
}
