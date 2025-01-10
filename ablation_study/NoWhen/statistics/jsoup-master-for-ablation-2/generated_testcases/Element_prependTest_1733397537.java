
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
        assertEquals("<p>Test</p>", element.html());
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
        assertEquals("", element.html());
    }

    @Test
    public void testPrependMultipleElements() {
        String html = "<p>First</p><p>Second</p>";
        element.prepend(html);
        assertEquals("<p>First</p><p>Second</p>", element.html());
    }

    @Test
    public void testPrependWithExistingContent() {
        element.append("<p>Existing</p>");
        element.prepend("<p>New</p>");
        assertEquals("<p>New</p><p>Existing</p>", element.html());
    }

    @Test
    public void testPrependTextContent() {
        String html = "Text content";
        element.prepend(html);
        assertEquals("Text content", element.text());
    }

    @Test
    public void testPrependWithNestedElements() {
        String html = "<div><p>Nested</p></div>";
        element.prepend(html);
        assertEquals("<div><p>Nested</p></div>", element.html());
    }

    @Test
    public void testPrependWithAttributes() {
        String html = "<p class=\"test\">Test</p>";
        element.prepend(html);
        assertEquals("<p class=\"test\">Test</p>", element.html());
    }

    @Test
    public void testPrependWithSpecialCharacters() {
        String html = "<p>&lt;Test&gt;</p>";
        element.prepend(html);
        assertEquals("<p>&lt;Test&gt;</p>", element.html());
    }

    @Test
    public void testPrependWithScriptTag() {
        String html = "<script>alert('Test');</script>";
        element.prepend(html);
        assertEquals("<script>alert('Test');</script>", element.html());
    }

    @Test
    public void testPrependWithStyleTag() {
        String html = "<style>body { color: red; }</style>";
        element.prepend(html);
        assertEquals("<style>body { color: red; }</style>", element.html());
    }
}
