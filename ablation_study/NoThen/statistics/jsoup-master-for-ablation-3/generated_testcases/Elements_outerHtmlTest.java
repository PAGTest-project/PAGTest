
package org.jsoup.select;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Elements_outerHtmlTest {
    private Elements elements;

    @BeforeEach
    public void setUp() {
        Document doc = Jsoup.parse("<div><p>Paragraph 1</p><p>Paragraph 2</p></div>");
        elements = new Elements(doc.select("p"));
    }

    @Test
    public void testOuterHtml() {
        String expected = "<p>Paragraph 1</p>\n<p>Paragraph 2</p>";
        assertEquals(expected, elements.outerHtml());
    }

    @Test
    public void testOuterHtmlWithEmptyElements() {
        elements.empty();
        String expected = "";
        assertEquals(expected, elements.outerHtml());
    }

    @Test
    public void testOuterHtmlWithWrappedElements() {
        elements.wrap("<div class='wrapper'></div>");
        String expected = "<div class='wrapper'><p>Paragraph 1</p></div>\n<div class='wrapper'><p>Paragraph 2</p></div>";
        assertEquals(expected, elements.outerHtml());
    }
}
