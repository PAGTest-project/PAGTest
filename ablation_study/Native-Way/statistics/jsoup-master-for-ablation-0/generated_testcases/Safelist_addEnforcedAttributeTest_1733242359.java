
package org.jsoup.safety;

import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;
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
    public void testAddEnforcedAttribute_validInput() {
        safelist.addEnforcedAttribute("a", "rel", "nofollow");
        Attributes enforcedAttributes = safelist.getEnforcedAttributes("a");
        assertEquals("nofollow", enforcedAttributes.get("rel"));
    }

    @Test
    public void testAddEnforcedAttribute_invalidTag() {
        assertThrows(IllegalArgumentException.class, () -> {
            safelist.addEnforcedAttribute("", "rel", "nofollow");
        });
    }

    @Test
    public void testAddEnforcedAttribute_invalidAttribute() {
        assertThrows(IllegalArgumentException.class, () -> {
            safelist.addEnforcedAttribute("a", "", "nofollow");
        });
    }

    @Test
    public void testAddEnforcedAttribute_invalidValue() {
        assertThrows(IllegalArgumentException.class, () -> {
            safelist.addEnforcedAttribute("a", "rel", "");
        });
    }

    @Test
    public void testAddEnforcedAttribute_multipleAttributes() {
        safelist.addEnforcedAttribute("a", "rel", "nofollow");
        safelist.addEnforcedAttribute("a", "target", "_blank");
        Attributes enforcedAttributes = safelist.getEnforcedAttributes("a");
        assertEquals("nofollow", enforcedAttributes.get("rel"));
        assertEquals("_blank", enforcedAttributes.get("target"));
    }

    @Test
    public void testAddEnforcedAttribute_existingAttribute() {
        safelist.addEnforcedAttribute("a", "rel", "nofollow");
        safelist.addEnforcedAttribute("a", "rel", "noopener");
        Attributes enforcedAttributes = safelist.getEnforcedAttributes("a");
        assertEquals("noopener", enforcedAttributes.get("rel"));
    }

    @Test
    public void testAddEnforcedAttribute_isSafeAttribute() {
        safelist.addEnforcedAttribute("a", "rel", "nofollow");
        Attributes attributes = new Attributes();
        Attribute attribute = new Attribute("rel", "nofollow");
        attributes.put(attribute);
        Element element = new Element(Tag.valueOf("a"), "", attributes);
        assertTrue(safelist.isSafeAttribute("a", element, attribute));
    }

    @Test
    public void testAddEnforcedAttribute_isNotSafeAttribute() {
        safelist.addEnforcedAttribute("a", "rel", "nofollow");
        Attributes attributes = new Attributes();
        Attribute attribute = new Attribute("rel", "noopener");
        attributes.put(attribute);
        Element element = new Element(Tag.valueOf("a"), "", attributes);
        assertFalse(safelist.isSafeAttribute("a", element, attribute));
    }
}
