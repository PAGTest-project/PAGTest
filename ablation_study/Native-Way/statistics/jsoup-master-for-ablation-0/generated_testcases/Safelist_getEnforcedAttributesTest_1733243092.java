
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
        assertEquals(1, attrs.size());
        assertEquals("nofollow", attrs.get("rel"));
    }

    @Test
    public void testGetEnforcedAttributes_multipleEnforcedAttributes() {
        safelist.addEnforcedAttribute("a", "rel", "nofollow");
        safelist.addEnforcedAttribute("a", "target", "_blank");
        Attributes attrs = safelist.getEnforcedAttributes("a");
        assertEquals(2, attrs.size());
        assertEquals("nofollow", attrs.get("rel"));
        assertEquals("_blank", attrs.get("target"));
    }

    @Test
    public void testGetEnforcedAttributes_differentTags() {
        safelist.addEnforcedAttribute("a", "rel", "nofollow");
        safelist.addEnforcedAttribute("img", "alt", "image");
        Attributes attrsA = safelist.getEnforcedAttributes("a");
        Attributes attrsImg = safelist.getEnforcedAttributes("img");
        assertEquals(1, attrsA.size());
        assertEquals("nofollow", attrsA.get("rel"));
        assertEquals(1, attrsImg.size());
        assertEquals("image", attrsImg.get("alt"));
    }

    @Test
    public void testGetEnforcedAttributes_removeEnforcedAttribute() {
        safelist.addEnforcedAttribute("a", "rel", "nofollow");
        safelist.removeEnforcedAttribute("a", "rel");
        Attributes attrs = safelist.getEnforcedAttributes("a");
        assertTrue(attrs.isEmpty());
    }
}
