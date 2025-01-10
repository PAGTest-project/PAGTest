
package org.jsoup.safety;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Safelist_removeEnforcedAttributeTest {
    private Safelist safelist;

    @BeforeEach
    public void setUp() {
        safelist = new Safelist();
    }

    @Test
    public void testRemoveEnforcedAttribute_AttributeRemoved() {
        safelist.addEnforcedAttribute("a", "rel", "nofollow");
        safelist.removeEnforcedAttribute("a", "rel");
        Attributes enforcedAttributes = safelist.getEnforcedAttributes("a");
        assertFalse(enforcedAttributes.hasKey("rel"));
    }

    @Test
    public void testRemoveEnforcedAttribute_TagRemovedWhenNoAttributesLeft() {
        safelist.addEnforcedAttribute("a", "rel", "nofollow");
        safelist.removeEnforcedAttribute("a", "rel");
        assertFalse(safelist.getEnforcedAttributes("a").hasKey("rel"));
    }

    @Test
    public void testRemoveEnforcedAttribute_TagNotRemovedWhenOtherAttributesExist() {
        safelist.addEnforcedAttribute("a", "rel", "nofollow");
        safelist.addEnforcedAttribute("a", "href", "http://example.com");
        safelist.removeEnforcedAttribute("a", "rel");
        assertTrue(safelist.getEnforcedAttributes("a").hasKey("href"));
    }

    @Test
    public void testRemoveEnforcedAttribute_NoEffectWhenTagNotAllowed() {
        safelist.removeEnforcedAttribute("invalidTag", "rel");
        assertFalse(safelist.getEnforcedAttributes("invalidTag").hasKey("rel"));
    }

    @Test
    public void testRemoveEnforcedAttribute_NoEffectWhenAttributeNotEnforced() {
        safelist.addEnforcedAttribute("a", "rel", "nofollow");
        safelist.removeEnforcedAttribute("a", "invalidAttribute");
        assertTrue(safelist.getEnforcedAttributes("a").hasKey("rel"));
    }
}
