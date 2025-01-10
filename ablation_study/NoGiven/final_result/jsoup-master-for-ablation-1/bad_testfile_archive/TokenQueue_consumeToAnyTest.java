
package org.jsoup.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TokenQueue_consumeToAnyTest {
    private CharacterReader tokenQueue;

    @BeforeEach
    public void setUp() {
        tokenQueue = new CharacterReader("abcde");
    }

    @Test
    public void testConsumeToAnyWithMatch() {
        String result = tokenQueue.consumeToAny("cd");
        assertEquals("ab", result);
        assertEquals(2, tokenQueue.pos);
    }

    @Test
    public void testConsumeToAnyWithoutMatch() {
        String result = tokenQueue.consumeToAny("f");
        assertEquals("abcde", result);
        assertEquals(5, tokenQueue.pos);
    }

    @Test
    public void testConsumeToAnyWithEmptyQueue() {
        tokenQueue = new CharacterReader("");
        String result = tokenQueue.consumeToAny("a");
        assertEquals("", result);
        assertEquals(0, tokenQueue.pos);
    }

    @Test
    public void testConsumeToAnyWithMultipleMatches() {
        tokenQueue = new CharacterReader("abcdeabcde");
        String result = tokenQueue.consumeToAny("cd", "e");
        assertEquals("ab", result);
        assertEquals(2, tokenQueue.pos);
    }

    @Test
    public void testConsumeToAnyWithNoQueueAdvance() {
        tokenQueue = new CharacterReader("abcde");
        String result = tokenQueue.consumeToAny("a");
        assertEquals("", result);
        assertEquals(0, tokenQueue.pos);
    }
}
