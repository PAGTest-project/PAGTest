
package org.jsoup.helper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.w3c.dom.DOMException;

import static org.junit.jupiter.api.Assertions.*;

public class W3CDom_fromJsoupTest {
    private W3CDom w3cDom;

    @BeforeEach
    public void setUp() {
        w3cDom = new W3CDom();
    }

    @Test
    public void testFromJsoupWithValidDocument() {
        String html = "<!DOCTYPE html><html><head></head><body><p>test</p></body></html>";
        Document jsoupDoc = Jsoup.parse(html);

        org.w3c.dom.Document w3cDoc = w3cDom.fromJsoup(jsoupDoc);
        assertNotNull(w3cDoc);
        assertEquals("html", w3cDoc.getDocumentElement().getTagName());
    }

    @Test
    public void testFromJsoupWithNullElement() {
        assertThrows(IllegalArgumentException.class, () -> {
            w3cDom.fromJsoup((Element) null);
        });
    }

    @Test
    public void testFromJsoupWithInvalidDoctype() {
        String html = "<!DOCTYPE invalid><html><head></head><body><p>test</p></body></html>";
        Document jsoupDoc = Jsoup.parse(html);

        org.w3c.dom.Document w3cDoc = w3cDom.fromJsoup(jsoupDoc);
        assertNotNull(w3cDoc);
        assertNull(w3cDoc.getDoctype());
    }

    @Test
    public void testFromJsoupWithElement() {
        String html = "<div><p>test</p></div>";
        Document jsoupDoc = Jsoup.parse(html);
        Element jsoupElement = jsoupDoc.select("div").first();

        org.w3c.dom.Document w3cDoc = w3cDom.fromJsoup(jsoupElement);
        assertNotNull(w3cDoc);
        assertEquals("div", w3cDoc.getDocumentElement().getTagName());
    }

    @Test
    public void testFromJsoupWithNamespaceAware() {
        w3cDom.namespaceAware(false);
        String html = "<html xmlns='http://www.w3.org/1999/xhtml'><head></head><body><p>test</p></body></html>";
        Document jsoupDoc = Jsoup.parse(html);

        org.w3c.dom.Document w3cDoc = w3cDom.fromJsoup(jsoupDoc);
        assertNotNull(w3cDoc);
        assertEquals("html", w3cDoc.getDocumentElement().getTagName());
    }

    @Test
    public void testFromJsoupWithDOMException() {
        String html = "<invalidTag><p>test</p></invalidTag>";
        Document jsoupDoc = Jsoup.parse(html);

        assertThrows(DOMException.class, () -> {
            w3cDom.fromJsoup(jsoupDoc);
        });
    }
}
