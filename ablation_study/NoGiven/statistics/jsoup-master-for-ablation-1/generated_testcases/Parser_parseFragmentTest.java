
package org.jsoup.parser;

import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class Parser_parseFragmentTest {

    @Test
    public void testParseFragment() {
        // Given
        String fragmentHtml = "<div>Test</div>";
        Element context = new Element("body");
        String baseUri = "http://example.com";
        ParseErrorList errorList = ParseErrorList.tracking(1);

        // When
        List<Node> nodes = Parser.parseFragment(fragmentHtml, context, baseUri, errorList);

        // Then
        assertNotNull(nodes);
    }
}
