
package org.jsoup.parser;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Parser_parseFragmentTest {

    private Parser parser;

    @BeforeEach
    public void setUp() {
        parser = new Parser(new HtmlTreeBuilder());
    }

    @Test
    public void testParseFragmentWithValidHtml() {
        String fragmentHtml = "<div>Test</div>";
        Element context = new Element("body");
        String baseUri = "http://example.com";
        ParseErrorList errorList = ParseErrorList.tracking(10);

        List<Node> nodes = Parser.parseFragment(fragmentHtml, context, baseUri, errorList);

        assertEquals(1, nodes.size());
        assertTrue(nodes.get(0) instanceof Element);
        assertEquals("div", ((Element) nodes.get(0)).tagName());
    }

    @Test
    public void testParseFragmentWithInvalidHtml() {
        String fragmentHtml = "<div><p>Test</div>";
        Element context = new Element("body");
        String baseUri = "http://example.com";
        ParseErrorList errorList = ParseErrorList.tracking(10);

        List<Node> nodes = Parser.parseFragment(fragmentHtml, context, baseUri, errorList);

        assertEquals(1, nodes.size());
        assertTrue(nodes.get(0) instanceof Element);
        assertEquals("div", ((Element) nodes.get(0)).tagName());
        assertEquals(1, errorList.size());
    }

    @Test
    public void testParseFragmentWithEmptyHtml() {
        String fragmentHtml = "";
        Element context = new Element("body");
        String baseUri = "http://example.com";
        ParseErrorList errorList = ParseErrorList.tracking(10);

        List<Node> nodes = Parser.parseFragment(fragmentHtml, context, baseUri, errorList);

        assertEquals(0, nodes.size());
        assertEquals(0, errorList.size());
    }

    @Test
    public void testParseFragmentWithNoContext() {
        String fragmentHtml = "<div>Test</div>";
        Element context = null;
        String baseUri = "http://example.com";
        ParseErrorList errorList = ParseErrorList.tracking(10);

        List<Node> nodes = Parser.parseFragment(fragmentHtml, context, baseUri, errorList);

        assertEquals(1, nodes.size());
        assertTrue(nodes.get(0) instanceof Element);
        assertEquals("div", ((Element) nodes.get(0)).tagName());
    }

    @Test
    public void testParseFragmentWithNoBaseUri() {
        String fragmentHtml = "<div>Test</div>";
        Element context = new Element("body");
        String baseUri = null;
        ParseErrorList errorList = ParseErrorList.tracking(10);

        List<Node> nodes = Parser.parseFragment(fragmentHtml, context, baseUri, errorList);

        assertEquals(1, nodes.size());
        assertTrue(nodes.get(0) instanceof Element);
        assertEquals("div", ((Element) nodes.get(0)).tagName());
    }
}
