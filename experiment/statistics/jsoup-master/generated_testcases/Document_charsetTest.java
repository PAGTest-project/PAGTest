
package org.jsoup.nodes;

import org.junit.jupiter.api.Test;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Document_charsetTest {

    @Test
    void testCharset() {
        // Given
        Document doc = new Document("baseUri");
        doc.updateMetaCharsetElement(true);
        Document.OutputSettings settings = new Document.OutputSettings();
        settings.charset(StandardCharsets.UTF_8);
        doc.outputSettings(settings);

        // When
        doc.charset(StandardCharsets.ISO_8859_1);

        // Then
        assertEquals(StandardCharsets.ISO_8859_1, doc.charset());
    }
}
