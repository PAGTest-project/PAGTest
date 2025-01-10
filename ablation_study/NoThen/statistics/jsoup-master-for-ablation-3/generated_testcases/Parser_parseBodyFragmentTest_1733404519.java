
package org.jsoup.parser;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class Parser_parseBodyFragmentTest {

    @Test
    public void testParseBodyFragment() {
        // Given
        String bodyHtml = "<div>Test</div>";
        String baseUri = "http://example.com";

        // When
        Document doc = Parser.parseBodyFragment(bodyHtml, baseUri);

        // Then
        assertNotNull(doc);
        Element body = doc.body();
        assertNotNull(body);
        List<Node> children = body.childNodes();
        assertEquals(1, children.size());
        Node child = children.get(0);
        assertEquals("div", child.nodeName());
        assertEquals("Test", ((Element) child).text());
    }
}
