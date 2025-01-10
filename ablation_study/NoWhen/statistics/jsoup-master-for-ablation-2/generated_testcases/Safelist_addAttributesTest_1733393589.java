
package org.jsoup.safety;

import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Safelist_addAttributesTest {
    private Safelist safelist;

    @BeforeEach
    public void setUp() {
        safelist = new Safelist();
    }

    @Test
    public void testAddAttributes_ValidAttributes() {
        safelist.addAttributes("a", "href", "class");
        assertTrue(safelist.isSafeAttribute("a", new Element(Tag.valueOf("a"), ""), new Attribute("href", "")));
        assertTrue(safelist.isSafeAttribute("a", new Element(Tag.valueOf("a"), ""), new Attribute("class", "")));
    }

    @Test
    public void testAddAttributes_InvalidAttributes() {
        safelist.addAttributes("a", "href", "class");
        assertFalse(safelist.isSafeAttribute("a", new Element(Tag.valueOf("a"), ""), new Attribute("style", "")));
    }

    @Test
    public void testAddAttributes_NoAttributesSupplied() {
        assertThrows(IllegalArgumentException.class, () -> {
            safelist.addAttributes("a");
        });
    }

    @Test
    public void testAddAttributes_EmptyTag() {
        assertThrows(IllegalArgumentException.class, () -> {
            safelist.addAttributes("", "href");
        });
    }

    @Test
    public void testAddAttributes_NullAttributes() {
        assertThrows(IllegalArgumentException.class, () -> {
            safelist.addAttributes("a", (String[]) null);
        });
    }

    @Test
    public void testAddAttributes_EmptyAttribute() {
        assertThrows(IllegalArgumentException.class, () -> {
            safelist.addAttributes("a", "href", "");
        });
    }

    @Test
    public void testAddAttributes_AllTag() {
        safelist.addAttributes(":all", "class");
        assertTrue(safelist.isSafeAttribute("a", new Element(Tag.valueOf("a"), ""), new Attribute("class", "")));
        assertTrue(safelist.isSafeAttribute("div", new Element(Tag.valueOf("div"), ""), new Attribute("class", "")));
    }

    @Test
    public void testAddAttributes_EnforcedAttributes() {
        safelist.addAttributes("a", "href", "class");
        safelist.addEnforcedAttribute("a", "rel", "nofollow");
        Attributes enforcedAttributes = safelist.getEnforcedAttributes("a");
        assertEquals("nofollow", enforcedAttributes.get("rel"));
    }
}
