
package org.jsoup.safety;

import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Element;
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
    public void testRemoveAttributes_TagNotAllowed() {
        safelist.removeAttributes("div", "class", "id");
        assertFalse(safelist.isSafeTag("div"));
    }

    @Test
    public void testRemoveAttributes_TagAllowed_AttributesRemoved() {
        safelist.addAttributes("div", "class", "id");
        safelist.removeAttributes("div", "class");
        assertTrue(safelist.isSafeTag("div"));
        assertFalse(safelist.isSafeAttribute("div", new Element(Tag.valueOf("div"), ""), new Attribute("class", "")));
    }

    @Test
    public void testRemoveAttributes_AllTags_AttributesRemoved() {
        safelist.addAttributes("div", "class", "id");
        safelist.addAttributes("span", "class", "id");
        safelist.removeAttributes(":all", "class");
        assertFalse(safelist.isSafeAttribute("div", new Element(Tag.valueOf("div"), ""), new Attribute("class", "")));
        assertFalse(safelist.isSafeAttribute("span", new Element(Tag.valueOf("span"), ""), new Attribute("class", "")));
    }

    @Test
    public void testRemoveAttributes_TagAllowed_NoAttributesLeft() {
        safelist.addAttributes("div", "class");
        safelist.removeAttributes("div", "class");
        assertTrue(safelist.isSafeTag("div"));
        assertFalse(safelist.isSafeAttribute("div", new Element(Tag.valueOf("div"), ""), new Attribute("class", "")));
        assertNull(safelist.attributes.get(TagName.valueOf("div")));
    }

    @Test
    public void testRemoveAttributes_AllTags_NoAttributesLeft() {
        safelist.addAttributes("div", "class");
        safelist.addAttributes("span", "class");
        safelist.removeAttributes(":all", "class");
        assertFalse(safelist.isSafeAttribute("div", new Element(Tag.valueOf("div"), ""), new Attribute("class", "")));
        assertFalse(safelist.isSafeAttribute("span", new Element(Tag.valueOf("span"), ""), new Attribute("class", "")));
        assertNull(safelist.attributes.get(TagName.valueOf("div")));
        assertNull(safelist.attributes.get(TagName.valueOf("span")));
    }
}
