
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
    public void testGetEnforcedAttributes_noEnforcedAttributes() {
        Attributes attrs = safelist.getEnforcedAttributes("a");
        assertTrue(attrs.isEmpty());
    }

    @Test
    public void testGetEnforcedAttributes_withEnforcedAttributes() {
        safelist.addEnforcedAttribute("a", "rel", "nofollow");
        Attributes attrs = safelist.getEnforcedAttributes("a");
        assertEquals("nofollow", attrs.get("rel"));
    }

    @Test
    public void testGetEnforcedAttributes_multipleEnforcedAttributes() {
        safelist.addEnforcedAttribute("a", "rel", "nofollow");
        safelist.addEnforcedAttribute("a", "target", "_blank");
        Attributes attrs = safelist.getEnforcedAttributes("a");
        assertEquals("nofollow", attrs.get("rel"));
        assertEquals("_blank", attrs.get("target"));
    }

    @Test
    public void testGetEnforcedAttributes_removeEnforcedAttribute() {
        safelist.addEnforcedAttribute("a", "rel", "nofollow");
        safelist.removeEnforcedAttribute("a", "rel");
        Attributes attrs = safelist.getEnforcedAttributes("a");
        assertNull(attrs.get("rel"));
    }

    @Test
    public void testGetEnforcedAttributes_differentTag() {
        safelist.addEnforcedAttribute("a", "rel", "nofollow");
        Attributes attrs = safelist.getEnforcedAttributes("img");
        assertTrue(attrs.isEmpty());
    }
}
