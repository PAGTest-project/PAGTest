
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
    public void testGetEnforcedAttributes_noAttributes() {
        Attributes attrs = safelist.getEnforcedAttributes("a");
        assertTrue(attrs.isEmpty());
    }

    @Test
    public void testGetEnforcedAttributes_withAttributes() {
        safelist.addEnforcedAttribute("a", "rel", "nofollow");
        Attributes attrs = safelist.getEnforcedAttributes("a");
        assertEquals("nofollow", attrs.get("rel"));
    }

    @Test
    public void testGetEnforcedAttributes_multipleAttributes() {
        safelist.addEnforcedAttribute("a", "rel", "nofollow");
        safelist.addEnforcedAttribute("a", "target", "_blank");
        Attributes attrs = safelist.getEnforcedAttributes("a");
        assertEquals("nofollow", attrs.get("rel"));
        assertEquals("_blank", attrs.get("target"));
    }

    @Test
    public void testGetEnforcedAttributes_removeAttributes() {
        safelist.addEnforcedAttribute("a", "rel", "nofollow");
        safelist.removeEnforcedAttribute("a", "rel");
        Attributes attrs = safelist.getEnforcedAttributes("a");
        assertFalse(attrs.hasKey("rel"));
    }

    @Test
    public void testGetEnforcedAttributes_differentTags() {
        safelist.addEnforcedAttribute("a", "rel", "nofollow");
        safelist.addEnforcedAttribute("img", "alt", "image");
        Attributes attrsA = safelist.getEnforcedAttributes("a");
        Attributes attrsImg = safelist.getEnforcedAttributes("img");
        assertEquals("nofollow", attrsA.get("rel"));
        assertEquals("image", attrsImg.get("alt"));
    }
}
