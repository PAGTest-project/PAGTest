
package org.jsoup.safety;

import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;
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
    public void testRemoveEnforcedAttribute_ExistingTagAndAttribute() {
        safelist.addEnforcedAttribute("a", "rel", "nofollow");
        safelist.removeEnforcedAttribute("a", "rel");

        Attributes enforcedAttributes = safelist.getEnforcedAttributes("a");
        assertFalse(enforcedAttributes.hasKey("rel"));
    }

    @Test
    public void testRemoveEnforcedAttribute_NonExistingTag() {
        safelist.removeEnforcedAttribute("a", "rel");

        Attributes enforcedAttributes = safelist.getEnforcedAttributes("a");
        assertFalse(enforcedAttributes.hasKey("rel"));
    }

    @Test
    public void testRemoveEnforcedAttribute_NonExistingAttribute() {
        safelist.addEnforcedAttribute("a", "rel", "nofollow");
        safelist.removeEnforcedAttribute("a", "href");

        Attributes enforcedAttributes = safelist.getEnforcedAttributes("a");
        assertTrue(enforcedAttributes.hasKey("rel"));
        assertFalse(enforcedAttributes.hasKey("href"));
    }

    @Test
    public void testRemoveEnforcedAttribute_EmptyEnforcedAttributes() {
        safelist.addEnforcedAttribute("a", "rel", "nofollow");
        safelist.removeEnforcedAttribute("a", "rel");

        assertFalse(safelist.enforcedAttributes.containsKey(TagName.valueOf("a")));
    }

    @Test
    public void testRemoveEnforcedAttribute_MultipleAttributes() {
        safelist.addEnforcedAttribute("a", "rel", "nofollow");
        safelist.addEnforcedAttribute("a", "target", "_blank");
        safelist.removeEnforcedAttribute("a", "rel");

        Attributes enforcedAttributes = safelist.getEnforcedAttributes("a");
        assertFalse(enforcedAttributes.hasKey("rel"));
        assertTrue(enforcedAttributes.hasKey("target"));
    }

    @Test
    public void testRemoveEnforcedAttribute_AllAttributesRemoved() {
        safelist.addEnforcedAttribute("a", "rel", "nofollow");
        safelist.addEnforcedAttribute("a", "target", "_blank");
        safelist.removeEnforcedAttribute("a", "rel");
        safelist.removeEnforcedAttribute("a", "target");

        assertFalse(safelist.enforcedAttributes.containsKey(TagName.valueOf("a")));
    }
}
