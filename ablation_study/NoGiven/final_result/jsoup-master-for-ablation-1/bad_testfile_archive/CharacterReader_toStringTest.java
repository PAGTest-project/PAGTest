
package org.jsoup.parser;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CharacterReader_toStringTest {

    @Test
    void testToStringWithValidBuffer() {
        CharacterReader reader = new CharacterReader("test");
        reader.bufPos = 0;
        reader.bufLength = 4;
        assertEquals("test", reader.toString());
    }

    @Test
    void testToStringWithEmptyBuffer() {
        CharacterReader reader = new CharacterReader("test");
        reader.bufPos = 4;
        reader.bufLength = 4;
        assertEquals("", reader.toString());
    }
}
