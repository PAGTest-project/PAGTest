
package org.jsoup.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TokenQueue_escapeCssIdentifierTest {

    @BeforeEach
    public void setUp() {
        // Any setup code if needed
    }

    @Test
    public void testEscapeCssIdentifierWithValidIdentifier() {
        String input = "valid-identifier_123";
        String expected = "valid-identifier_123";
        assertEquals(expected, TokenQueue.escapeCssIdentifier(input));
    }

    @Test
    public void testEscapeCssIdentifierWithSpecialCharacters() {
        String input = "invalid.identifier#123";
        String expected = "invalid\\.identifier\\#123";
        assertEquals(expected, TokenQueue.escapeCssIdentifier(input));
    }

    @Test
    public void testEscapeCssIdentifierWithMixedCharacters() {
        String input = "valid-identifier_123.invalid#";
        String expected = "valid-identifier_123\\.invalid\\#";
        assertEquals(expected, TokenQueue.escapeCssIdentifier(input));
    }

    @Test
    public void testEscapeCssIdentifierWithEmptyString() {
        String input = "";
        String expected = "";
        assertEquals(expected, TokenQueue.escapeCssIdentifier(input));
    }

    @Test
    public void testEscapeCssIdentifierWithWhitespace() {
        String input = "invalid identifier";
        String expected = "invalid\\ identifier";
        assertEquals(expected, TokenQueue.escapeCssIdentifier(input));
    }
}
