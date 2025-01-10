
package org.jsoup.helper;

import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactoryConfigurationException;

import static org.junit.jupiter.api.Assertions.*;

class W3CDom_selectXpathTest {

    @Test
    void testSelectXpath_Success() throws Exception {
        // Given
        W3CDom w3cDom = new W3CDom();
        Document doc = w3cDom.fromJsoup(org.jsoup.Jsoup.parse("<root><child>text</child></root>"));
        String xpath = "//child";

        // When
        NodeList nodeList = w3cDom.selectXpath(xpath, doc);

        // Then
        assertNotNull(nodeList);
        assertEquals(1, nodeList.getLength());
        assertEquals("child", nodeList.item(0).getNodeName());
    }

    @Test
    void testSelectXpath_XPathExpressionException() {
        // Given
        W3CDom w3cDom = new W3CDom();
        Document doc = w3cDom.fromJsoup(org.jsoup.Jsoup.parse("<root><child>text</child></root>"));
        String invalidXpath = "//invalid[";

        // When & Then
        assertThrows(Selector.SelectorParseException.class, () -> {
            w3cDom.selectXpath(invalidXpath, doc);
        });
    }

    @Test
    void testSelectXpath_XPathFactoryConfigurationException() {
        // Given
        W3CDom w3cDom = new W3CDom();
        Document doc = w3cDom.fromJsoup(org.jsoup.Jsoup.parse("<root><child>text</child></root>"));
        String xpath = "//child";
        System.setProperty(W3CDom.XPathFactoryProperty, "invalidFactory");

        // When & Then
        assertThrows(Selector.SelectorParseException.class, () -> {
            w3cDom.selectXpath(xpath, doc);
        });
    }
}
