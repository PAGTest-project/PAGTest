
package org.jsoup.helper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Node;

import static org.junit.jupiter.api.Assertions.*;

public class W3CDom_convertTest {
    private W3CDom w3cDom;

    @BeforeEach
    public void setUp() {
        w3cDom = new W3CDom();
    }

    @Test
    public void testConvertDocument() {
        String html = "<html><body><p>Test</p></body></html>";
        Document jsoupDoc = Jsoup.parse(html);
        org.w3c.dom.Document w3cDoc = w3cDom.fromJsoup(jsoupDoc);

        assertNotNull(w3cDoc);
        Node body = w3cDoc.getElementsByTagName("body").item(0);
        assertNotNull(body);
        Node p = w3cDoc.getElementsByTagName("p").item(0);
        assertNotNull(p);
        assertEquals("Test", p.getTextContent());
    }

    @Test
    public void testConvertElement() {
        String html = "<p>Test</p>";
        Document jsoupDoc = Jsoup.parse(html);
        org.w3c.dom.Document w3cDoc = w3cDom.fromJsoup(jsoupDoc.body().child(0));

        assertNotNull(w3cDoc);
        Node p = w3cDoc.getElementsByTagName("p").item(0);
        assertNotNull(p);
        assertEquals("Test", p.getTextContent());
    }
}
