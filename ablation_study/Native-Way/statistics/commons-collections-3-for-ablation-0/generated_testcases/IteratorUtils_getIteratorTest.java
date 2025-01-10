
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.DOMException;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Document;
import org.w3c.dom.UserDataHandler;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.apache.commons.collections4.iterators.EmptyIterator;
import org.apache.commons.collections4.iterators.ObjectArrayIterator;
import org.apache.commons.collections4.iterators.EnumerationIterator;
import org.apache.commons.collections4.iterators.NodeListIterator;
import org.apache.commons.collections4.iterators.SingletonIterator;
import org.apache.commons.collections4.iterators.ArrayIterator;

public class IteratorUtils_getIteratorTest {

    @Test
    public void testGetIterator() {
        // Test null input
        assertTrue(IteratorUtils.getIterator(null) instanceof EmptyIterator);

        // Test Iterator input
        Iterator<String> iterator = Arrays.asList("a", "b").iterator();
        assertTrue(IteratorUtils.getIterator(iterator) == iterator);

        // Test Iterable input
        Iterable<String> iterable = Arrays.asList("a", "b");
        assertTrue(IteratorUtils.getIterator(iterable) instanceof Iterator);

        // Test Object[] input
        Object[] array = {"a", "b"};
        assertTrue(IteratorUtils.getIterator(array) instanceof ObjectArrayIterator);

        // Test Enumeration input
        Vector<String> vector = new Vector<>(Arrays.asList("a", "b"));
        Enumeration<String> enumeration = vector.elements();
        assertTrue(IteratorUtils.getIterator(enumeration) instanceof EnumerationIterator);

        // Test Map input
        Map<String, String> map = new HashMap<>();
        map.put("a", "b");
        assertTrue(IteratorUtils.getIterator(map) instanceof Iterator);

        // Test NodeList input
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
        assertTrue(IteratorUtils.getIterator(nodeList) instanceof NodeListIterator);

        // Test Node input
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
        assertTrue(IteratorUtils.getIterator(node) instanceof NodeListIterator);

        // Test Dictionary input
        Dictionary<String, String> dictionary = new Hashtable<>();
        dictionary.put("a", "b");
        assertTrue(IteratorUtils.getIterator(dictionary) instanceof EnumerationIterator);

        // Test array input
        int[] intArray = {1, 2, 3};
        assertTrue(IteratorUtils.getIterator(intArray) instanceof ArrayIterator);

        // Test object with iterator method
        Object objWithIterator = new Object() {
            public Iterator<String> iterator() {
                return Arrays.asList("a", "b").iterator();
            }
        };
        assertTrue(IteratorUtils.getIterator(objWithIterator) instanceof Iterator);

        // Test default case
        Object defaultObj = new Object();
        assertTrue(IteratorUtils.getIterator(defaultObj) instanceof SingletonIterator);
    }
}
