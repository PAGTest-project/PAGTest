
package org.jsoup.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TokenQueue_escapeCssIdentifierTest {

    private TokenQueue tokenQueue;

    @BeforeEach
    public void setUp() {
        tokenQueue = new TokenQueue("");
    }

    @Test
    public void testEscapeCssIdentifierWithValidIdentifier() {
        String input = "valid-identifier";
        String expected = "valid-identifier";
        assertEquals(expected, TokenQueue.escapeCssIdentifier(input));
    }

    @Test
    public void testEscapeCssIdentifierWithSpecialCharacter() {
        String input = "invalid.identifier";
        String expected = "invalid\\.identifier";
        assertEquals(expected, TokenQueue.escapeCssIdentifier(input));
    }

    @Test
    public void testEscapeCssIdentifierWithMultipleSpecialCharacters() {
        String input = "invalid.identifier-with_special#chars";
        String expected = "invalid\\.identifier-with_special\\#chars";
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

    @Test
    public void testEscapeCssIdentifierWithEscapedCharacter() {
        String input = "identifier\\with\\escape";
        String expected = "identifier\\\\with\\\\escape";
        assertEquals(expected, TokenQueue.escapeCssIdentifier(input));
    }
}
