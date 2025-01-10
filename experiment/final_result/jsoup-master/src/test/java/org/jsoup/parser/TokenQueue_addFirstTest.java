
package org.jsoup.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TokenQueue_addFirstTest {
    private TokenQueue tokenQueue;

    @BeforeEach
    public void setUp() {
        tokenQueue = new TokenQueue("initialQueue");
    }

    @Test
    public void testAddFirst() {
        tokenQueue.addFirst("prepended");
        assertEquals("prependedinitialQueue", tokenQueue.toString());
    }
}
