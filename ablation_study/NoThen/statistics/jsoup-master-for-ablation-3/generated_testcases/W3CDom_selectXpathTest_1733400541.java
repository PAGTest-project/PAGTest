
package org.jsoup.helper;

import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactoryConfigurationException;
import static org.junit.jupiter.api.Assertions.*;

class W3CDom_selectXpathTest {

    @Test
    void testSelectXpath_Success() {
        // Given
        W3CDom w3cDom = new W3CDom();
        Element jsoupElement = new Element("root");
        jsoupElement.appendElement("child").text("test");
        Document w3cDoc = w3cDom.fromJsoup(jsoupElement);
        String xpath = "//child";

        // When
        NodeList nodeList = w3cDom.selectXpath(xpath, w3cDoc);

        // Then
        assertNotNull(nodeList);
        assertEquals(1, nodeList.getLength());
    }

    @Test
    void testSelectXpath_XPathExpressionException() {
        // Given
        W3CDom w3cDom = new W3CDom();
        Element jsoupElement = new Element("root");
        Document w3cDoc = w3cDom.fromJsoup(jsoupElement);
        String invalidXpath = "//[invalid";

        // When & Then
        assertThrows(Selector.SelectorParseException.class, () -> {
            w3cDom.selectXpath(invalidXpath, w3cDoc);
        });
    }

    @Test
    void testSelectXpath_XPathFactoryConfigurationException() {
        // Given
        W3CDom w3cDom = new W3CDom();
        Element jsoupElement = new Element("root");
        Document w3cDoc = w3cDom.fromJsoup(jsoupElement);
        String xpath = "//child";
        System.setProperty(W3CDom.XPathFactoryProperty, "invalidFactory");

        // When & Then
        assertThrows(Selector.SelectorParseException.class, () -> {
            w3cDom.selectXpath(xpath, w3cDoc);
        });
    }
}
