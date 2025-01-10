
package org.jsoup.parser;

import org.jsoup.nodes.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Parser_setTreeBuilderTest {
    private Parser parser;

    @BeforeEach
    public void setUp() {
        parser = new Parser(new HtmlTreeBuilder());
    }

    @Test
    public void testSetTreeBuilder() {
        TreeBuilder newTreeBuilder = new XmlTreeBuilder();
        parser.setTreeBuilder(newTreeBuilder);
        assertEquals(newTreeBuilder, parser.getTreeBuilder());
        assertEquals(parser, newTreeBuilder.parser);
    }

    @Test
    public void testSetTreeBuilderAndParseInput() {
        TreeBuilder newTreeBuilder = new XmlTreeBuilder();
        parser.setTreeBuilder(newTreeBuilder);

        String html = "<html><body><p>Test</p></body></html>";
        Document doc = parser.parseInput(html, "");

        assertNotNull(doc);
        assertEquals("Test", doc.body().text());
    }
}
