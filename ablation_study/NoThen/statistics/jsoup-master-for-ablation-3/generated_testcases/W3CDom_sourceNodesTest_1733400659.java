
package org.jsoup.helper;

import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class W3CDom_sourceNodesTest {

    @Test
    public void testSourceNodes_WithMatchingNodes() {
        // Given
        W3CDom w3cDom = new W3CDom();
        Document doc = w3cDom.fromJsoup(new Element("root"));
        NodeList nodeList = w3cDom.selectXpath("//*", doc);

        // When
        List<Element> result = w3cDom.sourceNodes(nodeList, Element.class);

        // Then
        assertEquals(1, result.size());
        assertTrue(result.get(0) instanceof Element);
    }

    @Test
    public void testSourceNodes_WithNoMatchingNodes() {
        // Given
        W3CDom w3cDom = new W3CDom();
        Document doc = w3cDom.fromJsoup(new Element("root"));
        NodeList nodeList = w3cDom.selectXpath("//*", doc);

        // When
        List<Node> result = w3cDom.sourceNodes(nodeList, Node.class);

        // Then
        assertEquals(0, result.size());
    }
}
