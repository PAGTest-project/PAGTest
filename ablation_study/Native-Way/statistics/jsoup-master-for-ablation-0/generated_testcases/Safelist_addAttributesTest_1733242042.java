
package org.jsoup.safety;

import org.jsoup.helper.Validate;
import org.jsoup.internal.Functions;
import org.jsoup.internal.Normalizer;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Tag;
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
        assertTrue(safelist.isSafeAttribute("a", new Element(Tag.valueOf("a"), ""), new Attribute("href", "http://example.com")));
        assertTrue(safelist.isSafeAttribute("a", new Element(Tag.valueOf("a"), ""), new Attribute("class", "example")));
    }

    @Test
    public void testAddAttributes_EmptyTag() {
        assertThrows(IllegalArgumentException.class, () -> safelist.addAttributes("", "href", "class"));
    }

    @Test
    public void testAddAttributes_NullAttributes() {
        assertThrows(IllegalArgumentException.class, () -> safelist.addAttributes("a", (String[]) null));
    }

    @Test
    public void testAddAttributes_NoAttributesSupplied() {
        assertThrows(IllegalArgumentException.class, () -> safelist.addAttributes("a"));
    }

    @Test
    public void testAddAttributes_EmptyAttribute() {
        assertThrows(IllegalArgumentException.class, () -> safelist.addAttributes("a", "href", ""));
    }

    @Test
    public void testAddAttributes_AllTag() {
        safelist.addAttributes(":all", "class");
        assertTrue(safelist.isSafeAttribute("a", new Element(Tag.valueOf("a"), ""), new Attribute("class", "example")));
        assertTrue(safelist.isSafeAttribute("div", new Element(Tag.valueOf("div"), ""), new Attribute("class", "example")));
    }

    @Test
    public void testAddAttributes_TagNotAllowed() {
        safelist.addAttributes("a", "href", "class");
        assertFalse(safelist.isSafeAttribute("div", new Element(Tag.valueOf("div"), ""), new Attribute("href", "http://example.com")));
    }

    @Test
    public void testAddAttributes_AttributeNotAllowed() {
        safelist.addAttributes("a", "href");
        assertFalse(safelist.isSafeAttribute("a", new Element(Tag.valueOf("a"), ""), new Attribute("class", "example")));
    }
}
