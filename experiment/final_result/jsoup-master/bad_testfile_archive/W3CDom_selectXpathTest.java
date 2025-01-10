
package org.jsoup.helper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import static org.junit.jupiter.api.Assertions.*;

public class W3CDom_selectXpathTest {
    private W3CDom w3cDom;
    private Document document;

    @BeforeEach
    public void setUp() throws ParserConfigurationException {
        w3cDom = new W3CDom();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        document = builder.newDocument();
    }

    @Test
    public void testSelectXpathSuccess() throws XPathExpressionException {
        Node contextNode = document.createElement("root");
        document.appendChild(contextNode);

        NodeList result = w3cDom.selectXpath("//root", contextNode);
        assertNotNull(result);
        assertEquals(1, result.getLength());
    }

    @Test
    public void testSelectXpathEmptyXpath() {
        Node contextNode = document.createElement("root");
        document.appendChild(contextNode);

        assertThrows(IllegalArgumentException.class, () -> w3cDom.selectXpath("", contextNode));
    }

    @Test
    public void testSelectXpathNullContextNode() {
        assertThrows(IllegalArgumentException.class, () -> w3cDom.selectXpath("//root", null));
    }

    @Test
    public void testSelectXpathInvalidXpath() {
        Node contextNode = document.createElement("root");
        document.appendChild(contextNode);

        assertThrows(Selector.SelectorParseException.class, () -> w3cDom.selectXpath("invalid[xpath", contextNode));
    }
}
