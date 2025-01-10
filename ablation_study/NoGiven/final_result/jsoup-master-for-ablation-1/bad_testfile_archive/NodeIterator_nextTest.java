
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
        html = "<html><head></head><body><div id='1'><p>One</p><p>Two</p></div><div id='2'><p>Three</p><p>Four</p></div></body></html>";
    }

    @Test
    public void testNextWithValidNode() {
        Document doc = Jsoup.parse(html);
        NodeIterator<Node> it = NodeIterator.from(doc);
        assertTrue(it.hasNext());
        assertNotNull(it.next());
    }

    @Test
    public void testNextWithNoSuchElementException() {
        Document doc = Jsoup.parse(html);
        NodeIterator<Node> it = NodeIterator.from(doc);
        while (it.hasNext()) {
            it.next();
        }
        assertThrows(NoSuchElementException.class, it::next);
    }

    @Test
    public void testNextWithRestart() {
        Document doc = Jsoup.parse(html);
        NodeIterator<Node> it = NodeIterator.from(doc);
        it.restart(doc.body().child(0));
        assertTrue(it.hasNext());
        assertNotNull(it.next());
    }

    @Test
    public void testNextWithRemove() {
        Document doc = Jsoup.parse(html);
        NodeIterator<Node> it = NodeIterator.from(doc);
        Node firstNode = it.next();
        it.remove();
        assertNull(firstNode.parent());
    }
}
