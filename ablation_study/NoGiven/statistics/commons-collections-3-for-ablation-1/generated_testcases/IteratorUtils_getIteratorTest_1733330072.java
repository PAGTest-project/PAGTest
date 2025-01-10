
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
    public void testGetIterator() {
        // Given
        Object[] array = {1, 2, 3};
        List<Object> list = Arrays.asList(array);
        Map<Object, Object> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");
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
        Node node = new Node() {
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

        // When
        Iterator<?> iteratorFromArray = IteratorUtils.getIterator(array);
        Iterator<?> iteratorFromList = IteratorUtils.getIterator(list);
        Iterator<?> iteratorFromMap = IteratorUtils.getIterator(map);
        Iterator<?> iteratorFromNodeList = IteratorUtils.getIterator(nodeList);
        Iterator<?> iteratorFromNode = IteratorUtils.getIterator(node);
        Iterator<?> iteratorFromNull = IteratorUtils.getIterator(null);

        // Then
        assertTrue(iteratorFromArray instanceof ArrayIterator);
        assertTrue(iteratorFromList instanceof Iterator);
        assertTrue(iteratorFromMap instanceof Iterator);
        assertTrue(iteratorFromNodeList instanceof NodeListIterator);
        assertTrue(iteratorFromNode instanceof NodeListIterator);
        assertTrue(iteratorFromNull instanceof EmptyIterator);
    }
}
