
package org.jsoup.parser;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class Parser_setTreeBuilderTest {
    private Parser parser;

    @BeforeEach
    public void setUp() {
        parser = new Parser(new HtmlTreeBuilder());
    }

    @Test
    public void testSetTreeBuilder() {
        TreeBuilder newTreeBuilder = new XmlTreeBuilder();
        Parser updatedParser = parser.setTreeBuilder(newTreeBuilder);

        assertEquals(newTreeBuilder, updatedParser.getTreeBuilder());
        assertEquals(parser, newTreeBuilder.parser);
    }

    @Test
    public void testSetTreeBuilderAndParseInput() {
        TreeBuilder newTreeBuilder = new XmlTreeBuilder();
        parser.setTreeBuilder(newTreeBuilder);

        String html = "<root><child>text</child></root>";
        Document doc = parser.parseInput(html, "");

        assertNotNull(doc);
        assertEquals("root", doc.child(0).nodeName());
    }

    @Test
    public void testSetTreeBuilderAndParseFragmentInput() {
        TreeBuilder newTreeBuilder = new XmlTreeBuilder();
        parser.setTreeBuilder(newTreeBuilder);

        String fragment = "<child>text</child>";
        Element context = new Element("root");
        String baseUri = "";

        List<Node> nodes = parser.parseFragmentInput(fragment, context, baseUri);

        assertNotNull(nodes);
        assertEquals(1, nodes.size());
        assertEquals("child", nodes.get(0).nodeName());
    }
}
