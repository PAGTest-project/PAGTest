
package org.jsoup.parser;

import org.jsoup.nodes.Node;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class Parser_parseXmlFragmentTest {

    private Parser parser;

    @BeforeEach
    public void setUp() {
        parser = new Parser(new XmlTreeBuilder());
    }

    @Test
    public void testParseXmlFragment() {
        String fragmentXml = "<root><child>text</child></root>";
        String baseUri = "http://example.com";

        List<Node> nodes = Parser.parseXmlFragment(fragmentXml, baseUri);

        assertNotNull(nodes);
        assertEquals(1, nodes.size());
        assertEquals("root", nodes.get(0).nodeName());
    }
}
