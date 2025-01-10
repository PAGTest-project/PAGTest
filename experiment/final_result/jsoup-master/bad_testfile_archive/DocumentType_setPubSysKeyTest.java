
package org.jsoup.nodes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DocumentType_setPubSysKeyTest {
    private DocumentType documentType;

    @BeforeEach
    public void setUp() {
        documentType = new DocumentType("html", "publicId", "systemId");
    }

    @Test
    public void testSetPubSysKeyWithNonNullValue() {
        documentType.setPubSysKey("PUBLIC");
        assertTrue(documentType.has("pubSysKey"));
        assertEquals("PUBLIC", documentType.attr("pubSysKey"));
    }

    @Test
    public void testSetPubSysKeyWithNullValue() {
        documentType.setPubSysKey(null);
        assertFalse(documentType.has("pubSysKey"));
    }
}
