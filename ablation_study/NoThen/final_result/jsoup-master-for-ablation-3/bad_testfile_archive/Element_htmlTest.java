
package org.jsoup.nodes;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Element_htmlTest {
    private Element element;

    @BeforeEach
    public void setUp() {
        element = new Element("div");
    }

    @Test
    public void testHtmlWithPrettyPrint() {
        element.append("<p>Test</p>");
        String html = element.html();
        assertEquals("<p>Test</p>", html);
    }

    @Test
    public void testHtmlWithoutPrettyPrint() {
        element.append("<p>Test</p>");
        element.outputSettings().prettyPrint(false);
        String html = element.html();
        assertEquals("<p>Test</p>", html);
    }

    @Test
    public void testHtmlWithEmptyElement() {
        String html = element.html();
        assertEquals("", html);
    }

    @Test
    public void testHtmlWithMultipleChildren() {
        element.append("<p>First</p>");
        element.append("<p>Second</p>");
        String html = element.html();
        assertEquals("<p>First</p><p>Second</p>", html);
    }

    @Test
    public void testHtmlWithWhitespace() {
        element.append(" <p>Test</p> ");
        String html = element.html();
        assertEquals("<p>Test</p>", html.trim());
    }
}
