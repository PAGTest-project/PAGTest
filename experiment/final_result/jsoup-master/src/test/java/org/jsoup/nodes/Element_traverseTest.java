
package org.jsoup.nodes;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.NodeVisitor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class Element_traverseTest {

    private Element element;

    @BeforeEach
    public void setUp() {
        String html = "<body><div><p>One</p></div></body>";
        Document doc = Jsoup.parse(html);
        element = doc.selectFirst("div");
        assertNotNull(element);
    }

    @Test
    public void testTraverse() {
        NodeVisitorMock visitor = new NodeVisitorMock();
        Element result = element.traverse(visitor);
        assertNotNull(result);
        assertEquals(element, result);
    }

    private static class NodeVisitorMock implements NodeVisitor {
        @Override
        public void head(Node node, int depth) {
            // Mock implementation
        }

        @Override
        public void tail(Node node, int depth) {
            // Mock implementation
        }
    }
}
