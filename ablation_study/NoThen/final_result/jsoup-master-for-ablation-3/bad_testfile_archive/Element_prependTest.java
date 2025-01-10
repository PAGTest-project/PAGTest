
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
        String html = "<p>Test</p>";
        element.prepend(html);
        assertEquals(1, element.childNodeSize());
        assertEquals("p", element.child(0).nodeName());
        assertEquals("Test", element.child(0).childNode(0).outerHtml());
    }

    @Test
    public void testPrependEmptyHtml() {
        String html = "";
        element.prepend(html);
        assertEquals(0, element.childNodeSize());
    }

    @Test
    public void testPrependNullHtml() {
        String html = null;
        assertThrows(IllegalArgumentException.class, () -> element.prepend(html));
    }

    @Test
    public void testPrependWithExistingChildren() {
        element.append("<p>Existing</p>");
        String html = "<p>New</p>";
        element.prepend(html);
        assertEquals(2, element.childNodeSize());
        assertEquals("p", element.child(0).nodeName());
        assertEquals("New", element.child(0).childNode(0).outerHtml());
        assertEquals("p", element.child(1).nodeName());
        assertEquals("Existing", element.child(1).childNode(0).outerHtml());
    }

    @Test
    public void testPrependWithEmptyElement() {
        element.empty();
        String html = "<p>Test</p>";
        element.prepend(html);
        assertEquals(1, element.childNodeSize());
        assertEquals("p", element.child(0).nodeName());
        assertEquals("Test", element.child(0).childNode(0).outerHtml());
    }
}
