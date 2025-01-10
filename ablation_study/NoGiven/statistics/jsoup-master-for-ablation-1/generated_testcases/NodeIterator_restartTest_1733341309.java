
package org.jsoup.nodes;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Node;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class NodeIterator_restartTest {
    private String html;

    @BeforeEach
    public void setUp() {
        html = "<html><head></head><body><div id='1'><p>One</p><p>Two</p></div><div id='2'><p>Three</p><p>Four</p></div></body></html>";
    }

    @Test
    public void testRestartWithValidNode() {
        Document doc = Jsoup.parse(html);
        NodeIterator<Node> it = new NodeIterator<>(doc, Node.class);
        it.restart(doc.body().child(0)); // Restart with the first div
        assertTrue(it.hasNext());
        assertEquals("div", it.next().nodeName());
    }

    @Test
    public void testRestartWithInvalidNode() {
        Document doc = Jsoup.parse(html);
        NodeIterator<Element> it = new NodeIterator<>(doc, Element.class);
        it.restart(doc.body().child(0).child(0)); // Restart with a p node, which is not an Element
        assertFalse(it.hasNext());
    }

    @Test
    public void testRestartWithNullNode() {
        Document doc = Jsoup.parse(html);
        NodeIterator<Node> it = new NodeIterator<>(doc, Node.class);
        assertThrows(NullPointerException.class, () -> it.restart(null));
    }

    @Test
    public void testRestartAndNext() {
        Document doc = Jsoup.parse(html);
        NodeIterator<Node> it = new NodeIterator<>(doc, Node.class);
        it.restart(doc.body().child(0)); // Restart with the first div
        assertTrue(it.hasNext());
        assertEquals("div", it.next().nodeName());
        assertTrue(it.hasNext());
        assertEquals("p", it.next().nodeName());
    }

    @Test
    public void testRestartAndHasNext() {
        Document doc = Jsoup.parse(html);
        NodeIterator<Node> it = new NodeIterator<>(doc, Node.class);
        it.restart(doc.body().child(0)); // Restart with the first div
        assertTrue(it.hasNext());
        it.next(); // Move to the first child
        assertTrue(it.hasNext());
        it.next(); // Move to the second child
        assertFalse(it.hasNext());
    }

    @Test
    public void testRestartAndNextThrowsNoSuchElementException() {
        Document doc = Jsoup.parse(html);
        NodeIterator<Node> it = new NodeIterator<>(doc, Node.class);
        it.restart(doc.body().child(0)); // Restart with the first div
        it.next(); // Move to the first child
        it.next(); // Move to the second child
        assertThrows(NoSuchElementException.class, it::next);
    }
}
