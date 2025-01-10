
package org.jsoup.parser;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CharacterReader_consumeToAnyTest {

    @Test
    void testConsumeToAny_NoMatch() {
        CharacterReader reader = new CharacterReader("abcdef");
        String result = reader.consumeToAny('g', 'h');
        assertEquals("abcdef", result);
    }

    @Test
    void testConsumeToAny_MatchFirstChar() {
        CharacterReader reader = new CharacterReader("abcdef");
        String result = reader.consumeToAny('a', 'b');
        assertEquals("", result);
    }

    @Test
    void testConsumeToAny_MatchMiddleChar() {
        CharacterReader reader = new CharacterReader("abcdef");
        String result = reader.consumeToAny('c', 'd');
        assertEquals("ab", result);
    }

    @Test
    void testConsumeToAny_MatchLastChar() {
        CharacterReader reader = new CharacterReader("abcdef");
        String result = reader.consumeToAny('e', 'f');
        assertEquals("abcd", result);
    }
}
