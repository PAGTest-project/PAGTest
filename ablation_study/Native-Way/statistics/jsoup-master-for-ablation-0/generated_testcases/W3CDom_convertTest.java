
package org.jsoup.helper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.w3c.dom.NodeList;

import static org.junit.jupiter.api.Assertions.*;

public class W3CDom_convertTest {
    private W3CDom w3cDom;

    @BeforeEach
    public void setUp() {
        w3cDom = new W3CDom();
    }

    @Test
    public void testConvertWithJsoupDocument() {
        String html = "<html><head></head><body><p>Test</p></body></html>";
        Document jsoupDoc = Jsoup.parse(html);
        org.w3c.dom.Document w3cDoc = w3cDom.fromJsoup(jsoupDoc);
        assertNotNull(w3cDoc);
        assertEquals("html", w3cDoc.getDocumentElement().getNodeName());
    }

    @Test
    public void testConvertWithJsoupElement() {
        String html = "<div><p>Test</p></div>";
        Document jsoupDoc = Jsoup.parse(html);
        Element jsoupElement = jsoupDoc.select("div").first();
        org.w3c.dom.Document w3cDoc = w3cDom.fromJsoup(jsoupElement);
        assertNotNull(w3cDoc);
        assertEquals("div", w3cDoc.getDocumentElement().getNodeName());
    }

    @Test
    public void testConvertWithNamespaceAware() {
        w3cDom.namespaceAware(false);
        String html = "<html><head></head><body><p>Test</p></body></html>";
        Document jsoupDoc = Jsoup.parse(html);
        org.w3c.dom.Document w3cDoc = w3cDom.fromJsoup(jsoupDoc);
        assertNotNull(w3cDoc);
        assertEquals("html", w3cDoc.getDocumentElement().getNodeName());
    }

    @Test
    public void testSelectXpath() {
        String html = "<html><head></head><body><p>Test</p></body></html>";
        Document jsoupDoc = Jsoup.parse(html);
        org.w3c.dom.Document w3cDoc = w3cDom.fromJsoup(jsoupDoc);
        NodeList nodeList = w3cDom.selectXpath("//p", w3cDoc);
        assertNotNull(nodeList);
        assertEquals(1, nodeList.getLength());
        assertEquals("p", nodeList.item(0).getNodeName());
    }

    @Test
    public void testAsString() {
        String html = "<html><head></head><body><p>Test</p></body></html>";
        Document jsoupDoc = Jsoup.parse(html);
        org.w3c.dom.Document w3cDoc = w3cDom.fromJsoup(jsoupDoc);
        String output = W3CDom.asString(w3cDoc, null);
        assertNotNull(output);
        assertTrue(output.contains("<p>Test</p>"));
    }
}
