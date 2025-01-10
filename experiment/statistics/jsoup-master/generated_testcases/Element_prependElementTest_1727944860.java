
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
        element = new Element(Tag.valueOf("div", Parser.NamespaceHtml), "");
    }

    @Test
    public void testPrependElement() {
        Element child = element.prependElement("span", Parser.NamespaceHtml);
        assertEquals("span", child.tagName());
        assertEquals(1, element.childNodeSize());
        assertEquals(child, element.child(0));
    }
}
