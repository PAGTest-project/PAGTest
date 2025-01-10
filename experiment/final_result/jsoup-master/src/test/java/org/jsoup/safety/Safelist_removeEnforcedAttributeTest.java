
package org.jsoup.safety;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Safelist_removeEnforcedAttributeTest {
    private Safelist safelist;

    @BeforeEach
    public void setUp() {
        safelist = new Safelist();
        safelist.addTags("a");
        safelist.addEnforcedAttribute("a", "href", "http://example.com");
    }

    @Test
    public void testRemoveEnforcedAttribute_ExistingAttribute() {
        safelist.removeEnforcedAttribute("a", "href");
        assertTrue(safelist.getEnforcedAttributes("a").isEmpty());
    }

    @Test
    public void testRemoveEnforcedAttribute_NonExistingAttribute() {
        safelist.removeEnforcedAttribute("a", "title");
        assertFalse(safelist.getEnforcedAttributes("a").isEmpty());
    }

    @Test
    public void testRemoveEnforcedAttribute_NonExistingTag() {
        safelist.removeEnforcedAttribute("div", "class");
        assertFalse(safelist.getEnforcedAttributes("a").isEmpty());
    }

    @Test
    public void testRemoveEnforcedAttribute_EmptyTagAndAttribute() {
        assertThrows(IllegalArgumentException.class, () -> {
            safelist.removeEnforcedAttribute("", "");
        });
    }

    @Test
    public void testRemoveEnforcedAttribute_NullTagAndAttribute() {
        assertThrows(IllegalArgumentException.class, () -> {
            safelist.removeEnforcedAttribute(null, null);
        });
    }
}
