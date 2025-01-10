
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
    public void testAddEnforcedAttribute_validTagAttributeAndValue() {
        safelist.addTags("a");
        safelist.addAttributes("a", "href");
        safelist.addEnforcedAttribute("a", "href", "nofollow");

        Attributes enforcedAttributes = safelist.getEnforcedAttributes("a");
        assertEquals("nofollow", enforcedAttributes.get("href"));
    }

    @Test
    public void testAddEnforcedAttribute_invalidTag() {
        safelist.addEnforcedAttribute("invalidTag", "href", "nofollow");

        Attributes enforcedAttributes = safelist.getEnforcedAttributes("invalidTag");
        assertNull(enforcedAttributes.get("href"));
    }

    @Test
    public void testAddEnforcedAttribute_invalidAttribute() {
        safelist.addTags("a");
        safelist.addEnforcedAttribute("a", "invalidAttribute", "nofollow");

        Attributes enforcedAttributes = safelist.getEnforcedAttributes("a");
        assertNull(enforcedAttributes.get("invalidAttribute"));
    }

    @Test
    public void testAddEnforcedAttribute_validTagAttributeAndValue_isSafeAttribute() {
        safelist.addTags("a");
        safelist.addAttributes("a", "href");
        safelist.addEnforcedAttribute("a", "href", "nofollow");

        Attributes attributes = new Attributes();
        Attribute attribute = new Attribute("href", "nofollow");
        attributes.put(attribute);
        Element element = new Element(Tag.valueOf("a"), "", attributes);

        assertTrue(safelist.isSafeAttribute("a", element, attribute));
    }

    @Test
    public void testAddEnforcedAttribute_validTagAttributeAndValue_isNotSafeAttribute() {
        safelist.addTags("a");
        safelist.addAttributes("a", "href");
        safelist.addEnforcedAttribute("a", "href", "nofollow");

        Attributes attributes = new Attributes();
        Attribute attribute = new Attribute("href", "invalidValue");
        attributes.put(attribute);
        Element element = new Element(Tag.valueOf("a"), "", attributes);

        assertFalse(safelist.isSafeAttribute("a", element, attribute));
    }
}
