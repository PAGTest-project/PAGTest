
package org.jsoup.helper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.w3c.dom.NodeList;

import static org.junit.jupiter.api.Assertions.*;

public class W3CDom_fromJsoupTest {
    private W3CDom w3cDom;

    @BeforeEach
    public void setUp() {
        w3cDom = new W3CDom();
    }

    @Test
    public void testFromJsoupWithValidElement() {
        String html = "<html><body><p>Test</p></body></html>";
        Document jsoupDoc = Jsoup.parse(html);
        Element jsoupElement = jsoupDoc.body().child(0);

        org.w3c.dom.Document w3cDoc = w3cDom.fromJsoup(jsoupElement);
        NodeList nodeList = w3cDoc.getElementsByTagName("p");

        assertEquals(1, nodeList.getLength());
        assertEquals("Test", nodeList.item(0).getTextContent());
    }

    @Test
    public void testFromJsoupWithNullElement() {
        assertThrows(IllegalArgumentException.class, () -> {
            w3cDom.fromJsoup(null);
        });
    }

    @Test
    public void testFromJsoupWithDocumentElement() {
        String html = "<html><body><p>Test</p></body></html>";
        Document jsoupDoc = Jsoup.parse(html);

        org.w3c.dom.Document w3cDoc = w3cDom.fromJsoup(jsoupDoc);
        NodeList nodeList = w3cDoc.getElementsByTagName("p");

        assertEquals(1, nodeList.getLength());
        assertEquals("Test", nodeList.item(0).getTextContent());
    }

    @Test
    public void testFromJsoupWithInvalidDoctype() {
        String html = "<!DOCTYPE invalid><html><body><p>Test</p></body></html>";
        Document jsoupDoc = Jsoup.parse(html);

        org.w3c.dom.Document w3cDoc = w3cDom.fromJsoup(jsoupDoc);
        NodeList nodeList = w3cDoc.getElementsByTagName("p");

        assertEquals(1, nodeList.getLength());
        assertEquals("Test", nodeList.item(0).getTextContent());
    }
}
