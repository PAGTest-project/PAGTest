
package org.jsoup.parser;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Parser_parseBodyFragmentTest {

    @Test
    public void testParseBodyFragment() {
        String bodyHtml = "<div>Test</div>";
        String baseUri = "http://example.com/";

        Document doc = Parser.parseBodyFragment(bodyHtml, baseUri);
        Element body = doc.body();

        assertEquals(1, body.childNodeSize());
        Node childNode = body.childNode(0);
        assertEquals("div", childNode.nodeName());
        assertEquals("Test", childNode.childNode(0).toString());
    }

    @Test
    public void testParseBodyFragmentWithEmptyBody() {
        String bodyHtml = "";
        String baseUri = "http://example.com/";

        Document doc = Parser.parseBodyFragment(bodyHtml, baseUri);
        Element body = doc.body();

        assertEquals(0, body.childNodeSize());
    }

    @Test
    public void testParseBodyFragmentWithMultipleNodes() {
        String bodyHtml = "<div>Test1</div><p>Test2</p>";
        String baseUri = "http://example.com/";

        Document doc = Parser.parseBodyFragment(bodyHtml, baseUri);
        Element body = doc.body();

        assertEquals(2, body.childNodeSize());
        Node firstChild = body.childNode(0);
        Node secondChild = body.childNode(1);
        assertEquals("div", firstChild.nodeName());
        assertEquals("p", secondChild.nodeName());
        assertEquals("Test1", firstChild.childNode(0).toString());
        assertEquals("Test2", secondChild.childNode(0).toString());
    }
}
