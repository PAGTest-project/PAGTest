
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
        assertEquals("Test", element.child(0).text());
    }

    @Test
    public void testPrependNullHtml() {
        assertThrows(IllegalArgumentException.class, () -> {
            element.prepend(null);
        });
    }

    @Test
    public void testPrependEmptyHtml() {
        String html = "";
        element.prepend(html);
        assertEquals(0, element.childNodeSize());
    }

    @Test
    public void testPrependMultipleElements() {
        String html = "<p>First</p><p>Second</p>";
        element.prepend(html);
        assertEquals(2, element.childNodeSize());
        assertEquals("First", element.child(0).text());
        assertEquals("Second", element.child(1).text());
    }

    @Test
    public void testPrependWithExistingChildren() {
        element.append("<p>Existing</p>");
        String html = "<p>New</p>";
        element.prepend(html);
        assertEquals(2, element.childNodeSize());
        assertEquals("New", element.child(0).text());
        assertEquals("Existing", element.child(1).text());
    }
}
