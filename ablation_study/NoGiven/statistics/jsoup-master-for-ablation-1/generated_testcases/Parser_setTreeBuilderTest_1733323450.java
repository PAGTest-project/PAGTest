
package org.jsoup.parser;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class Parser_setTreeBuilderTest {
    private Parser parser;
    private TreeBuilder mockTreeBuilder;

    @BeforeEach
    public void setUp() {
        mockTreeBuilder = new HtmlTreeBuilder();
        parser = new Parser(mockTreeBuilder);
    }

    @Test
    public void testSetTreeBuilder() {
        TreeBuilder newTreeBuilder = new XmlTreeBuilder();
        Parser updatedParser = parser.setTreeBuilder(newTreeBuilder);

        assertEquals(newTreeBuilder, updatedParser.getTreeBuilder());
        assertEquals(parser, newTreeBuilder.parser);
    }

    @Test
    public void testParseInputWithNewTreeBuilder() {
        TreeBuilder newTreeBuilder = new XmlTreeBuilder();
        parser.setTreeBuilder(newTreeBuilder);

        String html = "<html><body><p>Test</p></body></html>";
        Document doc = parser.parseInput(html, "");

        assertNotNull(doc);
        assertEquals("Test", doc.body().text());
    }

    @Test
    public void testParseFragmentInputWithNewTreeBuilder() {
        TreeBuilder newTreeBuilder = new XmlTreeBuilder();
        parser.setTreeBuilder(newTreeBuilder);

        String fragment = "<p>Test</p>";
        List<Node> nodes = parser.parseFragmentInput(fragment, null, "");

        assertNotNull(nodes);
        assertEquals(1, nodes.size());
        assertTrue(nodes.get(0) instanceof Element);
        assertEquals("Test", ((Element) nodes.get(0)).text());
    }
}
