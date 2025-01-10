
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
    private Document doc;
    private NodeIterator<Node> it;

    @BeforeEach
    void setUp() {
        html = "<html><head></head><body><div id='1'><p>One</p><p>Two</p></div><div id='2'><p>Three</p><p>Four</p></div></body></html>";
        doc = Jsoup.parse(html);
        it = NodeIterator.from(doc);
    }

    @Test
    void testNextSuccess() {
        assertTrue(it.hasNext());
        assertEquals(doc, it.next());
        assertTrue(it.hasNext());
        assertEquals(doc.childNode(0), it.next());
        assertTrue(it.hasNext());
        assertEquals(doc.childNode(0).childNode(0), it.next());
        assertTrue(it.hasNext());
        assertEquals(doc.childNode(0).childNode(1), it.next());
        assertTrue(it.hasNext());
        assertEquals(doc.childNode(0).childNode(1).childNode(0), it.next());
        assertTrue(it.hasNext());
        assertEquals(doc.childNode(0).childNode(1).childNode(1), it.next());
        assertTrue(it.hasNext());
        assertEquals(doc.childNode(0).childNode(1).childNode(2), it.next());
        assertTrue(it.hasNext());
        assertEquals(doc.childNode(0).childNode(1).childNode(3), it.next());
        assertFalse(it.hasNext());
    }

    @Test
    void testNextThrowsNoSuchElementException() {
        while (it.hasNext()) {
            it.next();
        }
        assertThrows(NoSuchElementException.class, () -> it.next());
    }
}
