
package org.jsoup.safety;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Safelist_simpleTextTest {

    @Test
    void testSimpleText() {
        Safelist safelist = Safelist.simpleText();
        assertTrue(safelist.isSafeTag("b"));
        assertTrue(safelist.isSafeTag("em"));
        assertTrue(safelist.isSafeTag("i"));
        assertTrue(safelist.isSafeTag("strong"));
        assertTrue(safelist.isSafeTag("u"));
        assertFalse(safelist.isSafeTag("a"));
    }
}
