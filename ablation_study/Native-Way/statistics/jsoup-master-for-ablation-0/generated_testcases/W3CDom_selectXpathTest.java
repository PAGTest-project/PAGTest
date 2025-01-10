
package org.jsoup.helper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import static org.junit.jupiter.api.Assertions.*;

public class W3CDom_selectXpathTest {
    private W3CDom w3cDom;
    private DocumentBuilder documentBuilder;

    @BeforeEach
    public void setUp() throws ParserConfigurationException {
        w3cDom = new W3CDom();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        documentBuilder = factory.newDocumentBuilder();
    }

    @Test
    public void testSelectXpathValid() throws Exception {
        Document doc = documentBuilder.newDocument();
        Node root = doc.createElement("root");
        doc.appendChild(root);
        Node child = doc.createElement("child");
        root.appendChild(child);

        NodeList nodeList = w3cDom.selectXpath("//child", doc);
        assertNotNull(nodeList);
        assertEquals(1, nodeList.getLength());
        assertEquals("child", nodeList.item(0).getNodeName());
    }

    @Test
    public void testSelectXpathInvalidXPath() {
        Document doc = documentBuilder.newDocument();
        Node root = doc.createElement("root");
        doc.appendChild(root);

        assertThrows(Selector.SelectorParseException.class, () -> {
            w3cDom.selectXpath("invalid[xpath", doc);
        });
    }

    @Test
    public void testSelectXpathNullContextNode() {
        assertThrows(IllegalArgumentException.class, () -> {
            w3cDom.selectXpath("//child", null);
        });
    }

    @Test
    public void testSelectXpathEmptyXPath() {
        Document doc = documentBuilder.newDocument();
        Node root = doc.createElement("root");
        doc.appendChild(root);

        assertThrows(IllegalArgumentException.class, () -> {
            w3cDom.selectXpath("", doc);
        });
    }

    @Test
    public void testSelectXpathNoMatches() throws Exception {
        Document doc = documentBuilder.newDocument();
        Node root = doc.createElement("root");
        doc.appendChild(root);

        NodeList nodeList = w3cDom.selectXpath("//nonexistent", doc);
        assertNotNull(nodeList);
        assertEquals(0, nodeList.getLength());
    }
}
