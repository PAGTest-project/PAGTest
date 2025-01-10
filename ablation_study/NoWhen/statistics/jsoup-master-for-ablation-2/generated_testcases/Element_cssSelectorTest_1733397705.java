
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
        String html = "<body><div id='uniqueId'><p>One</p></div><div class='class1'><p>Two</p></div></body>";
        doc = Jsoup.parse(html);
        element = doc.selectFirst("div");
    }

    @Test
    public void testCssSelectorWithId() {
        Element divWithId = doc.selectFirst("div#uniqueId");
        assertNotNull(divWithId);
        assertEquals("#uniqueId", divWithId.cssSelector());
    }

    @Test
    public void testCssSelectorWithoutId() {
        Element divWithoutId = doc.selectFirst("div.class1");
        assertNotNull(divWithoutId);
        assertEquals("div.class1", divWithoutId.cssSelector());
    }

    @Test
    public void testCssSelectorWithNonUniqueId() {
        String html = "<body><div id='nonUniqueId'><p>One</p></div><div id='nonUniqueId'><p>Two</p></div></body>";
        Document doc = Jsoup.parse(html);
        Element div = doc.selectFirst("div#nonUniqueId");
        assertNotNull(div);
        assertEquals("div#nonUniqueId", div.cssSelector());
    }

    @Test
    public void testCssSelectorWithNoOwnerDocument() {
        Element div = new Element("div").attr("id", "noDocId");
        assertEquals("#noDocId", div.cssSelector());
    }

    @Test
    public void testCssSelectorWithNestedElements() {
        Element nestedDiv = doc.selectFirst("div#uniqueId p");
        assertNotNull(nestedDiv);
        assertEquals("div#uniqueId p", nestedDiv.cssSelector());
    }

    @Test
    public void testCssSelectorWithMultipleClasses() {
        String html = "<body><div class='class1 class2'><p>One</p></div></body>";
        Document doc = Jsoup.parse(html);
        Element div = doc.selectFirst("div.class1.class2");
        assertNotNull(div);
        assertEquals("div.class1.class2", div.cssSelector());
    }

    @Test
    public void testCssSelectorWithNoAttributes() {
        Element div = new Element("div");
        assertEquals("div", div.cssSelector());
    }
}
