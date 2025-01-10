
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
        String input = "invalid#identifier";
        String expected = "invalid\\#identifier";
        assertEquals(expected, TokenQueue.escapeCssIdentifier(input));
    }

    @Test
    public void testEscapeCssIdentifierWithEscapedCharacters() {
        String input = "escaped\\identifier";
        String expected = "escaped\\\\identifier";
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
        String input = "identifier with space";
        String expected = "identifier\\ with\\ space";
        assertEquals(expected, TokenQueue.escapeCssIdentifier(input));
    }
}
