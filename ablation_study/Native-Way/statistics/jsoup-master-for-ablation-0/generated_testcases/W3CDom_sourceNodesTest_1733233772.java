
package org.jsoup.helper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class W3CDom_sourceNodesTest {
    private W3CDom w3cDom;

    @BeforeEach
    public void setUp() {
        w3cDom = new W3CDom();
    }

    @Test
    public void testSourceNodesWithTextNode() throws ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        Document doc = factory.newDocumentBuilder().newDocument();
        Node textNode = doc.createTextNode("test text");
        textNode.setUserData(W3CDom.SourceProperty, new TextNode("test text"), null);
        doc.appendChild(textNode);
        NodeList nodeList = doc.getChildNodes();

        List<TextNode> textNodes = w3cDom.sourceNodes(nodeList, TextNode.class);
        assertEquals(1, textNodes.size());
        assertEquals("test text", textNodes.get(0).getWholeText());
    }

    @Test
    public void testSourceNodesWithElement() throws ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        Document doc = factory.newDocumentBuilder().newDocument();
        Element element = Jsoup.parse("<div>test element</div>").body().child(0);
        Node w3cElement = doc.createElement("div");
        w3cElement.setUserData(W3CDom.SourceProperty, element, null);
        doc.appendChild(w3cElement);
        NodeList nodeList = doc.getChildNodes();

        List<Element> elements = w3cDom.sourceNodes(nodeList, Element.class);
        assertEquals(1, elements.size());
        assertEquals("div", elements.get(0).tagName());
        assertEquals("test element", elements.get(0).text());
    }

    @Test
    public void testSourceNodesWithEmptyNodeList() {
        NodeList emptyNodeList = new NodeList() {
            @Override
            public Node item(int index) {
                return null;
            }

            @Override
            public int getLength() {
                return 0;
            }
        };

        List<TextNode> textNodes = w3cDom.sourceNodes(emptyNodeList, TextNode.class);
        assertTrue(textNodes.isEmpty());
    }

    @Test
    public void testSourceNodesWithMixedNodes() throws ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        Document doc = factory.newDocumentBuilder().newDocument();
        Element element = Jsoup.parse("<div>test element</div>").body().child(0);
        Node w3cElement = doc.createElement("div");
        w3cElement.setUserData(W3CDom.SourceProperty, element, null);
        Node textNode = doc.createTextNode("test text");
        textNode.setUserData(W3CDom.SourceProperty, new TextNode("test text"), null);
        doc.appendChild(w3cElement);
        doc.appendChild(textNode);
        NodeList nodeList = doc.getChildNodes();

        List<Element> elements = w3cDom.sourceNodes(nodeList, Element.class);
        assertEquals(1, elements.size());
        assertEquals("div", elements.get(0).tagName());
        assertEquals("test element", elements.get(0).text());

        List<TextNode> textNodes = w3cDom.sourceNodes(nodeList, TextNode.class);
        assertEquals(1, textNodes.size());
        assertEquals("test text", textNodes.get(0).getWholeText());
    }
}
