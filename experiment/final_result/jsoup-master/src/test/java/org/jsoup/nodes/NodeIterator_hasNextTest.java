
package org.jsoup.nodes;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Node;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NodeIterator_hasNextTest {
    private Node rootNode;
    private NodeIterator<Node> nodeIterator;

    @BeforeEach
    public void setUp() {
        String html = "<div id=out1><div id=1><p>One<p>Two</div><div id=2><p>Three<p>Four</div></div><div id=out2>Out2";
        Document doc = Jsoup.parse(html);
        rootNode = doc;
        nodeIterator = new NodeIterator<>(rootNode, Node.class);
    }

    @Test
    public void testHasNextWithNextNode() {
        assertTrue(nodeIterator.hasNext());
    }

    @Test
    public void testHasNextWithoutNextNode() {
        // Consume all nodes to ensure no next node
        while (nodeIterator.hasNext()) {
            nodeIterator.next();
        }
        assertFalse(nodeIterator.hasNext());
    }
}
