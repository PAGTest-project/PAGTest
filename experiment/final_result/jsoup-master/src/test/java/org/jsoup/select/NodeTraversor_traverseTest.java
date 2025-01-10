
package org.jsoup.select;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Node;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class NodeTraversor_traverseTest {

    private Document doc;

    @BeforeEach
    public void setUp() {
        doc = Jsoup.parse("<div><p>Hello</p><p>World</p></div>");
    }

    @Test
    public void testTraverse() {
        final StringBuilder accum = new StringBuilder();
        NodeTraversor.traverse(new NodeVisitor() {
            @Override
            public void head(Node node, int depth) {
                accum.append("<").append(node.nodeName()).append(">");
            }

            @Override
            public void tail(Node node, int depth) {
                accum.append("</").append(node.nodeName()).append(">");
            }
        }, doc.body());

        assertEquals("<body><div><p><#text></#text></p><p><#text></#text></p></div></body>", accum.toString());
    }

    @Test
    public void testTraverseWithNullVisitor() {
        assertThrows(IllegalArgumentException.class, () -> {
            NodeTraversor.traverse((NodeVisitor) null, doc.body());
        });
    }

    @Test
    public void testTraverseWithNullRoot() {
        assertThrows(IllegalArgumentException.class, () -> {
            NodeTraversor.traverse(new NodeVisitor() {
                @Override
                public void head(Node node, int depth) {}

                @Override
                public void tail(Node node, int depth) {}
            }, (Node) null);
        });
    }
}
