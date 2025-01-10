
package org.jsoup.nodes;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class NodeIterator_restartTest {

    @Test
    void testRestartWithMatchingType() {
        Node mockNode = mock(Node.class);
        when(mockNode.parent()).thenReturn(mock(Node.class));

        NodeIterator<Node> iterator = new NodeIterator<>(Node.class);
        iterator.restart(mockNode);

        assertNotNull(iterator.next());
    }

    @Test
    void testRestartWithNonMatchingType() {
        Node mockNode = mock(Node.class);
        when(mockNode.parent()).thenReturn(mock(Node.class));

        NodeIterator<Element> iterator = new NodeIterator<>(Element.class);
        iterator.restart(mockNode);

        assertNull(iterator.next());
    }
}
