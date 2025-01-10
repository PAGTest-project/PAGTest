
package org.jsoup.safety;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Safelist_simpleTextTest {

    @Test
    public void testSimpleText_AddsCorrectTags() {
        Safelist safelist = Safelist.simpleText();

        assertTrue(safelist.isSafeTag("b"));
        assertTrue(safelist.isSafeTag("em"));
        assertTrue(safelist.isSafeTag("i"));
        assertTrue(safelist.isSafeTag("strong"));
        assertTrue(safelist.isSafeTag("u"));
    }

    @Test
    public void testSimpleText_NoEnforcedAttributes() {
        Safelist safelist = Safelist.simpleText();

        Attributes enforcedAttributes = safelist.getEnforcedAttributes("b");
        assertTrue(enforcedAttributes.isEmpty());

        enforcedAttributes = safelist.getEnforcedAttributes("em");
        assertTrue(enforcedAttributes.isEmpty());

        enforcedAttributes = safelist.getEnforcedAttributes("i");
        assertTrue(enforcedAttributes.isEmpty());

        enforcedAttributes = safelist.getEnforcedAttributes("strong");
        assertTrue(enforcedAttributes.isEmpty());

        enforcedAttributes = safelist.getEnforcedAttributes("u");
        assertTrue(enforcedAttributes.isEmpty());
    }
}
