
package org.jsoup.safety;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Safelist_addEnforcedAttributeTest {
    private Safelist safelist;

    @BeforeEach
    public void setUp() {
        safelist = new Safelist();
    }

    @Test
    public void testAddEnforcedAttribute_ValidInput() {
        safelist.addEnforcedAttribute("a", "rel", "nofollow");
        Attributes enforcedAttributes = safelist.getEnforcedAttributes("a");
        assertEquals("nofollow", enforcedAttributes.get("rel"));
    }

    @Test
    public void testAddEnforcedAttribute_EmptyTag() {
        assertThrows(IllegalArgumentException.class, () -> {
            safelist.addEnforcedAttribute("", "rel", "nofollow");
        });
    }

    @Test
    public void testAddEnforcedAttribute_EmptyAttribute() {
        assertThrows(IllegalArgumentException.class, () -> {
            safelist.addEnforcedAttribute("a", "", "nofollow");
        });
    }

    @Test
    public void testAddEnforcedAttribute_EmptyValue() {
        assertThrows(IllegalArgumentException.class, () -> {
            safelist.addEnforcedAttribute("a", "rel", "");
        });
    }

    @Test
    public void testAddEnforcedAttribute_TagNotAllowed() {
        safelist.addEnforcedAttribute("a", "rel", "nofollow");
        assertFalse(safelist.isSafeTag("div"));
    }

    @Test
    public void testAddEnforcedAttribute_AttributeNotAllowed() {
        safelist.addEnforcedAttribute("a", "rel", "nofollow");
        assertFalse(safelist.isSafeAttribute("a", new Element(Tag.valueOf("a"), ""), new Attribute("href", "http://example.com")));
    }

    @Test
    public void testAddEnforcedAttribute_MultipleAttributes() {
        safelist.addEnforcedAttribute("a", "rel", "nofollow");
        safelist.addEnforcedAttribute("a", "target", "_blank");
        Attributes enforcedAttributes = safelist.getEnforcedAttributes("a");
        assertEquals("nofollow", enforcedAttributes.get("rel"));
        assertEquals("_blank", enforcedAttributes.get("target"));
    }

    @Test
    public void testAddEnforcedAttribute_OverrideExisting() {
        safelist.addEnforcedAttribute("a", "rel", "nofollow");
        safelist.addEnforcedAttribute("a", "rel", "noopener");
        Attributes enforcedAttributes = safelist.getEnforcedAttributes("a");
        assertEquals("noopener", enforcedAttributes.get("rel"));
    }

    @Test
    public void testAddEnforcedAttribute_RemoveEnforcedAttribute() {
        safelist.addEnforcedAttribute("a", "rel", "nofollow");
        safelist.removeEnforcedAttribute("a", "rel");
        Attributes enforcedAttributes = safelist.getEnforcedAttributes("a");
        assertNull(enforcedAttributes.get("rel"));
    }
}
