
package org.jsoup.select;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Elements_traverseTest {
    private Elements elements;

    @BeforeEach
    public void setUp() {
        Document doc = Jsoup.parse("<div><p>One</p><p>Two</p></div>");
        elements = new Elements(doc.select("p"));
    }

    @Test
    public void testTraverse() {
        final int[] count = {0};
        NodeVisitor nodeVisitor = new NodeVisitor() {
            @Override
            public void head(Node node, int depth) {
                if (node instanceof Element) {
                    count[0]++;
                }
            }

            @Override
            public void tail(Node node, int depth) {
                // Do nothing
            }
        };

        elements.traverse(nodeVisitor);
        assertEquals(2, count[0]);
    }
}
