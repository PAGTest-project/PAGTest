
package org.jsoup.nodes;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Document_createShellTest {

    @Test
    public void testCreateShell() {
        String baseUri = "http://example.com";
        Document doc = Document.createShell(baseUri);

        assertNotNull(doc);
        assertEquals(baseUri, doc.baseUri());

        Element html = doc.child(0);
        assertEquals("html", html.tagName());

        Element head = html.child(0);
        assertEquals("head", head.tagName());

        Element body = html.child(1);
        assertEquals("body", body.tagName());
    }
}
