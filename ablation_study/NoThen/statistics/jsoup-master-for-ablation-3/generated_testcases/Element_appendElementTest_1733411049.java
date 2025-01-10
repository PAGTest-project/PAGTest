
package org.jsoup.nodes;

import org.jsoup.Jsoup;
import org.jsoup.parser.Tag;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Element_appendElementTest {
    private Element parentElement;

    @BeforeEach
    public void setUp() {
        parentElement = new Element("div");
    }

    @Test
    public void testAppendElement() {
        Element child = parentElement.appendElement("span", "http://www.w3.org/1999/xhtml");
        assertEquals(Tag.valueOf("span", "http://www.w3.org/1999/xhtml", Parser.htmlParser().settings()), child.tag());
        assertEquals(1, parentElement.childNodeSize());
        assertEquals(child, parentElement.child(0));
    }

    @Test
    public void testAppendElementWithDefaultNamespace() {
        Element child = parentElement.appendElement("span");
        assertEquals(Tag.valueOf("span", Parser.NamespaceHtml, Parser.htmlParser().settings()), child.tag());
        assertEquals(1, parentElement.childNodeSize());
        assertEquals(child, parentElement.child(0));
    }

    @Test
    public void testAppendElementWithInvalidTagName() {
        assertThrows(IllegalArgumentException.class, () -> parentElement.appendElement("", "http://www.w3.org/1999/xhtml"));
    }

    @Test
    public void testAppendElementWithNullTagName() {
        assertThrows(IllegalArgumentException.class, () -> parentElement.appendElement(null, "http://www.w3.org/1999/xhtml"));
    }

    @Test
    public void testAppendElementWithNullNamespace() {
        Element child = parentElement.appendElement("span", null);
        assertEquals(Tag.valueOf("span", Parser.NamespaceHtml, Parser.htmlParser().settings()), child.tag());
        assertEquals(1, parentElement.childNodeSize());
        assertEquals(child, parentElement.child(0));
    }
}
