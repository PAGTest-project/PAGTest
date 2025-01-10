
package org.apache.commons.collections4.iterators;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

public class NodeListIterator_nextTest {

    private NodeListIterator nodeListIterator;
    private Node[] nodes;

    @BeforeEach
    protected void setUp() throws Exception {
        // Create mocked Node Instances and fill Node[] to be used by test cases
        final Node node1 = createMock(Element.class);
        final Node node2 = createMock(Element.class);
        final Node node3 = createMock(Text.class);
        final Node node4 = createMock(Element.class);
        nodes = new Node[] { node1, node2, node3, node4 };

        replay(node1);
        replay(node2);
        replay(node3);
        replay(node4);

        // Create a mocked NodeList
        NodeList mockedNodeList = createMock(NodeList.class);
        expect(mockedNodeList.getLength()).andReturn(nodes.length).anyTimes();
        for (int i = 0; i < nodes.length; i++) {
            expect(mockedNodeList.item(i)).andReturn(nodes[i]).anyTimes();
        }
        replay(mockedNodeList);

        // Initialize the NodeListIterator with the mocked NodeList
        nodeListIterator = new NodeListIterator(mockedNodeList);
    }

    @Test
    public void testNextWithElements() {
        for (Node node : nodes) {
            assertEquals(node, nodeListIterator.next());
        }
    }

    @Test
    public void testNextWithNoMoreElements() {
        // Consume all elements
        for (int i = 0; i < nodes.length; i++) {
            nodeListIterator.next();
        }

        // Attempt to get the next element when there are none left
        assertThrows(NoSuchElementException.class, () -> {
            nodeListIterator.next();
        });
    }
}
