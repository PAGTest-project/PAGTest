
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
    public void testRemoveEnforcedAttribute_ExistingAttribute() {
        safelist.addEnforcedAttribute("a", "rel", "nofollow");
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
    public void testRemoveEnforcedAttribute_EmptyMapAfterRemoval() {
        safelist.addEnforcedAttribute("a", "rel", "nofollow");
        safelist.removeEnforcedAttribute("a", "rel");

        assertFalse(safelist.getEnforcedAttributes("a").hasKey("rel"));
    }

    @Test
    public void testRemoveEnforcedAttribute_NonExistingTag() {
        safelist.removeEnforcedAttribute("b", "rel");

        Attributes enforcedAttributes = safelist.getEnforcedAttributes("b");
        assertFalse(enforcedAttributes.hasKey("rel"));
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
}
