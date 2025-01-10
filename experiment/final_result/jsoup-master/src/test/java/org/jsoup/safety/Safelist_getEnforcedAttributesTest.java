
package org.jsoup.safety;

import org.jsoup.nodes.Attributes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Safelist_getEnforcedAttributesTest {
    private Safelist safelist;

    @BeforeEach
    public void setUp() {
        safelist = new Safelist();
    }

    @Test
    public void testGetEnforcedAttributes_withEnforcedAttributes() {
        safelist.addEnforcedAttribute("a", "rel", "nofollow");
        Attributes enforcedAttrs = safelist.getEnforcedAttributes("a");
        assertEquals("nofollow", enforcedAttrs.get("rel"));
    }

    @Test
    public void testGetEnforcedAttributes_withoutEnforcedAttributes() {
        Attributes enforcedAttrs = safelist.getEnforcedAttributes("a");
        assertTrue(enforcedAttrs.isEmpty());
    }

    @Test
    public void testGetEnforcedAttributes_afterRemovingEnforcedAttribute() {
        safelist.addEnforcedAttribute("a", "rel", "nofollow");
        safelist.removeEnforcedAttribute("a", "rel");
        Attributes enforcedAttrs = safelist.getEnforcedAttributes("a");
        assertFalse(enforcedAttrs.hasKey("rel"));
    }
}
