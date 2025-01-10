
package org.jsoup.nodes;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class NodeIterator_restartTest {
    private String html;
    private Document doc;

    @BeforeEach
    public void setUp() {
        html = "<html><head></head><body><div id='1'><p>One</p><p>Two</p></div><div id='2'><p>Three</p><p>Four</p></div></body></html>";
        doc = Jsoup.parse(html);
    }

    @Test
    public void testRestartWithMatchingType() {
        NodeIterator<Element> it = new NodeIterator<>(doc, Element.class);
        it.restart(doc.body().child(0)); // Restart with a div element
        assertTrue(it.hasNext());
        assertEquals("div", it.next().tagName());
    }

    @Test
    public void testRestartWithNonMatchingType() {
        NodeIterator<TextNode> it = new NodeIterator<>(doc, TextNode.class);
        it.restart(doc.body().child(0)); // Restart with a div element
        assertFalse(it.hasNext());
    }

    @Test
    public void testRestartWithRootNode() {
        NodeIterator<Node> it = NodeIterator.from(doc);
        it.restart(doc); // Restart with the root node
        assertTrue(it.hasNext());
        assertEquals("html", it.next().nodeName());
    }
}
