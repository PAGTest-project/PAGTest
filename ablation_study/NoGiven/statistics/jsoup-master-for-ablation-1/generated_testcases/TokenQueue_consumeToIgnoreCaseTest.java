
package org.jsoup.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TokenQueue_consumeToIgnoreCaseTest {
    private TokenQueue tokenQueue;

    @BeforeEach
    public void setUp() {
        tokenQueue = new TokenQueue("sample data for testing");
    }

    @Test
    public void testConsumeToIgnoreCase_SimpleMatch() {
        tokenQueue.addFirst("simple match");
        String result = tokenQueue.consumeToIgnoreCase("match");
        assertEquals("simple ", result);
    }

    @Test
    public void testConsumeToIgnoreCase_NoMatch() {
        tokenQueue.addFirst("no match here");
        String result = tokenQueue.consumeToIgnoreCase("nomatch");
        assertEquals("no match here", result);
    }

    @Test
    public void testConsumeToIgnoreCase_CaseInsensitiveMatch() {
        tokenQueue.addFirst("Case Insensitive Match");
        String result = tokenQueue.consumeToIgnoreCase("insensitive");
        assertEquals("Case ", result);
    }

    @Test
    public void testConsumeToIgnoreCase_EmptyQueue() {
        tokenQueue = new TokenQueue("");
        String result = tokenQueue.consumeToIgnoreCase("anything");
        assertEquals("", result);
    }

    @Test
    public void testConsumeToIgnoreCase_MultipleMatches() {
        tokenQueue.addFirst("match one match two");
        String result = tokenQueue.consumeToIgnoreCase("match");
        assertEquals("match one ", result);
    }

    @Test
    public void testConsumeToIgnoreCase_SpecialCharacters() {
        tokenQueue.addFirst("special characters: !@#$%^&*()");
        String result = tokenQueue.consumeToIgnoreCase("!@#$");
        assertEquals("special characters: ", result);
    }

    @Test
    public void testConsumeToIgnoreCase_WhitespaceHandling() {
        tokenQueue.addFirst("  leading whitespace");
        String result = tokenQueue.consumeToIgnoreCase("whitespace");
        assertEquals("  leading ", result);
    }

    @Test
    public void testConsumeToIgnoreCase_EscapedCharacters() {
        tokenQueue.addFirst("escaped \\( characters \\)");
        String result = tokenQueue.consumeToIgnoreCase("\\)");
        assertEquals("escaped \\( characters ", result);
    }
}
