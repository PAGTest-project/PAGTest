
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
    public void testCreateShellHtmlStructure() {
        Document shell = Document.createShell("http://example.com");
        Element html = shell.htmlEl();
        assertNotNull(html);
        assertEquals("html", html.tagName());
        assertNotNull(html.selectFirst("head"));
        assertNotNull(html.selectFirst("body"));
    }
}
