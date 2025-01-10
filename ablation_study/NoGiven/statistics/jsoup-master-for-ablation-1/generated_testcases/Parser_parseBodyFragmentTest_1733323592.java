
package org.jsoup.parser;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Parser_parseBodyFragmentTest {

    @Test
    public void testParseBodyFragment() {
        // Given
        String bodyHtml = "<div>Test</div>";
        String baseUri = "http://example.com";

        // When
        Document doc = Parser.parseBodyFragment(bodyHtml, baseUri);

        // Then
        Element body = doc.body();
        assertEquals(1, body.childNodeSize());
        Node child = body.childNode(0);
        assertEquals("div", child.nodeName());
        assertEquals("Test", child.childNode(0).toString());
    }
}
