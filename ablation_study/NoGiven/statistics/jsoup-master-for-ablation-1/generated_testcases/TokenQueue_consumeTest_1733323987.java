
package org.jsoup.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TokenQueue_consumeTest {
    private TokenQueue tokenQueue;

    @BeforeEach
    public void setUp() {
        tokenQueue = new TokenQueue("validsequence");
    }

    @Test
    public void testConsumeValidSequence() {
        tokenQueue.consumeWhitespace();
        assertDoesNotThrow(() -> tokenQueue.consume("valid"));
        assertEquals(5, tokenQueue.pos);
    }

    @Test
    public void testConsumeInvalidSequence() {
        tokenQueue.consumeWhitespace();
        assertThrows(IllegalStateException.class, () -> tokenQueue.consume("invalid"));
    }

    @Test
    public void testConsumeSequenceTooLong() {
        tokenQueue.consumeWhitespace();
        assertThrows(IllegalStateException.class, () -> tokenQueue.consume("validsequenceandmore"));
    }

    @Test
    public void testConsumeEmptyQueue() {
        TokenQueue emptyQueue = new TokenQueue("");
        assertThrows(IllegalStateException.class, () -> emptyQueue.consume("any"));
    }

    @Test
    public void testConsumeWithWhitespace() {
        TokenQueue queueWithWhitespace = new TokenQueue("  validsequence");
        queueWithWhitespace.consumeWhitespace();
        assertDoesNotThrow(() -> queueWithWhitespace.consume("valid"));
        assertEquals(7, queueWithWhitespace.pos);
    }

    @Test
    public void testConsumeWordBeforeSequence() {
        TokenQueue queueWithWord = new TokenQueue("wordvalidsequence");
        assertEquals("word", queueWithWord.consumeWord());
        assertDoesNotThrow(() -> queueWithWord.consume("valid"));
        assertEquals(9, queueWithWord.pos);
    }

    @Test
    public void testConsumeToEnhancement() {
        TokenQueue queue = new TokenQueue("validsequence");
        assertEquals("valid", queue.consumeTo("sequence"));
        assertEquals(5, queue.pos);
    }

    @Test
    public void testConsumeToIgnoreCaseEnhancement() {
        TokenQueue queue = new TokenQueue("VALIDsequence");
        assertEquals("VALID", queue.consumeToIgnoreCase("sequence"));
        assertEquals(5, queue.pos);
    }
}
