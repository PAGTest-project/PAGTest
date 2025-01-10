
package org.jsoup.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TokenQueue_consumeToIgnoreCaseTest {
    private TokenQueue tokenQueue;

    @BeforeEach
    public void setUp() {
        tokenQueue = new TokenQueue("sample data");
    }

    @Test
    public void testConsumeToIgnoreCase_MatchFound() {
        tokenQueue = new TokenQueue("<textarea>one < two </TEXTarea>");
        String result = tokenQueue.consumeToIgnoreCase("</textarea");
        assertEquals("<textarea>one < two ", result);
    }

    @Test
    public void testConsumeToIgnoreCase_NoMatchFound() {
        tokenQueue = new TokenQueue("<textarea> one two < three </oops>");
        String result = tokenQueue.consumeToIgnoreCase("</textarea");
        assertEquals("<textarea> one two < three </oops>", result);
    }

    @Test
    public void testConsumeToIgnoreCase_EmptyQueue() {
        tokenQueue = new TokenQueue("");
        String result = tokenQueue.consumeToIgnoreCase("</textarea");
        assertEquals("", result);
    }

    @Test
    public void testConsumeToIgnoreCase_SingleCharacterMatch() {
        tokenQueue = new TokenQueue("aBcDeFg");
        String result = tokenQueue.consumeToIgnoreCase("d");
        assertEquals("aBc", result);
    }

    @Test
    public void testConsumeToIgnoreCase_CaseInsensitiveMatch() {
        tokenQueue = new TokenQueue("aBcDeFg");
        String result = tokenQueue.consumeToIgnoreCase("D");
        assertEquals("aBc", result);
    }
}
