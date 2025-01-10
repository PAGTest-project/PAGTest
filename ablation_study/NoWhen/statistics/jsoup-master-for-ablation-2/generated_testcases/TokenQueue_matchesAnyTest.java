
package org.jsoup.parser;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TokenQueue_matchesAnyTest {

    @Test
    public void testMatchesAny_EmptyQueue() {
        TokenQueue tq = new TokenQueue("");
        assertFalse(tq.matchesAny('a', 'b', 'c'));
    }

    @Test
    public void testMatchesAny_NoMatch() {
        TokenQueue tq = new TokenQueue("xyz");
        assertFalse(tq.matchesAny('a', 'b', 'c'));
    }

    @Test
    public void testMatchesAny_Match() {
        TokenQueue tq = new TokenQueue("abc");
        assertTrue(tq.matchesAny('a', 'b', 'c'));
    }
}
