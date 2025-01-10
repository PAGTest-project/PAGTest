
package org.apache.commons.collections4.iterators;

import org.junit.jupiter.api.Test;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class NodeListIterator_nextTest {

    @Test
    void testNextWithElements() {
        NodeList mockNodeList = mock(NodeList.class);
        Node mockNode = mock(Node.class);
        when(mockNodeList.getLength()).thenReturn(1);
        when(mockNodeList.item(0)).thenReturn(mockNode);

        NodeListIterator iterator = new NodeListIterator(mockNodeList);
        assertEquals(mockNode, iterator.next());
    }

    @Test
    void testNextWithoutElements() {
        NodeList mockNodeList = mock(NodeList.class);
        when(mockNodeList.getLength()).thenReturn(0);

        NodeListIterator iterator = new NodeListIterator(mockNodeList);
        assertThrows(NoSuchElementException.class, iterator::next);
    }
}
