
package org.jsoup.helper;

import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class W3CDom_sourceNodesTest {

    @Test
    public void testSourceNodes_AllNodesMatch() {
        // Given
        W3CDom w3cDom = new W3CDom();
        NodeList nodeList = mock(NodeList.class);
        Element element1 = mock(Element.class);
        Element element2 = mock(Element.class);
        when(nodeList.getLength()).thenReturn(2);
        when(nodeList.item(0)).thenReturn(mockNodeWithUserData(element1));
        when(nodeList.item(1)).thenReturn(mockNodeWithUserData(element2));

        // When
        List<Element> result = w3cDom.sourceNodes(nodeList, Element.class);

        // Then
        assertEquals(2, result.size());
        assertEquals(element1, result.get(0));
        assertEquals(element2, result.get(1));
    }

    @Test
    public void testSourceNodes_NoNodesMatch() {
        // Given
        W3CDom w3cDom = new W3CDom();
        NodeList nodeList = mock(NodeList.class);
        when(nodeList.getLength()).thenReturn(1);
        when(nodeList.item(0)).thenReturn(mockNodeWithUserData(new Object()));

        // When
        List<Element> result = w3cDom.sourceNodes(nodeList, Element.class);

        // Then
        assertEquals(0, result.size());
    }

    private org.w3c.dom.Node mockNodeWithUserData(Object userData) {
        org.w3c.dom.Node node = mock(org.w3c.dom.Node.class);
        when(node.getUserData(W3CDom.SourceProperty)).thenReturn(userData);
        return node;
    }
}
