
package org.jsoup.safety;

import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Safelist_removeAttributesTest {
    private Safelist safelist;

    @BeforeEach
    public void setUp() {
        safelist = new Safelist();
    }

    @Test
    public void testRemoveAttributes_SingleTagSingleAttribute() {
        safelist.addAttributes("a", "href", "class");
        safelist.removeAttributes("a", "href");

        assertFalse(safelist.isSafeAttribute("a", new Element(Tag.valueOf("a"), ""), new Attribute("href", "")));
        assertTrue(safelist.isSafeAttribute("a", new Element(Tag.valueOf("a"), ""), new Attribute("class", "")));
    }

    @Test
    public void testRemoveAttributes_SingleTagMultipleAttributes() {
        safelist.addAttributes("a", "href", "class", "id");
        safelist.removeAttributes("a", "href", "class");

        assertFalse(safelist.isSafeAttribute("a", new Element(Tag.valueOf("a"), ""), new Attribute("href", "")));
        assertFalse(safelist.isSafeAttribute("a", new Element(Tag.valueOf("a"), ""), new Attribute("class", "")));
        assertTrue(safelist.isSafeAttribute("a", new Element(Tag.valueOf("a"), ""), new Attribute("id", "")));
    }

    @Test
    public void testRemoveAttributes_AllTagsSingleAttribute() {
        safelist.addAttributes("a", "href");
        safelist.addAttributes("img", "src");
        safelist.removeAttributes(":all", "href");

        assertFalse(safelist.isSafeAttribute("a", new Element(Tag.valueOf("a"), ""), new Attribute("href", "")));
        assertTrue(safelist.isSafeAttribute("img", new Element(Tag.valueOf("img"), ""), new Attribute("src", "")));
    }

    @Test
    public void testRemoveAttributes_AllTagsMultipleAttributes() {
        safelist.addAttributes("a", "href", "class");
        safelist.addAttributes("img", "src", "class");
        safelist.removeAttributes(":all", "href", "class");

        assertFalse(safelist.isSafeAttribute("a", new Element(Tag.valueOf("a"), ""), new Attribute("href", "")));
        assertFalse(safelist.isSafeAttribute("a", new Element(Tag.valueOf("a"), ""), new Attribute("class", "")));
        assertFalse(safelist.isSafeAttribute("img", new Element(Tag.valueOf("img"), ""), new Attribute("src", "")));
        assertFalse(safelist.isSafeAttribute("img", new Element(Tag.valueOf("img"), ""), new Attribute("class", "")));
    }

    @Test
    public void testRemoveAttributes_TagNotAllowed() {
        safelist.removeAttributes("a", "href");

        assertFalse(safelist.isSafeAttribute("a", new Element(Tag.valueOf("a"), ""), new Attribute("href", "")));
    }

    @Test
    public void testRemoveAttributes_AttributeNotAllowed() {
        safelist.addAttributes("a", "href");
        safelist.removeAttributes("a", "class");

        assertTrue(safelist.isSafeAttribute("a", new Element(Tag.valueOf("a"), ""), new Attribute("href", "")));
        assertFalse(safelist.isSafeAttribute("a", new Element(Tag.valueOf("a"), ""), new Attribute("class", "")));
    }

    @Test
    public void testRemoveAttributes_EnforcedAttributeInteraction() {
        safelist.addAttributes("a", "href");
        safelist.addEnforcedAttribute("a", "href", "nofollow");
        safelist.removeAttributes("a", "href");

        assertFalse(safelist.isSafeAttribute("a", new Element(Tag.valueOf("a"), ""), new Attribute("href", "")));
    }
}
