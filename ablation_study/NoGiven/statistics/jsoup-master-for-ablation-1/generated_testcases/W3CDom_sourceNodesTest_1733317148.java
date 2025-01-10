
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
        org.jsoup.nodes.Document jsoupDoc = new org.jsoup.nodes.Document("http://example.com");
        Element jsoupElement = jsoupDoc.appendElement("div");
        jsoupElement.attr(W3CDom.SourceProperty, "true");

        Document w3cDoc = w3cDom.fromJsoup(jsoupElement);
        NodeList nodeList = w3cDom.selectXpath("//div", w3cDoc);

        // When
        List<Element> result = w3cDom.sourceNodes(nodeList, Element.class);

        // Then
        assertEquals(1, result.size());
        assertTrue(result.get(0).hasAttr(W3CDom.SourceProperty));
    }

    @Test
    public void testSourceNodes_WithNoMatchingNodes() {
        // Given
        W3CDom w3cDom = new W3CDom();
        org.jsoup.nodes.Document jsoupDoc = new org.jsoup.nodes.Document("http://example.com");
        Element jsoupElement = jsoupDoc.appendElement("div");
        jsoupElement.attr(W3CDom.SourceProperty, "true");

        Document w3cDoc = w3cDom.fromJsoup(jsoupElement);
        NodeList nodeList = w3cDom.selectXpath("//p", w3cDoc);

        // When
        List<Element> result = w3cDom.sourceNodes(nodeList, Element.class);

        // Then
        assertEquals(0, result.size());
    }
}
