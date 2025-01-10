
package org.jsoup.nodes;

import org.jsoup.Jsoup;
import org.jsoup.parser.Tag;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Element_prependElementTest {
    private Element element;

    @BeforeEach
    public void setUp() {
        element = new Element("div");
    }

    @Test
    public void testPrependElement() {
        Element child = element.prependElement("span", "http://www.w3.org/1999/xhtml");
        assertEquals("span", child.tagName());
        assertEquals("http://www.w3.org/1999/xhtml", child.tag().namespace());
        assertEquals(child, element.child(0));
    }

    @Test
    public void testPrependElementWithDefaultNamespace() {
        Element child = element.prependElement("span");
        assertEquals("span", child.tagName());
        assertEquals("http://www.w3.org/1999/xhtml", child.tag().namespace());
        assertEquals(child, element.child(0));
    }

    @Test
    public void testPrependElementWithExistingChildren() {
        element.appendChild(new Element("p"));
        Element child = element.prependElement("span", "http://www.w3.org/1999/xhtml");
        assertEquals("span", child.tagName());
        assertEquals("http://www.w3.org/1999/xhtml", child.tag().namespace());
        assertEquals(child, element.child(0));
        assertEquals(2, element.childrenSize());
    }

    @Test
    public void testPrependElementWithInvalidTagName() {
        assertThrows(IllegalArgumentException.class, () -> {
            element.prependElement("", "http://www.w3.org/1999/xhtml");
        });
    }

    @Test
    public void testPrependElementWithNullTagName() {
        assertThrows(IllegalArgumentException.class, () -> {
            element.prependElement(null, "http://www.w3.org/1999/xhtml");
        });
    }

    @Test
    public void testPrependElementWithNullNamespace() {
        Element child = element.prependElement("span", null);
        assertEquals("span", child.tagName());
        assertEquals("", child.tag().namespace());
        assertEquals(child, element.child(0));
    }
}
