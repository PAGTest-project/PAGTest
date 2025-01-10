
package org.jsoup.nodes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.jsoup.Jsoup;
import org.jsoup.parser.Parser;

import static org.junit.jupiter.api.Assertions.*;

public class Document_documentTypeTest {
    private Document document;

    @BeforeEach
    public void setUp() {
        document = new Document("http://example.com");
    }

    @Test
    public void testDocumentTypeFound() {
        DocumentType docType = new DocumentType("html", "-//W3C//DTD HTML 4.01//EN", "http://www.w3.org/TR/html4/strict.dtd");
        document.appendChild(docType);
        assertEquals(docType, document.documentType());
    }

    @Test
    public void testDocumentTypeNotFound() {
        assertNull(document.documentType());
    }

    @Test
    public void testDocumentTypeFoundAfterLeafNode() {
        document.appendChild(new TextNode("Some text"));
        DocumentType docType = new DocumentType("html", "-//W3C//DTD HTML 4.01//EN", "http://www.w3.org/TR/html4/strict.dtd");
        document.appendChild(docType);
        assertEquals(docType, document.documentType());
    }
}
