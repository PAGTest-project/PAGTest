
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
        doc = Jsoup.parse("<div><p>One</p></div>");
    }

    @Test
    public void testFilterStop() {
        NodeFilter filter = new NodeFilter() {
            @Override
            public FilterResult head(Node node, int depth) {
                return FilterResult.STOP;
            }

            @Override
            public FilterResult tail(Node node, int depth) {
                return FilterResult.CONTINUE;
            }
        };
        FilterResult result = NodeTraversor.filter(filter, doc);
        assertEquals(FilterResult.STOP, result);
    }

    @Test
    public void testFilterContinue() {
        NodeFilter filter = new NodeFilter() {
            @Override
            public FilterResult head(Node node, int depth) {
                return FilterResult.CONTINUE;
            }

            @Override
            public FilterResult tail(Node node, int depth) {
                return FilterResult.CONTINUE;
            }
        };
        FilterResult result = NodeTraversor.filter(filter, doc);
        assertEquals(FilterResult.CONTINUE, result);
    }

    @Test
    public void testFilterRemove() {
        NodeFilter filter = new NodeFilter() {
            @Override
            public FilterResult head(Node node, int depth) {
                return FilterResult.REMOVE;
            }

            @Override
            public FilterResult tail(Node node, int depth) {
                return FilterResult.CONTINUE;
            }
        };
        FilterResult result = NodeTraversor.filter(filter, doc);
        assertEquals(FilterResult.CONTINUE, result);
        assertEquals(0, doc.childNodeSize());
    }

    @Test
    public void testFilterSkipChildren() {
        NodeFilter filter = new NodeFilter() {
            @Override
            public FilterResult head(Node node, int depth) {
                return FilterResult.SKIP_CHILDREN;
            }

            @Override
            public FilterResult tail(Node node, int depth) {
                return FilterResult.CONTINUE;
            }
        };
        FilterResult result = NodeTraversor.filter(filter, doc);
        assertEquals(FilterResult.CONTINUE, result);
        assertEquals(1, doc.childNodeSize());
    }
}
