
package org.jsoup.nodes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Document_outputSettingsTest {
    private Document document;

    @BeforeEach
    public void setUp() {
        document = new Document("baseUri");
    }

    @Test
    public void testOutputSettingsValid() {
        // Given
        Document.OutputSettings outputSettings = new Document.OutputSettings();

        // When
        Document result = document.outputSettings(outputSettings);

        // Then
        assertSame(document, result);
        assertSame(outputSettings, document.outputSettings());
    }

    @Test
    public void testOutputSettingsNull() {
        // When & Then
        assertThrows(IllegalArgumentException.class, () -> document.outputSettings(null));
    }
}
