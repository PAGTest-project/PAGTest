
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
        element.append(html);
        assertEquals("<div>" + html + "</div>", element.outerHtml());
    }

    @Test
    public void testAppendNullHtml() {
        assertThrows(IllegalArgumentException.class, () -> element.append(null));
    }

    @Test
    public void testAppendEmptyHtml() {
        String html = "";
        element.append(html);
        assertEquals("<div></div>", element.outerHtml());
    }

    @Test
    public void testAppendMultipleTimes() {
        String html1 = "<p>First paragraph</p>";
        String html2 = "<p>Second paragraph</p>";
        element.append(html1);
        element.append(html2);
        assertEquals("<div>" + html1 + html2 + "</div>", element.outerHtml());
    }

    @Test
    public void testAppendWithTextContent() {
        String html = "<p>Test paragraph</p>";
        element.append(html);
        assertEquals("Test paragraph", element.text());
    }
}
