
package org.jsoup.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TokenQueue_consumeToAnyTest {
    private TokenQueue tokenQueue;

    @BeforeEach
    public void setUp() {
        tokenQueue = new TokenQueue("one < two </TEXTarea>");
    }

    @Test
    public void testConsumeToAnyWithMatch() {
        String result = tokenQueue.consumeToAny("</TEXTarea>");
        assertEquals("one < two ", result);
    }

    @Test
    public void testConsumeToAnyWithoutMatch() {
        tokenQueue = new TokenQueue("one < two </TEXTarea>");
        String result = tokenQueue.consumeToAny("</oops>");
        assertEquals("one < two </TEXTarea>", result);
    }

    @Test
    public void testConsumeToAnyWithEmptyQueue() {
        tokenQueue = new TokenQueue("");
        String result = tokenQueue.consumeToAny("</TEXTarea>");
        assertEquals("", result);
    }

    @Test
    public void testConsumeToAnyWithMultipleMatches() {
        tokenQueue = new TokenQueue("one < two </TEXTarea> three < four </TEXTarea>");
        String result = tokenQueue.consumeToAny("</TEXTarea>", "</TEXTarea>");
        assertEquals("one < two ", result);
    }

    @Test
    public void testConsumeToAnyWithNoQueueAdvance() {
        tokenQueue = new TokenQueue("one < two </TEXTarea>");
        tokenQueue.consumeToAny("</TEXTarea>");
        assertEquals("</TEXTarea>", tokenQueue.remainder());
    }
}
