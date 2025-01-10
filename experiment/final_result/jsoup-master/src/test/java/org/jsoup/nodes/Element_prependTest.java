
package org.jsoup.nodes;

import org.jsoup.Jsoup;
import org.jsoup.parser.Parser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Element_prependTest {
    private Element element;

    @BeforeEach
    public void setUp() {
        element = new Element("div");
    }

    @Test
    public void testPrependValidHtml() {
        String html = "<p>Hello</p>";
        Element result = element.prepend(html);

        assertEquals(element, result);
        assertEquals(1, element.childNodeSize());
        assertEquals("p", element.child(0).nodeName());
        assertEquals("Hello", element.child(0).childNode(0).toString());
    }

    @Test
    public void testPrependNullHtml() {
        assertThrows(IllegalArgumentException.class, () -> {
            element.prepend(null);
        });
    }
}
