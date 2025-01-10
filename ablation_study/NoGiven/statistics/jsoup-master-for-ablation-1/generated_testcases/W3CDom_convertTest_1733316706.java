
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
    public void testConvertWithValidDocument() {
        String html = "<html><head><title>Test</title></head><body><p>Paragraph</p></body></html>";
        Document jsoupDoc = Jsoup.parse(html);
        org.w3c.dom.Document w3cDoc = w3cDom.fromJsoup(jsoupDoc);

        NodeList paragraphs = w3cDoc.getElementsByTagName("p");
        assertEquals(1, paragraphs.getLength());
        assertEquals("Paragraph", paragraphs.item(0).getTextContent());
    }

    @Test
    public void testConvertWithInvalidElement() {
        String html = "<invalid>Invalid Element</invalid>";
        Document jsoupDoc = Jsoup.parse(html);
        org.w3c.dom.Document w3cDoc = w3cDom.fromJsoup(jsoupDoc);

        NodeList invalidElements = w3cDoc.getElementsByTagName("invalid");
        assertEquals(1, invalidElements.getLength());
        assertEquals("Invalid Element", invalidElements.item(0).getTextContent());
    }

    @Test
    public void testConvertWithNamespaceAware() {
        w3cDom.namespaceAware(false);
        String html = "<html xmlns='http://www.w3.org/1999/xhtml'><head><title>Namespace Test</title></head><body><p>Paragraph</p></body></html>";
        Document jsoupDoc = Jsoup.parse(html);
        org.w3c.dom.Document w3cDoc = w3cDom.fromJsoup(jsoupDoc);

        NodeList paragraphs = w3cDoc.getElementsByTagName("p");
        assertEquals(1, paragraphs.getLength());
        assertEquals("Paragraph", paragraphs.item(0).getTextContent());
    }

    @Test
    public void testConvertWithDocumentURI() {
        String html = "<html><head><title>URI Test</title></head><body><p>Paragraph</p></body></html>";
        Document jsoupDoc = Jsoup.parse(html);
        jsoupDoc.location("http://example.com");
        org.w3c.dom.Document w3cDoc = w3cDom.fromJsoup(jsoupDoc);

        assertEquals("http://example.com", w3cDoc.getDocumentURI());
    }

    @Test
    public void testConvertWithNullInput() {
        assertThrows(IllegalArgumentException.class, () -> {
            w3cDom.fromJsoup((Element) null);
        });
    }
}
