
package org.jsoup.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TokenQueue_matchesAnyTest {
    private CharacterReader characterReader;

    @BeforeEach
    public void setUp() {
        characterReader = new CharacterReader("One Two");
    }

    @Test
    public void testMatchesAnyWithEmptyQueue() {
        characterReader = new CharacterReader("");
        assertFalse(characterReader.matchesAny('O', 'T'));
    }

    @Test
    public void testMatchesAnyWithMatchingChar() {
        assertTrue(characterReader.matchesAny('O', 'T'));
    }

    @Test
    public void testMatchesAnyWithNonMatchingChar() {
        assertFalse(characterReader.matchesAny('X', 'Y'));
    }

    @Test
    public void testMatchesAnyWithMultipleChars() {
        assertTrue(characterReader.matchesAny('O', 'N', 'E'));
    }

    @Test
    public void testMatchesAnyWithNoChars() {
        assertFalse(characterReader.matchesAny());
    }
}
