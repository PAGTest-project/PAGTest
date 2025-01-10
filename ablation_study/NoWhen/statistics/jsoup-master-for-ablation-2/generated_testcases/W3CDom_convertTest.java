
package org.jsoup.helper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.w3c.dom.NodeList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class W3CDom_convertTest {
    private W3CDom w3cDom;

    @BeforeEach
    public void setUp() {
        w3cDom = new W3CDom();
    }

    @Test
    public void testConvertElementToW3CDocument() {
        String html = "<html><head><title>Test</title></head><body><p>Hello, World!</p></body></html>";
        Document jsoupDoc = Jsoup.parse(html);
        org.w3c.dom.Document w3cDoc = w3cDom.fromJsoup(jsoupDoc);

        NodeList nodeList = w3cDom.selectXpath("//title", w3cDoc);
        assertEquals(1, nodeList.getLength());
        assertEquals("Test", nodeList.item(0).getTextContent());

        nodeList = w3cDom.selectXpath("//p", w3cDoc);
        assertEquals(1, nodeList.getLength());
        assertEquals("Hello, World!", nodeList.item(0).getTextContent());
    }

    @Test
    public void testConvertDocumentToW3CDocument() {
        String html = "<html><head><title>Test</title></head><body><p>Hello, World!</p></body></html>";
        Document jsoupDoc = Jsoup.parse(html);
        org.w3c.dom.Document w3cDoc = W3CDom.convert(jsoupDoc);

        NodeList nodeList = w3cDom.selectXpath("//title", w3cDoc);
        assertEquals(1, nodeList.getLength());
        assertEquals("Test", nodeList.item(0).getTextContent());

        nodeList = w3cDom.selectXpath("//p", w3cDoc);
        assertEquals(1, nodeList.getLength());
        assertEquals("Hello, World!", nodeList.item(0).getTextContent());
    }

    @Test
    public void testConvertElementWithNamespace() {
        String html = "<html xmlns=\"http://www.w3.org/1999/xhtml\"><head><title>Test</title></head><body><p>Hello, World!</p></body></html>";
        Document jsoupDoc = Jsoup.parse(html);
        org.w3c.dom.Document w3cDoc = w3cDom.fromJsoup(jsoupDoc);

        NodeList nodeList = w3cDom.selectXpath("//xhtml:title", w3cDoc);
        assertEquals(1, nodeList.getLength());
        assertEquals("Test", nodeList.item(0).getTextContent());

        nodeList = w3cDom.selectXpath("//xhtml:p", w3cDoc);
        assertEquals(1, nodeList.getLength());
        assertEquals("Hello, World!", nodeList.item(0).getTextContent());
    }
}
