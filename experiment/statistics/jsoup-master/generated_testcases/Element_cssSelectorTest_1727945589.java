
package org.jsoup.nodes;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Element_cssSelectorTest {
    private Document doc;
    private Element element;

    @BeforeEach
    public void setUp() {
        doc = Jsoup.parse("<div id='uniqueId' class='testClass'><p>Test</p></div>");
        element = doc.select("div").first();
    }

    @Test
    public void testCssSelectorWithUniqueId() {
        assertEquals("#uniqueId", element.cssSelector());
    }

    @Test
    public void testCssSelectorWithoutUniqueId() {
        element.removeAttr("id");
        assertEquals("div.testClass", element.cssSelector());
    }

    @Test
    public void testCssSelectorWithNoOwnerDocument() {
        element.removeAttr("id");
        element.remove(); // Remove element from document
        assertEquals("div.testClass", element.cssSelector());
    }

    @Test
    public void testCssSelectorWithNonUniqueId() {
        doc = Jsoup.parse("<div id='nonUniqueId'><p>Test</p></div><div id='nonUniqueId'><p>Test</p></div>");
        element = doc.select("div").first();
        assertEquals("div#nonUniqueId:nth-child(1)", element.cssSelector());
    }
}
