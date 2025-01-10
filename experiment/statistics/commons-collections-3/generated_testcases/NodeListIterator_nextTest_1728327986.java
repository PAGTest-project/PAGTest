
package org.apache.commons.collections4.iterators;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;
import org.w3c.dom.Text;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Document;
import org.w3c.dom.UserDataHandler;
import org.w3c.dom.Attr;
import java.util.NoSuchElementException;

public class NodeListIterator_nextTest {

    private NodeListIterator iterator;
    private NodeList mockNodeList;

    @BeforeEach
    public void setUp() {
        mockNodeList = new NodeList() {
            private Node[] nodes = {
                createMockNode("node1"),
                createMockNode("node2")
            };

            @Override
            public Node item(int index) {
                return nodes[index];
            }

            @Override
            public int getLength() {
                return nodes.length;
            }
        };
        iterator = new NodeListIterator(mockNodeList);
    }

    private Node createMockNode(String name) {
        return new Element() {
            @Override
            public String getNodeName() {
                return name;
            }

            @Override
            public String getNodeValue() {
                return null;
            }

            @Override
            public short getNodeType() {
                return 0;
            }

            @Override
            public Node getParentNode() {
                return null;
            }

            @Override
            public NodeList getChildNodes() {
                return null;
            }

            @Override
            public Node getFirstChild() {
                return null;
            }

            @Override
            public Node getLastChild() {
                return null;
            }

            @Override
            public Node getPreviousSibling() {
                return null;
            }

            @Override
            public Node getNextSibling() {
                return null;
            }

            @Override
            public NamedNodeMap getAttributes() {
                return null;
            }

            @Override
            public Document getOwnerDocument() {
                return null;
            }

            @Override
            public Node insertBefore(Node newChild, Node refChild) {
                return null;
            }

            @Override
            public Node replaceChild(Node newChild, Node oldChild) {
                return null;
            }

            @Override
            public Node removeChild(Node oldChild) {
                return null;
            }

            @Override
            public Node appendChild(Node newChild) {
                return null;
            }

            @Override
            public boolean hasChildNodes() {
                return false;
            }

            @Override
            public Node cloneNode(boolean deep) {
                return null;
            }

            @Override
            public void normalize() {}

            @Override
            public boolean isSupported(String feature, String version) {
                return false;
            }

            @Override
            public String getNamespaceURI() {
                return null;
            }

            @Override
            public String getPrefix() {
                return null;
            }

            @Override
            public void setPrefix(String prefix) {}

            @Override
            public String getLocalName() {
                return null;
            }

            @Override
            public boolean hasAttributes() {
                return false;
            }

            @Override
            public String getBaseURI() {
                return null;
            }

            @Override
            public short compareDocumentPosition(Node other) {
                return 0;
            }

            @Override
            public String getTextContent() {
                return null;
            }

            @Override
            public void setTextContent(String textContent) {}

            @Override
            public boolean isSameNode(Node other) {
                return false;
            }

            @Override
            public String lookupPrefix(String namespaceURI) {
                return null;
            }

            @Override
            public boolean isDefaultNamespace(String namespaceURI) {
                return false;
            }

            @Override
            public String lookupNamespaceURI(String prefix) {
                return null;
            }

            @Override
            public boolean isEqualNode(Node arg) {
                return false;
            }

            @Override
            public Object getFeature(String feature, String version) {
                return null;
            }

            @Override
            public Object setUserData(String key, Object data, UserDataHandler handler) {
                return null;
            }

            @Override
            public Object getUserData(String key) {
                return null;
            }

            @Override
            public void setIdAttributeNode(Attr idAttr, boolean isId) {}
        };
    }

    @Test
    public void testNextWithElements() {
        assertTrue(iterator.hasNext());
        assertEquals("node1", iterator.next().getNodeName());
        assertTrue(iterator.hasNext());
        assertEquals("node2", iterator.next().getNodeName());
        assertFalse(iterator.hasNext());
    }

    @Test
    public void testNextWithNoElements() {
        iterator = new NodeListIterator(new NodeList() {
            @Override
            public Node item(int index) {
                return null;
            }

            @Override
            public int getLength() {
                return 0;
            }
        });
        assertFalse(iterator.hasNext());
        assertThrows(NoSuchElementException.class, () -> iterator.next());
    }
}
