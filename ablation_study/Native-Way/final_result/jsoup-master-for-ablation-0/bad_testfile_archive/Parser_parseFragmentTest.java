
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
    private HtmlTreeBuilder treeBuilder;

    @BeforeEach
    public void setUp() {
        treeBuilder = new HtmlTreeBuilder();
        parser = new Parser(treeBuilder);
    }

    @Test
    public void testParseFragmentWithValidHtml() {
        String fragmentHtml = "<div>Test</div>";
        Element context = new Element("body");
        String baseUri = "http://example.com";
        ParseErrorList errorList = ParseErrorList.tracking(1);

        List<Node> nodes = Parser.parseFragment(fragmentHtml, context, baseUri, errorList);

        assertEquals(1, nodes.size());
        assertTrue(nodes.get(0) instanceof Element);
        assertEquals("div", ((Element) nodes.get(0)).tagName());
        assertEquals("Test", nodes.get(0).childNode(0).outerHtml());
    }

    @Test
    public void testParseFragmentWithInvalidHtml() {
        String fragmentHtml = "<div><p>Test</div>";
        Element context = new Element("body");
        String baseUri = "http://example.com";
        ParseErrorList errorList = ParseErrorList.tracking(1);

        List<Node> nodes = Parser.parseFragment(fragmentHtml, context, baseUri, errorList);

        assertEquals(1, nodes.size());
        assertTrue(nodes.get(0) instanceof Element);
        assertEquals("div", ((Element) nodes.get(0)).tagName());
        assertEquals("<p>Test</p>", nodes.get(0).childNode(0).outerHtml());
    }

    @Test
    public void testParseFragmentWithEmptyHtml() {
        String fragmentHtml = "";
        Element context = new Element("body");
        String baseUri = "http://example.com";
        ParseErrorList errorList = ParseErrorList.tracking(1);

        List<Node> nodes = Parser.parseFragment(fragmentHtml, context, baseUri, errorList);

        assertEquals(0, nodes.size());
    }

    @Test
    public void testParseFragmentWithNullContext() {
        String fragmentHtml = "<div>Test</div>";
        Element context = null;
        String baseUri = "http://example.com";
        ParseErrorList errorList = ParseErrorList.tracking(1);

        List<Node> nodes = Parser.parseFragment(fragmentHtml, context, baseUri, errorList);

        assertEquals(1, nodes.size());
        assertTrue(nodes.get(0) instanceof Element);
        assertEquals("div", ((Element) nodes.get(0)).tagName());
        assertEquals("Test", nodes.get(0).childNode(0).outerHtml());
    }

    @Test
    public void testParseFragmentWithErrors() {
        String fragmentHtml = "<div><p>Test</div>";
        Element context = new Element("body");
        String baseUri = "http://example.com";
        ParseErrorList errorList = ParseErrorList.tracking(1);

        List<Node> nodes = Parser.parseFragment(fragmentHtml, context, baseUri, errorList);

        assertEquals(1, nodes.size());
        assertTrue(nodes.get(0) instanceof Element);
        assertEquals("div", ((Element) nodes.get(0)).tagName());
        assertEquals("<p>Test</p>", nodes.get(0).childNode(0).outerHtml());
        assertEquals(1, errorList.size());
    }
}
