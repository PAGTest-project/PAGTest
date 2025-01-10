
package org.jsoup.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TokenQueue_consumeToTest {
    private TokenQueue tokenQueue;

    @BeforeEach
    public void setUp() {
        tokenQueue = new TokenQueue("one < two </TEXTarea>");
    }

    @Test
    public void testConsumeTo_FoundSequence() {
        String result = tokenQueue.consumeTo("</TEXTarea");
        assertEquals("one < two ", result);
    }

    @Test
    public void testConsumeTo_NotFoundSequence() {
        String result = tokenQueue.consumeTo("</oops>");
        assertEquals("one < two </TEXTarea>", result);
    }

    @Test
    public void testConsumeTo_EmptyQueue() {
        tokenQueue = new TokenQueue("");
        String result = tokenQueue.consumeTo("</TEXTarea");
        assertEquals("", result);
    }

    @Test
    public void testConsumeTo_SequenceAtStart() {
        tokenQueue = new TokenQueue("</TEXTarea> one < two </TEXTarea>");
        String result = tokenQueue.consumeTo("</TEXTarea");
        assertEquals("", result);
    }

    @Test
    public void testConsumeTo_SequenceAtEnd() {
        tokenQueue = new TokenQueue("one < two </TEXTarea>");
        String result = tokenQueue.consumeTo("</TEXTarea>");
        assertEquals("one < two ", result);
    }

    @Test
    public void testConsumeTo_MultipleSequences() {
        tokenQueue = new TokenQueue("one < two </TEXTarea> three < four </TEXTarea>");
        String result = tokenQueue.consumeTo("</TEXTarea");
        assertEquals("one < two ", result);
    }

    @Test
    public void testConsumeTo_SequenceWithWhitespace() {
        tokenQueue = new TokenQueue("one < two </TEXTarea> three < four </TEXTarea>");
        String result = tokenQueue.consumeTo(" </TEXTarea>");
        assertEquals("one < two", result);
    }

    @Test
    public void testConsumeTo_SequenceWithSpecialCharacters() {
        tokenQueue = new TokenQueue("one < two </TEXTarea> three < four </TEXTarea>");
        String result = tokenQueue.consumeTo("<");
        assertEquals("one ", result);
    }
}
