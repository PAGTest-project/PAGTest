
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
        String baseUri = "http://example.com";

        Document doc = Parser.parseBodyFragment(bodyHtml, baseUri);
        Element body = doc.body();

        assertEquals(1, body.childNodeSize());
        assertEquals("div", body.child(0).nodeName());
        assertEquals("Test", body.text());
    }

    @Test
    public void testParseBodyFragmentWithMultipleNodes() {
        String bodyHtml = "<div>Test1</div><p>Test2</p>";
        String baseUri = "http://example.com";

        Document doc = Parser.parseBodyFragment(bodyHtml, baseUri);
        Element body = doc.body();

        assertEquals(2, body.childNodeSize());
        assertEquals("div", body.child(0).nodeName());
        assertEquals("p", body.child(1).nodeName());
        assertEquals("Test1", body.child(0).text());
        assertEquals("Test2", body.child(1).text());
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
    public void testParseBodyFragmentWithNestedNodes() {
        String bodyHtml = "<div><p>Nested</p></div>";
        String baseUri = "http://example.com";

        Document doc = Parser.parseBodyFragment(bodyHtml, baseUri);
        Element body = doc.body();

        assertEquals(1, body.childNodeSize());
        assertEquals("div", body.child(0).nodeName());
        assertEquals(1, body.child(0).childNodeSize());
        assertEquals("p", body.child(0).child(0).nodeName());
        assertEquals("Nested", body.child(0).child(0).text());
    }

    @Test
    public void testParseBodyFragmentWithSpecialCharacters() {
        String bodyHtml = "<div>&lt;Test&gt;</div>";
        String baseUri = "http://example.com";

        Document doc = Parser.parseBodyFragment(bodyHtml, baseUri);
        Element body = doc.body();

        assertEquals(1, body.childNodeSize());
        assertEquals("div", body.child(0).nodeName());
        assertEquals("<Test>", body.child(0).text());
    }
}
