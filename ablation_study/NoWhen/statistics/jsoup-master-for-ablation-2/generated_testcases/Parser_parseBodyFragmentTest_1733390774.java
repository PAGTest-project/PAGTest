
package org.jsoup.parser;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Parser_parseBodyFragmentTest {

    @Test
    public void testParseBodyFragment() {
        String bodyHtml = "<div>Test Content</div>";
        String baseUri = "http://example.com";

        Document doc = Parser.parseBodyFragment(bodyHtml, baseUri);
        Element body = doc.body();

        assertEquals(1, body.childNodeSize());
        Node childNode = body.childNode(0);
        assertTrue(childNode instanceof Element);
        assertEquals("div", ((Element) childNode).tagName());
        assertEquals("<div>Test Content</div>", childNode.outerHtml());
    }

    @Test
    public void testParseBodyFragmentWithEmptyBody() {
        String bodyHtml = "";
        String baseUri = "http://example.com";

        Document doc = Parser.parseBodyFragment(bodyHtml, baseUri);
        Element body = doc.body();

        assertEquals(0, body.childNodeSize());
    }

    @Test
    public void testParseBodyFragmentWithMultipleNodes() {
        String bodyHtml = "<div>First</div><p>Second</p>";
        String baseUri = "http://example.com";

        Document doc = Parser.parseBodyFragment(bodyHtml, baseUri);
        Element body = doc.body();

        assertEquals(2, body.childNodeSize());
        Node firstChild = body.childNode(0);
        Node secondChild = body.childNode(1);
        assertTrue(firstChild instanceof Element);
        assertTrue(secondChild instanceof Element);
        assertEquals("div", ((Element) firstChild).tagName());
        assertEquals("p", ((Element) secondChild).tagName());
        assertEquals("<div>First</div>", firstChild.outerHtml());
        assertEquals("<p>Second</p>", secondChild.outerHtml());
    }
}
