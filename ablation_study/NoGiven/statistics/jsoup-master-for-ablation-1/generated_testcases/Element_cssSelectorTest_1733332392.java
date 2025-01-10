
package org.jsoup.nodes;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Element_cssSelectorTest {
    private Document doc;
    private Element element;

    @BeforeEach
    public void setUp() {
        doc = Jsoup.parse("<html><body><div id='uniqueDiv' class='testClass'><p>Test</p></div></body></html>");
        element = doc.selectFirst("div");
    }

    @Test
    public void testCssSelectorWithUniqueId() {
        assertEquals("#uniqueDiv", element.cssSelector());
    }

    @Test
    public void testCssSelectorWithoutUniqueId() {
        element.attr("id", "");
        assertEquals("div.testClass", element.cssSelector());
    }

    @Test
    public void testCssSelectorWithNoOwnerDocument() {
        element.removeAttr("id");
        element.removeAttr("class");
        element.ownerDocument().remove();
        assertEquals("div", element.cssSelector());
    }

    @Test
    public void testCssSelectorWithParent() {
        Element child = element.selectFirst("p");
        assertEquals("div.testClass > p", child.cssSelector());
    }

    @Test
    public void testCssSelectorWithMultipleClasses() {
        element.addClass("anotherClass");
        assertEquals("div.testClass.anotherClass", element.cssSelector());
    }

    @Test
    public void testCssSelectorWithNoAttributes() {
        element.removeAttr("id");
        element.removeAttr("class");
        assertEquals("div", element.cssSelector());
    }

    @Test
    public void testCssSelectorWithNestedElements() {
        Element nested = element.appendElement("div").appendElement("p");
        assertEquals("div.testClass > div > p", nested.cssSelector());
    }

    @Test
    public void testCssSelectorWithSiblingElements() {
        Element sibling = element.parent().appendElement("div");
        assertEquals("div.testClass", element.cssSelector());
        assertEquals("div", sibling.cssSelector());
    }

    @Test
    public void testCssSelectorWithDocumentRoot() {
        Element root = doc.selectFirst("html");
        assertEquals("html", root.cssSelector());
    }

    @Test
    public void testCssSelectorWithEmptyDocument() {
        Document emptyDoc = Jsoup.parse("");
        Element emptyElement = emptyDoc.appendElement("div");
        assertEquals("div", emptyElement.cssSelector());
    }
}
