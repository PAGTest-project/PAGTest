
package org.apache.commons.collections4;

import org.apache.commons.collections4.iterators.ArrayIterator;
import org.apache.commons.collections4.iterators.EnumerationIterator;
import org.apache.commons.collections4.iterators.NodeListIterator;
import org.apache.commons.collections4.iterators.ObjectArrayIterator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.UserDataHandler;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class IteratorUtils_getIteratorTest {

    private List<String> list;
    private NodeList nodeList;
    private Node node;
    private Dictionary<String, String> dictionary;
    private Object[] array;
    private Enumeration<String> enumeration;
    private Map<String, String> map;

    @BeforeEach
    public void setUp() {
        list = new ArrayList<>();
        list.add("One");
        list.add("Two");
        list.add("Three");

        nodeList = new NodeList() {
            @Override
            public Node item(int index) {
                return null;
            }

            @Override
            public int getLength() {
                return 0;
            }
        };

        node = new Node() {
            @Override
            public String getNodeName() {
                return null;
            }

            @Override
            public String getNodeValue() {
                return null;
            }

            @Override
            public void setNodeValue(String nodeValue) {

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
            public void setPrefix(String prefix) {

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
            public Object getUserData(String key) {
                return null;
            }

            @Override
            public Object setUserData(String key, Object data, UserDataHandler handler) {
                return null;
            }
        };

        dictionary = new Hashtable<>();
        dictionary.put("key1", "value1");
        dictionary.put("key2", "value2");

        array = new Object[]{"One", "Two", "Three"};

        enumeration = new Enumeration<String>() {
            private final Iterator<String> it = list.iterator();

            @Override
            public boolean hasMoreElements() {
                return it.hasNext();
            }

            @Override
            public String nextElement() {
                return it.next();
            }
        };

        map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");
    }

    @Test
    public void testGetIteratorFromList() {
        Iterator<?> iterator = IteratorUtils.getIterator(list);
        assertTrue(iterator.hasNext());
        assertEquals("One", iterator.next());
    }

    @Test
    public void testGetIteratorFromNodeList() {
        Iterator<?> iterator = IteratorUtils.getIterator(nodeList);
        assertFalse(iterator.hasNext());
    }

    @Test
    public void testGetIteratorFromNode() {
        Iterator<?> iterator = IteratorUtils.getIterator(node);
        assertFalse(iterator.hasNext());
    }

    @Test
    public void testGetIteratorFromDictionary() {
        Iterator<?> iterator = IteratorUtils.getIterator(dictionary);
        assertTrue(iterator.hasNext());
        assertEquals("value1", iterator.next());
    }

    @Test
    public void testGetIteratorFromArray() {
        Iterator<?> iterator = IteratorUtils.getIterator(array);
        assertTrue(iterator.hasNext());
        assertEquals("One", iterator.next());
    }

    @Test
    public void testGetIteratorFromEnumeration() {
        Iterator<?> iterator = IteratorUtils.getIterator(enumeration);
        assertTrue(iterator.hasNext());
        assertEquals("One", iterator.next());
    }

    @Test
    public void testGetIteratorFromMap() {
        Iterator<?> iterator = IteratorUtils.getIterator(map);
        assertTrue(iterator.hasNext());
        assertEquals("value1", iterator.next());
    }

    @Test
    public void testGetIteratorFromNull() {
        Iterator<?> iterator = IteratorUtils.getIterator(null);
        assertFalse(iterator.hasNext());
    }
}
