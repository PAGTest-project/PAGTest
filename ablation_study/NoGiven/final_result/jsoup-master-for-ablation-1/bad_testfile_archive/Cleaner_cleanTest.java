
package org.jsoup.safety;

import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Cleaner_cleanTest {

    @Test
    public void testClean_ValidDocument() {
        // Given
        Safelist safelist = Safelist.basic();
        Cleaner cleaner = new Cleaner(safelist);
        Document dirtyDocument = new Document("http://example.com");
        dirtyDocument.appendElement("p").text("Hello, World!");

        // When
        Document cleanDocument = cleaner.clean(dirtyDocument);

        // Then
        assertNotNull(cleanDocument);
        assertEquals(dirtyDocument.baseUri(), cleanDocument.baseUri());
        assertEquals(1, cleanDocument.body().childNodeSize());
    }

    @Test
    public void testClean_NullDocument() {
        // Given
        Safelist safelist = Safelist.basic();
        Cleaner cleaner = new Cleaner(safelist);

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> {
            cleaner.clean(null);
        });
    }
}
