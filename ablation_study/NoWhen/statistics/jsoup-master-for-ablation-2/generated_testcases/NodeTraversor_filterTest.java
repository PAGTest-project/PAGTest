
package org.jsoup.select;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Node;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class NodeTraversor_filterTest {

    private Document doc;

    @BeforeEach
    public void setUp() {
        doc = Jsoup.parse("<div><p>Hello</p></div><div>There</div>");
    }

    @Test
    public void testFilterStop() {
        Node root = doc.select("div").first();
        FilterResult result = NodeTraversor.filter(new NodeFilter() {
            @Override
            public FilterResult head(Node node, int depth) {
                return FilterResult.STOP;
            }

            @Override
            public FilterResult tail(Node node, int depth) {
                return FilterResult.CONTINUE;
            }
        }, root);
        assertEquals(FilterResult.STOP, result);
    }

    @Test
    public void testFilterContinue() {
        Node root = doc.select("div").first();
        FilterResult result = NodeTraversor.filter(new NodeFilter() {
            @Override
            public FilterResult head(Node node, int depth) {
                return FilterResult.CONTINUE;
            }

            @Override
            public FilterResult tail(Node node, int depth) {
                return FilterResult.CONTINUE;
            }
        }, root);
        assertEquals(FilterResult.CONTINUE, result);
    }

    @Test
    public void testFilterSkipChildren() {
        Node root = doc.select("div").first();
        final StringBuilder accum = new StringBuilder();
        FilterResult result = NodeTraversor.filter(new NodeFilter() {
            @Override
            public FilterResult head(Node node, int depth) {
                accum.append("<").append(node.nodeName()).append(">");
                return ("p".equals(node.nodeName())) ? FilterResult.SKIP_CHILDREN : FilterResult.CONTINUE;
            }

            @Override
            public FilterResult tail(Node node, int depth) {
                accum.append("</").append(node.nodeName()).append(">");
                return FilterResult.CONTINUE;
            }
        }, root);
        assertEquals("<div><p></p></div>", accum.toString());
        assertEquals(FilterResult.CONTINUE, result);
    }

    @Test
    public void testFilterRemove() {
        Node root = doc.select("div").first();
        FilterResult result = NodeTraversor.filter(new NodeFilter() {
            @Override
            public FilterResult head(Node node, int depth) {
                return FilterResult.REMOVE;
            }

            @Override
            public FilterResult tail(Node node, int depth) {
                return FilterResult.CONTINUE;
            }
        }, root);
        assertEquals(0, root.childNodeSize());
        assertEquals(FilterResult.CONTINUE, result);
    }
}
