
package org.jsoup.nodes;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Node;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class NodeIterator_nextTest {
    private String html;

    @BeforeEach
    public void setUp() {
        html = "<html><head></head><body><div id=\"1\"><p>One</p><p>Two</p></div><div id=\"2\"><p>Three</p><p>Four</p></div></body></html>";
    }

    @Test
    public void testNextWithValidNode() {
        Document doc = Jsoup.parse(html);
        NodeIterator<Node> it = NodeIterator.from(doc);
        assertNotNull(it.next());
    }

    @Test
    public void testNextThrowsNoSuchElementException() {
        Document doc = Jsoup.parse(html);
        NodeIterator<Node> it = NodeIterator.from(doc);
        while (it.hasNext()) {
            it.next();
        }
        assertThrows(NoSuchElementException.class, () -> it.next());
    }

    @Test
    public void testNextUpdatesStateCorrectly() {
        Document doc = Jsoup.parse(html);
        NodeIterator<Node> it = NodeIterator.from(doc);
        Node firstNode = it.next();
        Node secondNode = it.next();
        assertNotEquals(firstNode, secondNode);
        assertNotEquals(firstNode.parent(), secondNode.parent());
    }

    @Test
    public void testNextWithRestart() {
        Document doc = Jsoup.parse(html);
        NodeIterator<Node> it = NodeIterator.from(doc);
        Node firstNode = it.next();
        it.restart(doc.body());
        Node restartedNode = it.next();
        assertNotEquals(firstNode, restartedNode);
    }

    private void assertIterates(NodeIterator<Node> it, String expected) {
        StringBuilder actual = new StringBuilder();
        while (it.hasNext()) {
            Node node = it.next();
            actual.append(node.nodeName()).append(";");
        }
        assertEquals(expected, actual.toString());
    }
}
