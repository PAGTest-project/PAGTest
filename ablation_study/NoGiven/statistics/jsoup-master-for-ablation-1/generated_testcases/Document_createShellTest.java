
package org.jsoup.nodes;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Document_createShellTest {

    @Test
    public void testCreateShell() {
        // Given
        String baseUri = "http://example.com";

        // When
        Document doc = Document.createShell(baseUri);

        // Then
        assertNotNull(doc);
        assertEquals(baseUri, doc.baseUri());
        assertEquals(1, doc.childrenSize());
        Element html = doc.child(0);
        assertEquals("html", html.tagName());
        assertEquals(2, html.childrenSize());
        assertEquals("head", html.child(0).tagName());
        assertEquals("body", html.child(1).tagName());
    }
}
