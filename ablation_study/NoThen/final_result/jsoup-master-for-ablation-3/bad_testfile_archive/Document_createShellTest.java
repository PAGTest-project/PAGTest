
package org.jsoup.nodes;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Document_createShellTest {

    @Test
    void testCreateShellWithValidBaseUri() {
        String baseUri = "http://example.com";
        Document doc = Document.createShell(baseUri);

        assertNotNull(doc);
        assertEquals(baseUri, doc.baseUri());
        assertEquals("html", doc.child(0).tagName());
        assertEquals("head", doc.child(0).child(0).tagName());
        assertEquals("body", doc.child(0).child(1).tagName());
    }

    @Test
    void testCreateShellWithNullBaseUri() {
        assertThrows(IllegalArgumentException.class, () -> {
            Document.createShell(null);
        });
    }
}
