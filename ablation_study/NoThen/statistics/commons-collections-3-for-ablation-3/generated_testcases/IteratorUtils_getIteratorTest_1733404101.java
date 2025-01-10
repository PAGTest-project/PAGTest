
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.UserDataHandler;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class IteratorUtils_getIteratorTest {

    @Test
    public void testGetIterator_nullInput() {
        Iterator<?> iterator = IteratorUtils.getIterator(null);
        assertTrue(!iterator.hasNext());
    }

    @Test
    public void testGetIterator_iteratorInput() {
        Iterator<String> inputIterator = Arrays.asList("a", "b", "c").iterator();
        Iterator<?> iterator = IteratorUtils.getIterator(inputIterator);
        assertTrue(iterator == inputIterator);
    }

    @Test
    public void testGetIterator_iterableInput() {
        Iterable<String> iterable = Arrays.asList("a", "b", "c");
        Iterator<?> iterator = IteratorUtils.getIterator(iterable);
        assertTrue(iterator.hasNext());
    }

    @Test
    public void testGetIterator_objectArrayInput() {
        Object[] array = {"a", "b", "c"};
        Iterator<?> iterator = IteratorUtils.getIterator(array);
        assertTrue(iterator.hasNext());
    }

    @Test
    public void testGetIterator_enumerationInput() {
        Vector<String> vector = new Vector<>(Arrays.asList("a", "b", "c"));
        Enumeration<String> enumeration = vector.elements();
        Iterator<?> iterator = IteratorUtils.getIterator(enumeration);
        assertTrue(iterator.hasNext());
    }

    @Test
    public void testGetIterator_mapInput() {
        Map<String, String> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        Iterator<?> iterator = IteratorUtils.getIterator(map);
        assertTrue(iterator.hasNext());
    }

    @Test
    public void testGetIterator_nodeListInput() {
        NodeList nodeList = new NodeList() {
            @Override
            public Node item(int index) {
                return null;
            }

            @Override
            public int getLength() {
                return 0;
            }
        };
        Iterator<?> iterator = IteratorUtils.getIterator(nodeList);
        assertTrue(iterator.hasNext());
    }

    @Test
    public void testGetIterator_nodeInput() {
        Node node = new Node() {
            @Override
            public short getNodeType() {
                return 0;
            }

            @Override
            public String getNodeName() {
                return null;
            }

            @Override
            public String getNodeValue() throws DOMException {
                return null;
            }

            @Override
            public void setNodeValue(String nodeValue) throws DOMException {

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
            public Node insertBefore(Node newChild, Node refChild) throws DOMException {
                return null;
            }

            @Override
            public Node replaceChild(Node newChild, Node oldChild) throws DOMException {
                return null;
            }

            @Override
            public Node removeChild(Node oldChild) throws DOMException {
                return null;
            }

            @Override
            public Node appendChild(Node newChild) throws DOMException {
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
            public void normalize() {

            }

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
            public void setPrefix(String prefix) throws DOMException {

            }

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
            public short compareDocumentPosition(Node other) throws DOMException {
                return 0;
            }

            @Override
            public String getTextContent() throws DOMException {
                return null;
            }

            @Override
            public void setTextContent(String textContent) throws DOMException {

            }

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
        };
        Iterator<?> iterator = IteratorUtils.getIterator(node);
        assertTrue(iterator.hasNext());
    }

    @Test
    public void testGetIterator_dictionaryInput() {
        Dictionary<String, String> dictionary = new Hashtable<>();
        dictionary.put("key1", "value1");
        dictionary.put("key2", "value2");
        Iterator<?> iterator = IteratorUtils.getIterator(dictionary);
        assertTrue(iterator.hasNext());
    }

    @Test
    public void testGetIterator_arrayInput() {
        int[] array = {1, 2, 3};
        Iterator<?> iterator = IteratorUtils.getIterator(array);
        assertTrue(iterator.hasNext());
    }

    @Test
    public void testGetIterator_objectWithIteratorMethodInput() {
        Object obj = new Object() {
            public Iterator<String> iterator() {
                return Arrays.asList("a", "b", "c").iterator();
            }
        };
        Iterator<?> iterator = IteratorUtils.getIterator(obj);
        assertTrue(iterator.hasNext());
    }

    @Test
    public void testGetIterator_singletonIteratorInput() {
        Object obj = new Object();
        Iterator<?> iterator = IteratorUtils.getIterator(obj);
        assertTrue(iterator.hasNext());
    }
}
