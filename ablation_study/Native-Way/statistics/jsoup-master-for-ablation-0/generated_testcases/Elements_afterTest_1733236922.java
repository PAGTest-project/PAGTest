
package org.jsoup.select;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Elements_afterTest {
    private Elements elements;

    @BeforeEach
    public void setUp() {
        Document doc = Jsoup.parse("<div id='test'><p>Hello</p></div>");
        elements = doc.select("p");
    }

    @Test
    public void testAfterWithSingleElement() {
        elements.after("<span>World</span>");
        assertEquals("<p>Hello</p><span>World</span>", elements.first().outerHtml());
    }

    @Test
    public void testAfterWithMultipleElements() {
        Document doc = Jsoup.parse("<div id='test'><p>Hello</p><p>Goodbye</p></div>");
        elements = doc.select("p");
        elements.after("<span>World</span>");
        assertEquals("<p>Hello</p><span>World</span><p>Goodbye</p><span>World</span>", doc.getElementById("test").html());
    }

    @Test
    public void testAfterWithEmptyElements() {
        elements = new Elements();
        elements.after("<span>World</span>");
        assertEquals(0, elements.size());
    }

    @Test
    public void testAfterWithNullHtml() {
        assertThrows(IllegalArgumentException.class, () -> elements.after(null));
    }

    @Test
    public void testAfterWithEmptyHtml() {
        elements.after("");
        assertEquals("<p>Hello</p>", elements.first().outerHtml());
    }
}
