
package org.jsoup.parser;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CharacterReader_consumeToTest {

    @Test
    void testConsumeTo_CharFound() {
        CharacterReader reader = new CharacterReader("abcde");
        String result = reader.consumeTo('d');
        assertEquals("abc", result);
    }

    @Test
    void testConsumeTo_CharNotFound() {
        CharacterReader reader = new CharacterReader("abcde");
        String result = reader.consumeTo('f');
        assertEquals("abcde", result);
    }
}
