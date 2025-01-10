
package org.jsoup.safety;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Safelist_simpleTextTest {

    private Safelist safelist;

    @BeforeEach
    public void setUp() {
        safelist = Safelist.simpleText();
    }

    @Test
    public void testSimpleText_ContainsExpectedTags() {
        assertTrue(safelist.isSafeTag("b"));
        assertTrue(safelist.isSafeTag("em"));
        assertTrue(safelist.isSafeTag("i"));
        assertTrue(safelist.isSafeTag("strong"));
        assertTrue(safelist.isSafeTag("u"));
    }

    @Test
    public void testSimpleText_DoesNotContainOtherTags() {
        assertFalse(safelist.isSafeTag("a"));
        assertFalse(safelist.isSafeTag("img"));
        assertFalse(safelist.isSafeTag("div"));
        assertFalse(safelist.isSafeTag("span"));
    }

    @Test
    public void testSimpleText_EnforcedAttributes() {
        Attributes enforcedAttributes = safelist.getEnforcedAttributes("b");
        assertEquals(0, enforcedAttributes.size());
    }

    @Test
    public void testSimpleText_PreserveRelativeLinks() {
        assertFalse(safelist.preserveRelativeLinks());
    }
}
