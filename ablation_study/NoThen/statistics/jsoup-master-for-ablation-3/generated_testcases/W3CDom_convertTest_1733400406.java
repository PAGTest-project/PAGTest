
package org.jsoup.helper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Node;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class W3CDom_convertTest {

    @Test
    public void testConvertWithDocument() {
        // Given
        Document jsoupDoc = Jsoup.parse("<html><head></head><body><div id='test'>Hello</div></body></html>");
        W3CDom w3cDom = new W3CDom();
        org.w3c.dom.Document w3cDoc = w3cDom.fromJsoup(jsoupDoc);

        // When
        w3cDom.convert(jsoupDoc, w3cDoc);

        // Then
        Node rootEl = w3cDoc.getDocumentElement().getFirstChild();
        assertNotNull(rootEl);
        assertEquals("div", rootEl.getNodeName());
        assertEquals("test", rootEl.getAttributes().getNamedItem("id").getNodeValue());
    }

    @Test
    public void testConvertWithElement() {
        // Given
        Document jsoupDoc = Jsoup.parse("<html><head></head><body><div id='test'>Hello</div></body></html>");
        Element jsoupEl = jsoupDoc.select("div").first();
        W3CDom w3cDom = new W3CDom();
        org.w3c.dom.Document w3cDoc = w3cDom.fromJsoup(jsoupDoc);

        // When
        w3cDom.convert(jsoupEl, w3cDoc);

        // Then
        Node rootEl = w3cDoc.getDocumentElement().getFirstChild();
        assertNotNull(rootEl);
        assertEquals("div", rootEl.getNodeName());
        assertEquals("test", rootEl.getAttributes().getNamedItem("id").getNodeValue());
    }
}
