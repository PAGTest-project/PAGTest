
package org.jsoup.helper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
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
    public void testFromJsoupWithValidDocument() {
        String html = "<html><head><title>Test</title></head><body><p>Hello</p></body></html>";
        Document jsoupDoc = Jsoup.parse(html);

        org.w3c.dom.Document w3cDoc = w3cDom.fromJsoup(jsoupDoc);
        assertNotNull(w3cDoc);

        NodeList paragraphs = w3cDoc.getElementsByTagName("p");
        assertEquals(1, paragraphs.getLength());
        assertEquals("Hello", paragraphs.item(0).getTextContent());
    }

    @Test
    public void testFromJsoupWithNullDocument() {
        assertThrows(IllegalArgumentException.class, () -> {
            w3cDom.fromJsoup(null);
        });
    }

    @Test
    public void testFromJsoupWithInvalidDoctype() {
        String html = "<!DOCTYPE invalid><html><head><title>Test</title></head><body><p>Hello</p></body></html>";
        Document jsoupDoc = Jsoup.parse(html);

        org.w3c.dom.Document w3cDoc = w3cDom.fromJsoup(jsoupDoc);
        assertNotNull(w3cDoc);

        NodeList doctype = w3cDoc.getElementsByTagName("!DOCTYPE");
        assertEquals(0, doctype.getLength());
    }

    @Test
    public void testFromJsoupWithElement() {
        String html = "<html><head><title>Test</title></head><body><p>Hello</p></body></html>";
        Document jsoupDoc = Jsoup.parse(html);
        org.jsoup.nodes.Element jsoupElement = jsoupDoc.body().child(0);

        org.w3c.dom.Document w3cDoc = w3cDom.fromJsoup(jsoupElement);
        assertNotNull(w3cDoc);

        NodeList paragraphs = w3cDoc.getElementsByTagName("p");
        assertEquals(1, paragraphs.getLength());
        assertEquals("Hello", paragraphs.item(0).getTextContent());
    }
}
