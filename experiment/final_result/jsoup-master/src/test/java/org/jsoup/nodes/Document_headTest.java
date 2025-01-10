
package org.jsoup.nodes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Document_headTest {
    private Document document;

    @BeforeEach
    public void setUp() {
        document = new Document("http://example.com");
    }

    @Test
    public void testHeadElementPresent() {
        // Given a document with an existing head element
        Element html = document.appendElement("html");
        html.appendElement("head");

        // When head() is called
        Element head = document.head();

        // Then the head element should be returned
        assertNotNull(head);
        assertEquals("head", head.tagName());
    }

    @Test
    public void testHeadElementCreated() {
        // Given a document without a head element
        Element html = document.appendElement("html");

        // When head() is called
        Element head = document.head();

        // Then a new head element should be created and returned
        assertNotNull(head);
        assertEquals("head", head.tagName());
        assertTrue(html.children().contains(head));
    }
}
