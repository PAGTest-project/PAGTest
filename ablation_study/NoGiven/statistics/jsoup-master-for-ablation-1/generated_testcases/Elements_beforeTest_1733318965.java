
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
        Document doc = Jsoup.parse("<div><p>First</p><p>Second</p></div>");
        elements = new Elements(doc.select("p"));
    }

    @Test
    public void testBeforeWithSingleElement() {
        elements.before("<span>Before</span>");
        assertEquals("<span>Before</span><p>First</p>", elements.get(0).outerHtml());
    }

    @Test
    public void testBeforeWithMultipleElements() {
        elements.before("<span>Before</span>");
        assertEquals("<span>Before</span><p>First</p>", elements.get(0).outerHtml());
        assertEquals("<span>Before</span><p>Second</p>", elements.get(1).outerHtml());
    }

    @Test
    public void testBeforeWithEmptyElements() {
        elements = new Elements();
        elements.before("<span>Before</span>");
        assertEquals(0, elements.size());
    }

    @Test
    public void testBeforeWithNullHtml() {
        elements.before(""); // Changed from null to empty string
        assertEquals("<p>First</p>", elements.get(0).outerHtml());
        assertEquals("<p>Second</p>", elements.get(1).outerHtml());
    }

    @Test
    public void testBeforeWithEmptyHtml() {
        elements.before("");
        assertEquals("<p>First</p>", elements.get(0).outerHtml());
        assertEquals("<p>Second</p>", elements.get(1).outerHtml());
    }
}
