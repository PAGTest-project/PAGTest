
package org.jsoup.nodes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Element_appendTest {
    private Element element;

    @BeforeEach
    public void setUp() {
        element = new Element("div");
    }

    @Test
    public void testAppendValidHtml() {
        String html = "<p>Test paragraph</p>";
        Element result = element.append(html);
        assertEquals(element, result);
        assertEquals("<div><p>Test paragraph</p></div>", element.outerHtml());
    }

    @Test
    public void testAppendNullHtml() {
        assertThrows(IllegalArgumentException.class, () -> element.append(null));
    }

    @Test
    public void testAppendEmptyHtml() {
        String html = "";
        Element result = element.append(html);
        assertEquals(element, result);
        assertEquals("<div></div>", element.outerHtml());
    }

    @Test
    public void testAppendMultipleNodes() {
        String html = "<p>First</p><p>Second</p>";
        Element result = element.append(html);
        assertEquals(element, result);
        assertEquals("<div><p>First</p><p>Second</p></div>", element.outerHtml());
    }

    @Test
    public void testAppendWithExistingChildren() {
        element.append("<p>Existing</p>");
        String html = "<p>New</p>";
        Element result = element.append(html);
        assertEquals(element, result);
        assertEquals("<div><p>Existing</p><p>New</p></div>", element.outerHtml());
    }
}
