
package org.jsoup.nodes;

import org.jsoup.Jsoup;
import org.jsoup.parser.Parser;
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
        assertEquals(1, element.childNodeSize());
        assertEquals("Test paragraph", element.text());
    }

    @Test
    public void testAppendNullHtml() {
        assertThrows(IllegalArgumentException.class, () -> {
            element.append(null);
        });
    }

    @Test
    public void testAppendEmptyHtml() {
        String html = "";
        element.append(html);
        assertEquals(0, element.childNodeSize());
    }

    @Test
    public void testAppendMultipleElements() {
        String html = "<p>First</p><p>Second</p>";
        element.append(html);
        assertEquals(2, element.childNodeSize());
        assertEquals("First Second", element.text());
    }

    @Test
    public void testAppendWithBaseUri() {
        String html = "<a href='test.html'>Link</a>";
        element.setBaseUri("http://example.com");
        element.append(html);
        assertEquals(1, element.childNodeSize());
        assertEquals("http://example.com/test.html", element.select("a").first().absUrl("href"));
    }

    @Test
    public void testAppendWithInvalidHtml() {
        String html = "<p>Unclosed tag";
        element.append(html);
        assertEquals(1, element.childNodeSize());
        assertEquals("Unclosed tag", element.text());
    }

    @Test
    public void testAppendAfterEmpty() {
        String html = "<p>Test paragraph</p>";
        element.append(html);
        element.empty();
        element.append(html);
        assertEquals(1, element.childNodeSize());
        assertEquals("Test paragraph", element.text());
    }

    @Test
    public void testAppendWithScriptTag() {
        String html = "<script>alert('test');</script>";
        element.append(html);
        assertEquals(1, element.childNodeSize());
        assertEquals("", element.text()); // Scripts are not rendered as text
    }

    @Test
    public void testAppendWithStyleTag() {
        String html = "<style>body { color: red; }</style>";
        element.append(html);
        assertEquals(1, element.childNodeSize());
        assertEquals("", element.text()); // Styles are not rendered as text
    }

    @Test
    public void testAppendWithComment() {
        String html = "<!-- This is a comment -->";
        element.append(html);
        assertEquals(1, element.childNodeSize());
        assertEquals("", element.text()); // Comments are not rendered as text
    }

    @Test
    public void testAppendWithDoctype() {
        String html = "<!DOCTYPE html>";
        element.append(html);
        assertEquals(0, element.childNodeSize()); // Doctypes are not added as child nodes
    }
}
