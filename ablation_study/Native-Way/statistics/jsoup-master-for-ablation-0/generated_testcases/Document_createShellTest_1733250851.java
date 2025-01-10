
package org.jsoup.nodes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Document_createShellTest {
    private Document document;

    @BeforeEach
    public void setUp() {
        document = new Document("http://example.com");
    }

    @Test
    public void testCreateShellWithValidBaseUri() {
        Document shell = Document.createShell("http://example.com");
        assertNotNull(shell);
        assertEquals("http://example.com", shell.baseUri());
        assertNotNull(shell.head());
        assertNotNull(shell.body());
    }

    @Test
    public void testCreateShellWithNullBaseUri() {
        assertThrows(IllegalArgumentException.class, () -> {
            Document.createShell(null);
        });
    }

    @Test
    public void testCreateShellStructure() {
        Document shell = Document.createShell("http://example.com");
        Element html = shell.child(0);
        assertNotNull(html);
        assertEquals("html", html.tagName());

        Element head = html.child(0);
        assertNotNull(head);
        assertEquals("head", head.tagName());

        Element body = html.child(1);
        assertNotNull(body);
        assertEquals("body", body.tagName());
    }
}
