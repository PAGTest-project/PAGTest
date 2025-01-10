
package org.jsoup.parser;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Parser_settingsTest {

    @Test
    void testSettings() {
        // Given
        Parser parser = new Parser(new HtmlTreeBuilder());
        ParseSettings newSettings = new ParseSettings(true, true);

        // When
        Parser updatedParser = parser.settings(newSettings);

        // Then
        assertEquals(newSettings, updatedParser.settings());
    }
}
