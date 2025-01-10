
package org.jsoup.nodes;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Element_cssSelectorTest {
    private Document doc;
    private Element element;

    @BeforeEach
    public void setUp() {
        doc = Jsoup.parse("<div id='test'><p class='inner'>Text</p></div>");
        element = doc.select("div").first();
    }

    @Test
    public void testCssSelectorWithId() {
        element.attr("id", "uniqueId");
        assertEquals("#uniqueId", element.cssSelector());
    }

    @Test
    public void testCssSelectorWithoutId() {
        element.removeAttr("id");
        assertEquals("div", element.cssSelector());
    }

    @Test
    public void testCssSelectorWithNonUniqueId() {
        Document doc = Jsoup.parse("<div id='test'><div id='test'></div></div>");
        Element element = doc.select("div#test").first();
        assertEquals("div#test", element.cssSelector());
    }

    @Test
    public void testCssSelectorWithParent() {
        Element child = element.select("p").first();
        assertEquals("div#test > p.inner", child.cssSelector());
    }

    @Test
    public void testCssSelectorWithNoOwnerDocument() {
        element.remove();
        assertEquals("#test", element.cssSelector());
    }
}
