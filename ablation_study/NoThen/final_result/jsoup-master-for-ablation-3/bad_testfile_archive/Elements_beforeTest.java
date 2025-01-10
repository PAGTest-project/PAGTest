
package org.jsoup.select;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Elements_beforeTest {
    private Elements elements;

    @BeforeEach
    public void setUp() {
        Document doc = Jsoup.parse("<div><p id='p1'>Paragraph 1</p><p id='p2'>Paragraph 2</p></div>");
        elements = new Elements(doc.select("p"));
    }

    @Test
    public void testBeforeWithSingleElement() {
        elements.get(0).before("<span>Before</span>");
        assertEquals("<span>Before</span><p id=\"p1\">Paragraph 1</p>", elements.get(0).outerHtml());
    }

    @Test
    public void testBeforeWithMultipleElements() {
        elements.before("<span>Before</span>");
        assertEquals("<span>Before</span><p id=\"p1\">Paragraph 1</p>", elements.get(0).outerHtml());
        assertEquals("<span>Before</span><p id=\"p2\">Paragraph 2</p>", elements.get(1).outerHtml());
    }

    @Test
    public void testBeforeWithEmptyElements() {
        Elements emptyElements = new Elements();
        emptyElements.before("<span>Before</span>");
        assertEquals(0, emptyElements.size());
    }
}
