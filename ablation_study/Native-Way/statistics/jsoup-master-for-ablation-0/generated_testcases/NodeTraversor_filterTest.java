
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
    public void testFilterContinue() {
        final StringBuilder accum = new StringBuilder();
        NodeTraversor.filter(new NodeFilter() {
            @Override
            public FilterResult head(Node node, int depth) {
                accum.append("<").append(node.nodeName()).append(">");
                return FilterResult.CONTINUE;
            }

            @Override
            public FilterResult tail(Node node, int depth) {
                accum.append("</").append(node.nodeName()).append(">");
                return FilterResult.CONTINUE;
            }
        }, doc);
        assertEquals("<#document><html><head></head><body><div><p><#text></#text></p></div><div><#text></#text></div></body></html></#document>", accum.toString());
    }

    @Test
    public void testFilterSkipChildren() {
        final StringBuilder accum = new StringBuilder();
        NodeTraversor.filter(new NodeFilter() {
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
        }, doc);
        assertEquals("<#document><html><head></head><body><div><p></p></div><div><#text></#text></div></body></html></#document>", accum.toString());
    }

    @Test
    public void testFilterStop() {
        final StringBuilder accum = new StringBuilder();
        FilterResult result = NodeTraversor.filter(new NodeFilter() {
            @Override
            public FilterResult head(Node node, int depth) {
                accum.append("<").append(node.nodeName()).append(">");
                return ("p".equals(node.nodeName())) ? FilterResult.STOP : FilterResult.CONTINUE;
            }

            @Override
            public FilterResult tail(Node node, int depth) {
                accum.append("</").append(node.nodeName()).append(">");
                return FilterResult.CONTINUE;
            }
        }, doc);
        assertEquals("<#document><html><head></head><body><div><p>", accum.toString());
        assertEquals(FilterResult.STOP, result);
    }

    @Test
    public void testFilterRemove() {
        final StringBuilder accum = new StringBuilder();
        NodeTraversor.filter(new NodeFilter() {
            @Override
            public FilterResult head(Node node, int depth) {
                accum.append("<").append(node.nodeName()).append(">");
                return ("p".equals(node.nodeName())) ? FilterResult.REMOVE : FilterResult.CONTINUE;
            }

            @Override
            public FilterResult tail(Node node, int depth) {
                accum.append("</").append(node.nodeName()).append(">");
                return FilterResult.CONTINUE;
            }
        }, doc);
        assertEquals("<#document><html><head></head><body><div></div><div><#text></#text></div></body></html></#document>", accum.toString());
    }
}
