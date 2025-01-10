
package org.jsoup.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Parser_setTreeBuilderTest {
    private Parser parser;
    private TreeBuilder mockTreeBuilder;

    @BeforeEach
    void setUp() {
        parser = new Parser(new HtmlTreeBuilder());
        mockTreeBuilder = new HtmlTreeBuilder();
    }

    @Test
    void testSetTreeBuilder() {
        Parser updatedParser = parser.setTreeBuilder(mockTreeBuilder);
        assertEquals(mockTreeBuilder, updatedParser.getTreeBuilder());
        assertEquals(parser, mockTreeBuilder.parser);
    }

    @Test
    void testSetTreeBuilderWithNull() {
        Parser updatedParser = parser.setTreeBuilder(null);
        assertNull(updatedParser.getTreeBuilder());
        assertNull(mockTreeBuilder.parser);
    }
}
