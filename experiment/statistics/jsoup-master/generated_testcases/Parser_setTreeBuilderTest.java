
package org.jsoup.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Parser_setTreeBuilderTest {
    private Parser parser;
    private TreeBuilder mockTreeBuilder;

    @BeforeEach
    void setUp() {
        mockTreeBuilder = new HtmlTreeBuilder();
        parser = new Parser(mockTreeBuilder);
    }

    @Test
    void testSetTreeBuilder() {
        TreeBuilder newTreeBuilder = new XmlTreeBuilder();
        Parser updatedParser = parser.setTreeBuilder(newTreeBuilder);

        // Verify that the TreeBuilder was updated
        assertSame(newTreeBuilder, updatedParser.getTreeBuilder());
        // Verify that the new TreeBuilder's parser reference was updated
        assertSame(updatedParser, newTreeBuilder.parser);
    }
}
