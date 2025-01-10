
package org.jsoup.safety;

import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
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
    public void testAddEnforcedAttribute_MultipleAttributes() {
        safelist.addEnforcedAttribute("a", "rel", "nofollow");
        safelist.addEnforcedAttribute("a", "target", "_blank");
        Attributes enforcedAttributes = safelist.getEnforcedAttributes("a");
        assertEquals("nofollow", enforcedAttributes.get("rel"));
        assertEquals("_blank", enforcedAttributes.get("target"));
    }

    @Test
    public void testAddEnforcedAttribute_OverwriteExisting() {
        safelist.addEnforcedAttribute("a", "rel", "nofollow");
        safelist.addEnforcedAttribute("a", "rel", "noopener");
        Attributes enforcedAttributes = safelist.getEnforcedAttributes("a");
        assertEquals("noopener", enforcedAttributes.get("rel"));
    }

    @Test
    public void testAddEnforcedAttribute_TagNotAllowed() {
        safelist.addEnforcedAttribute("invalidTag", "rel", "nofollow");
        assertTrue(safelist.isSafeTag("invalidTag"));
    }

    @Test
    public void testAddEnforcedAttribute_AttributeNotAllowed() {
        safelist.addEnforcedAttribute("a", "invalidAttr", "value");
        Attributes enforcedAttributes = safelist.getEnforcedAttributes("a");
        assertEquals("value", enforcedAttributes.get("invalidAttr"));
    }

    @Test
    public void testAddEnforcedAttribute_CheckSafety() {
        safelist.addEnforcedAttribute("a", "rel", "nofollow");
        Attribute attr = new Attribute("rel", "nofollow");
        assertTrue(safelist.isSafeAttribute("a", null, attr));
    }
}
