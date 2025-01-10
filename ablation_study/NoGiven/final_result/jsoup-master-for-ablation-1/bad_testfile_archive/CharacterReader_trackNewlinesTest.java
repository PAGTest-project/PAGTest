
package org.jsoup.parser;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CharacterReader_trackNewlinesTest {

    @Test
    void testTrackNewlines_EnableTracking() {
        CharacterReader reader = new CharacterReader("some input");
        reader.trackNewlines(true);
        assertNotNull(reader.getNewlinePositions());
    }

    @Test
    void testTrackNewlines_DisableTracking() {
        CharacterReader reader = new CharacterReader("some input");
        reader.trackNewlines(true); // Enable first
        reader.trackNewlines(false); // Then disable
        assertNull(reader.getNewlinePositions());
    }
}
