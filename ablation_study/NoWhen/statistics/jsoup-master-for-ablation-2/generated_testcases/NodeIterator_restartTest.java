
package org.jsoup.nodes;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class NodeIterator_restartTest {

    @Test
    void testRestartWithMatchingType() {
        Node mockNode = mock(Node.class);
        Node mockParent = mock(Node.class);
        when(mockNode.parent()).thenReturn(mockParent);

        NodeIterator<Node> iterator = new NodeIterator<>(mockNode, Node.class);
        iterator.restart(mockNode);

        assertEquals(mockNode, iterator.next());
    }

    @Test
    void testRestartWithNonMatchingType() {
        Node mockNode = mock(Node.class);
        Node mockParent = mock(Node.class);
        when(mockNode.parent()).thenReturn(mockParent);

        NodeIterator<Element> iterator = new NodeIterator<>(mockNode, Element.class);
        iterator.restart(mockNode);

        assertNull(iterator.next());
    }
}
