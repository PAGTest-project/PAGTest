
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
                // Stop after p.
                return ("p".equals(node.nodeName())) ? FilterResult.STOP : FilterResult.CONTINUE;
            }
        }, doc.select("div").first());
        assertEquals("<div><p><#text></#text></p>", accum.toString());
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
        }, doc.select("div").first());
        assertEquals("<div><p><#text></#text></p></div>", accum.toString());
    }

    @Test
    public void testFilterRemove() {
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
                return ("p".equals(node.nodeName())) ? FilterResult.REMOVE : FilterResult.CONTINUE;
            }
        }, doc.select("div").first());
        assertEquals("<div><p><#text></#text></p></div>", accum.toString());
        assertNull(doc.select("div").first().childNode(0));
    }
}
