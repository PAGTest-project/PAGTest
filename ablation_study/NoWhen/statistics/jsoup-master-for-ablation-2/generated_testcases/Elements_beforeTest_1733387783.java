
package org.jsoup.select;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Elements_beforeTest {
    private Elements elements;

    @BeforeEach
    public void setUp() {
        Document doc = Jsoup.parse("<div><p id='p1'>Paragraph 1</p><p id='p2'>Paragraph 2</p></div>");
        elements = new Elements(doc.select("p"));
    }

    @Test
    public void testBeforeWithValidHtml() {
        Elements result = elements.before("<span>Before</span>");
        assertEquals(2, result.size());
        assertEquals("<span>Before</span><p id=\"p1\">Paragraph 1</p>", result.get(0).outerHtml());
        assertEquals("<span>Before</span><p id=\"p2\">Paragraph 2</p>", result.get(1).outerHtml());
    }

    @Test
    public void testBeforeWithEmptyHtml() {
        Elements result = elements.before("");
        assertEquals(2, result.size());
        assertEquals("<p id=\"p1\">Paragraph 1</p>", result.get(0).outerHtml());
        assertEquals("<p id=\"p2\">Paragraph 2</p>", result.get(1).outerHtml());
    }

    @Test
    public void testBeforeWithNullHtml() {
        Elements result = elements.before(null);
        assertEquals(2, result.size());
        assertEquals("<p id=\"p1\">Paragraph 1</p>", result.get(0).outerHtml());
        assertEquals("<p id=\"p2\">Paragraph 2</p>", result.get(1).outerHtml());
    }
}
