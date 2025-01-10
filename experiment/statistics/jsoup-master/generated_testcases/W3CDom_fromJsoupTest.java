
package org.jsoup.helper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Node;

import static org.junit.jupiter.api.Assertions.*;

public class W3CDom_fromJsoupTest {
    private W3CDom w3cDom;

    @BeforeEach
    public void setUp() {
        w3cDom = new W3CDom();
    }

    @Test
    public void testFromJsoupWithValidDocument() {
        String html = "<html><body><p>Test</p></body></html>";
        Document jsoupDoc = Jsoup.parse(html);
        org.w3c.dom.Document w3cDoc = w3cDom.fromJsoup(jsoupDoc);

        assertNotNull(w3cDoc);
        assertEquals("html", w3cDoc.getFirstChild().getNodeName());
    }

    @Test
    public void testFromJsoupWithNullDocument() {
        assertThrows(IllegalArgumentException.class, () -> {
            w3cDom.fromJsoup((Document) null);
        });
    }
}
