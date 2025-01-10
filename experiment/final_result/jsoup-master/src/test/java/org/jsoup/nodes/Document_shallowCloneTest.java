
package org.jsoup.nodes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

public class Document_shallowCloneTest {
    private Document originalDocument;

    @BeforeEach
    public void setUp() {
        originalDocument = new Document("http://example.com");
        originalDocument.outputSettings().charset("UTF-8");
        originalDocument.attributes = new Attributes();
    }

    @Test
    public void testShallowClone() {
        // Given
        Document clone = originalDocument.shallowClone();

        // Then
        assertEquals(originalDocument.tag().namespace(), clone.tag().namespace());
        assertEquals(originalDocument.baseUri(), clone.baseUri());
        assertEquals(originalDocument.outputSettings().charset(), clone.outputSettings().charset());
        assertNotSame(originalDocument.attributes, clone.attributes);
        assertNotSame(originalDocument.outputSettings(), clone.outputSettings());
    }
}
