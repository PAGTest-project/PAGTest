
package org.jsoup.nodes;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Node;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class NodeIterator_nextTest {

    private NodeIterator<Node> nodeIterator;
    private Document doc;

    @BeforeEach
    public void setUp() {
        String html = "<div id=out1><div id=1><p>One<p>Two</div><div id=2><p>Three<p>Four</div></div><div id=out2>Out2";
        doc = Jsoup.parse(html);
        nodeIterator = new NodeIterator<>(doc, Node.class);
    }

    @Test
    public void testNextWithValidNode() {
        assertTrue(nodeIterator.hasNext());
        Node node = nodeIterator.next();
        assertNotNull(node);
        assertEquals("html", node.nodeName());
    }

    @Test
    public void testNextWithNoSuchElementException() {
        while (nodeIterator.hasNext()) {
            nodeIterator.next();
        }
        assertThrows(NoSuchElementException.class, () -> nodeIterator.next());
    }

    @Test
    public void testRestart() {
        Node firstNode = nodeIterator.next();
        nodeIterator.restart(doc.body());
        Node restartedNode = nodeIterator.next();
        assertNotEquals(firstNode, restartedNode);
        assertEquals("body", restartedNode.nodeName());
    }

    @Test
    public void testRemove() {
        while (nodeIterator.hasNext()) {
            Node node = nodeIterator.next();
            if ("1".equals(node.attr("id"))) {
                nodeIterator.remove();
            }
        }
        assertTrue(doc.select("div#1").isEmpty());
    }
}
