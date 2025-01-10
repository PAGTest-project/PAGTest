
package org.jsoup.select;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Node;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NodeTraversor_filterTest {

    private Document doc;

    @BeforeEach
    public void setUp() {
        doc = Jsoup.parse("<div><p>Hello</p></div><div>There</div>");
    }

    @Test
    public void testFilterSkipChildren() {
        final StringBuilder accum = new StringBuilder();
        NodeTraversor.filter(new NodeFilter() {
            @Override
            public FilterResult head(Node node, int depth) {
                accum.append("<").append(node.nodeName()).append(">");
                // OMIT contents of p:
                return ("p".equals(node.nodeName())) ? FilterResult.SKIP_CHILDREN : FilterResult.CONTINUE;
            }

            @Override
            public FilterResult tail(Node node, int depth) {
                accum.append("</").append(node.nodeName()).append(">");
                return FilterResult.CONTINUE;
            }
        }, doc.select("div").first());
        assertEquals("<div><p></p></div>", accum.toString());
    }

    @Test
    public void testFilterStop() {
        final StringBuilder accum = new StringBuilder();
        FilterResult result = NodeTraversor.filter(new NodeFilter() {
            @Override
            public FilterResult head(Node node, int depth) {
                accum.append("<").append(node.nodeName()).append(">");
                // STOP at the first div:
                return ("div".equals(node.nodeName())) ? FilterResult.STOP : FilterResult.CONTINUE;
            }

            @Override
            public FilterResult tail(Node node, int depth) {
                accum.append("</").append(node.nodeName()).append(">");
                return FilterResult.CONTINUE;
            }
        }, doc.select("div").first());
        assertEquals("<div>", accum.toString());
        assertEquals(FilterResult.STOP, result);
    }

    @Test
    public void testFilterRemove() {
        final StringBuilder accum = new StringBuilder();
        NodeTraversor.filter(new NodeFilter() {
            @Override
            public FilterResult head(Node node, int depth) {
                accum.append("<").append(node.nodeName()).append(">");
                // REMOVE the first p:
                return ("p".equals(node.nodeName())) ? FilterResult.REMOVE : FilterResult.CONTINUE;
            }

            @Override
            public FilterResult tail(Node node, int depth) {
                accum.append("</").append(node.nodeName()).append(">");
                return FilterResult.CONTINUE;
            }
        }, doc.select("div").first());
        assertEquals("<div></div>", accum.toString());
    }
}
