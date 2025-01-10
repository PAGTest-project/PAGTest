
package org.jsoup.helper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.w3c.dom.NodeList;

import static org.junit.jupiter.api.Assertions.*;

public class W3CDom_namespaceAwareTest {
    private W3CDom w3cDom;

    @BeforeEach
    public void setUp() {
        w3cDom = new W3CDom();
    }

    @Test
    public void testNamespaceAwareTrue() {
        w3cDom.namespaceAware(true);
        assertTrue(w3cDom.namespaceAware());
    }

    @Test
    public void testNamespaceAwareFalse() {
        w3cDom.namespaceAware(false);
        assertFalse(w3cDom.namespaceAware());
    }

    @Test
    public void testNamespaceAwareTrueWithXPath() {
        w3cDom.namespaceAware(true);
        Document jsoupDoc = Jsoup.parse("<html><body><p>Test</p></body></html>");
        org.w3c.dom.Document w3cDoc = w3cDom.fromJsoup(jsoupDoc);
        NodeList nodeList = w3cDom.selectXpath("//p", w3cDoc);
        assertEquals(1, nodeList.getLength());
    }

    @Test
    public void testNamespaceAwareFalseWithXPath() {
        w3cDom.namespaceAware(false);
        Document jsoupDoc = Jsoup.parse("<html><body><p>Test</p></body></html>");
        org.w3c.dom.Document w3cDoc = w3cDom.fromJsoup(jsoupDoc);
        NodeList nodeList = w3cDom.selectXpath("//p", w3cDoc);
        assertEquals(1, nodeList.getLength());
    }
}
