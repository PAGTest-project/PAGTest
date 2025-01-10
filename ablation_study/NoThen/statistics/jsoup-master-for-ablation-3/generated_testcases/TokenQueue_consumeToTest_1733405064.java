
package org.jsoup.parser;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TokenQueue_consumeToTest {

    @Test
    public void testConsumeTo_Found() {
        TokenQueue tq = new TokenQueue("abcdefghi");
        String result = tq.consumeTo("efg");
        assertEquals("abcd", result);
        assertEquals(4, tq.pos);
    }

    @Test
    public void testConsumeTo_NotFound() {
        TokenQueue tq = new TokenQueue("abcdefghi");
        String result = tq.consumeTo("xyz");
        assertEquals("abcdefghi", result);
        assertEquals(9, tq.pos);
    }
}
