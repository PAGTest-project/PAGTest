
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
    private boolean createIteratorWithStandardConstr;

    @BeforeEach
    protected void setUp() throws Exception {
        // Default: use standard constr.
        createIteratorWithStandardConstr = true;

        // create mocked Node Instances and fill Node[] to be used by test cases
        final Node node1 = createMock(Element.class);
        final Node node2 = createMock(Element.class);
        final Node node3 = createMock(Text.class);
        final Node node4 = createMock(Element.class);
        nodes = new Node[] { node1, node2, node3, node4 };

        replay(node1);
        replay(node2);
        replay(node3);
        replay(node4);
    }

    @Test
    public void testNextWithElements() {
        NodeList nodeList = createMock(NodeList.class);
        expect(nodeList.getLength()).andReturn(nodes.length).anyTimes();
        for (int i = 0; i < nodes.length; i++) {
            expect(nodeList.item(i)).andReturn(nodes[i]).anyTimes();
        }
        replay(nodeList);

        nodeListIterator = new NodeListIterator(nodeList);

        for (Node node : nodes) {
            assertEquals(node, nodeListIterator.next());
        }
    }

    @Test
    public void testNextWithNoElements() {
        NodeList nodeList = createMock(NodeList.class);
        expect(nodeList.getLength()).andReturn(0).anyTimes();
        replay(nodeList);

        nodeListIterator = new NodeListIterator(nodeList);

        assertThrows(NoSuchElementException.class, () -> {
            nodeListIterator.next();
        });
    }

    @Test
    public void testNextWithNodeConstructor() {
        Node node = createMock(Element.class);
        NodeList nodeList = createMock(NodeList.class);
        expect(node.getChildNodes()).andReturn(nodeList).anyTimes();
        expect(nodeList.getLength()).andReturn(nodes.length).anyTimes();
        for (int i = 0; i < nodes.length; i++) {
            expect(nodeList.item(i)).andReturn(nodes[i]).anyTimes();
        }
        replay(node);
        replay(nodeList);

        nodeListIterator = new NodeListIterator(node);

        for (Node childNode : nodes) {
            assertEquals(childNode, nodeListIterator.next());
        }
    }

    @Test
    public void testNextWithNodeConstructorNoElements() {
        Node node = createMock(Element.class);
        NodeList nodeList = createMock(NodeList.class);
        expect(node.getChildNodes()).andReturn(nodeList).anyTimes();
        expect(nodeList.getLength()).andReturn(0).anyTimes();
        replay(node);
        replay(nodeList);

        nodeListIterator = new NodeListIterator(node);

        assertThrows(NoSuchElementException.class, () -> {
            nodeListIterator.next();
        });
    }
}
