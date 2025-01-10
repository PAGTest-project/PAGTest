
package org.jsoup.parser;

import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.Reader;
import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class StreamParser_parseFragmentTest {

    @Test
    public void testParseFragment() {
        // Given
        Reader input = new StringReader("<div></div>");
        Element context = mock(Element.class);
        String baseUri = "http://example.com";
        Parser parser = mock(Parser.class);
        TreeBuilder treeBuilder = mock(TreeBuilder.class);
        Mockito.when(parser.getTreeBuilder()).thenReturn(treeBuilder);

        StreamParser streamParser = new StreamParser(parser);

        // When
        StreamParser result = streamParser.parseFragment(input, context, baseUri);

        // Then
        assertSame(streamParser, result);
        verify(treeBuilder).initialiseParseFragment(context);
    }
}
