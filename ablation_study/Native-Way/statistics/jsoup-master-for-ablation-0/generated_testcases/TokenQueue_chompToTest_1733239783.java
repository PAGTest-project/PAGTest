
package org.jsoup.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TokenQueue_chompToTest {
    private TokenQueue tokenQueue;

    @BeforeEach
    public void setUp() {
        tokenQueue = new TokenQueue("initial data");
    }

    @Test
    public void testChompTo() {
        tokenQueue = new TokenQueue("one < two </TEXTarea>");
        String data = tokenQueue.chompTo("</TEXTarea");
        assertEquals("one < two ", data);

        tokenQueue = new TokenQueue("<textarea> one two < three </oops>");
        data = tokenQueue.chompTo("</textarea");
        assertEquals("<textarea> one two < three ", data);
    }

    @Test
    public void testChompToWithNoMatch() {
        tokenQueue = new TokenQueue("no match here");
        String data = tokenQueue.chompTo("</textarea");
        assertEquals("no match here", data);
    }

    @Test
    public void testChompToWithEmptyQueue() {
        tokenQueue = new TokenQueue("");
        String data = tokenQueue.chompTo("</textarea");
        assertEquals("", data);
    }

    @Test
    public void testChompToWithExactMatch() {
        tokenQueue = new TokenQueue("</textarea>");
        String data = tokenQueue.chompTo("</textarea");
        assertEquals("", data);
    }

    @Test
    public void testChompToWithPartialMatch() {
        tokenQueue = new TokenQueue("</textare");
        String data = tokenQueue.chompTo("</textarea");
        assertEquals("</textare", data);
    }
}
