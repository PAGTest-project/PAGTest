
package org.jsoup.helper;

import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.junit.jupiter.api.Test;
import org.w3c.dom.NodeList;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class W3CDom_sourceNodesTest {

    @Test
    public void testSourceNodes_AllNodesMatchType() {
        // Given
        W3CDom w3cDom = new W3CDom();
        NodeList nodeList = mock(NodeList.class);
        when(nodeList.getLength()).thenReturn(2);

        org.w3c.dom.Node node1 = mock(org.w3c.dom.Node.class);
        org.w3c.dom.Node node2 = mock(org.w3c.dom.Node.class);
        when(nodeList.item(0)).thenReturn(node1);
        when(nodeList.item(1)).thenReturn(node2);

        Element element1 = mock(Element.class);
        Element element2 = mock(Element.class);
        when(node1.getUserData(W3CDom.SourceProperty)).thenReturn(element1);
        when(node2.getUserData(W3CDom.SourceProperty)).thenReturn(element2);

        // When
        List<Element> result = w3cDom.sourceNodes(nodeList, Element.class);

        // Then
        assertEquals(2, result.size());
        assertEquals(element1, result.get(0));
        assertEquals(element2, result.get(1));
    }

    @Test
    public void testSourceNodes_SomeNodesDoNotMatchType() {
        // Given
        W3CDom w3cDom = new W3CDom();
        NodeList nodeList = mock(NodeList.class);
        when(nodeList.getLength()).thenReturn(2);

        org.w3c.dom.Node node1 = mock(org.w3c.dom.Node.class);
        org.w3c.dom.Node node2 = mock(org.w3c.dom.Node.class);
        when(nodeList.item(0)).thenReturn(node1);
        when(nodeList.item(1)).thenReturn(node2);

        Element element1 = mock(Element.class);
        Node node3 = mock(Node.class);
        when(node1.getUserData(W3CDom.SourceProperty)).thenReturn(element1);
        when(node2.getUserData(W3CDom.SourceProperty)).thenReturn(node3);

        // When
        List<Element> result = w3cDom.sourceNodes(nodeList, Element.class);

        // Then
        assertEquals(1, result.size());
        assertEquals(element1, result.get(0));
    }
}
