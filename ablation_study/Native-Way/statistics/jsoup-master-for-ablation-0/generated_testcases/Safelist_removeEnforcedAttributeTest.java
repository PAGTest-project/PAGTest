
package org.jsoup.safety;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Safelist_removeEnforcedAttributeTest {
    private Safelist safelist;

    @BeforeEach
    public void setUp() {
        safelist = new Safelist();
        safelist.addTags("a", "img");
        safelist.addAttributes("a", "href", "class");
        safelist.addAttributes("img", "src", "alt");
        safelist.addEnforcedAttribute("a", "rel", "nofollow");
        safelist.addEnforcedAttribute("img", "alt", "image");
    }

    @Test
    public void testRemoveEnforcedAttribute_existingTagAndAttribute() {
        safelist.removeEnforcedAttribute("a", "rel");
        assertFalse(safelist.getEnforcedAttributes("a").hasKey("rel"));
    }

    @Test
    public void testRemoveEnforcedAttribute_existingTagNoAttributesLeft() {
        safelist.removeEnforcedAttribute("img", "alt");
        assertFalse(safelist.getEnforcedAttributes("img").hasKey("alt"));
        assertTrue(safelist.getEnforcedAttributes("img").isEmpty());
    }

    @Test
    public void testRemoveEnforcedAttribute_nonExistingTag() {
        safelist.removeEnforcedAttribute("nonExistingTag", "rel");
        assertFalse(safelist.getEnforcedAttributes("nonExistingTag").hasKey("rel"));
    }

    @Test
    public void testRemoveEnforcedAttribute_nonExistingAttribute() {
        safelist.removeEnforcedAttribute("a", "nonExistingAttribute");
        assertFalse(safelist.getEnforcedAttributes("a").hasKey("nonExistingAttribute"));
    }

    @Test
    public void testRemoveEnforcedAttribute_allAttributesRemoved() {
        safelist.removeEnforcedAttribute("a", "rel");
        safelist.removeEnforcedAttribute("a", "class");
        assertTrue(safelist.getEnforcedAttributes("a").isEmpty());
    }
}
