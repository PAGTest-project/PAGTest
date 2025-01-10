
package org.jsoup.parser;

import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Parser_parseFragmentTest {

    private Parser parser;

    @BeforeEach
    public void setUp() {
        parser = new Parser(new HtmlTreeBuilder());
    }

    @Test
    public void testParseFragment() {
        String fragmentHtml = "<div>Test</div>";
        Element context = new Element("body");
        String baseUri = "http://example.com/";

        List<Node> nodes = Parser.parseFragment(fragmentHtml, context, baseUri);

        assertEquals(1, nodes.size());
        assertEquals("div", nodes.get(0).nodeName());
        assertEquals("Test", nodes.get(0).childNode(0).outerHtml().trim());
    }

    @Test
    public void testParseFragmentWithEmptyString() {
        String fragmentHtml = "";
        Element context = new Element("body");
        String baseUri = "http://example.com/";

        List<Node> nodes = Parser.parseFragment(fragmentHtml, context, baseUri);

        assertEquals(0, nodes.size());
    }
}
