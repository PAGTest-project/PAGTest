
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
        String input = "invalid!@#$%^&*()";
        String expected = "\\invalid\\!\\@\\#\\$\\%\\^\\&\\*\\(\\)";
        assertEquals(expected, TokenQueue.escapeCssIdentifier(input));
    }

    @Test
    public void testEscapeCssIdentifierWithMixedCharacters() {
        String input = "valid-123!@#invalid";
        String expected = "valid-123\\!\\@\\#invalid";
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
        String expected = "\\invalid\\ identifier";
        assertEquals(expected, TokenQueue.escapeCssIdentifier(input));
    }

    @Test
    public void testEscapeCssIdentifierWithEscapedCharacters() {
        String input = "valid\\identifier";
        String expected = "valid\\identifier";
        assertEquals(expected, TokenQueue.escapeCssIdentifier(input));
    }

    @Test
    public void testEscapeCssIdentifierWithNumbersOnly() {
        String input = "123456";
        String expected = "123456";
        assertEquals(expected, TokenQueue.escapeCssIdentifier(input));
    }

    @Test
    public void testEscapeCssIdentifierWithHyphenAndUnderscore() {
        String input = "valid-identifier_with-hyphen_and_underscore";
        String expected = "valid-identifier_with-hyphen_and_underscore";
        assertEquals(expected, TokenQueue.escapeCssIdentifier(input));
    }
}
