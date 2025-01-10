
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
        Attributes attributes = new Attributes();
        Attribute hrefAttribute = new Attribute("href", "http://example.com");
        Attribute classAttribute = new Attribute("class", "btn");
        attributes.put(hrefAttribute);
        attributes.put(classAttribute);
        Element element = new Element(Tag.valueOf("a"), "", attributes);

        assertTrue(safelist.isSafeAttribute("a", element, hrefAttribute));
        assertTrue(safelist.isSafeAttribute("a", element, classAttribute));
    }

    @Test
    public void testAddAttributes_InvalidAttributes() {
        safelist.addAttributes("a", "href", "class");
        Attributes attributes = new Attributes();
        Attribute invalidAttribute = new Attribute("invalid", "value");
        attributes.put(invalidAttribute);
        Element element = new Element(Tag.valueOf("a"), "", attributes);

        assertFalse(safelist.isSafeAttribute("a", element, invalidAttribute));
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
    public void testAddAttributes_RemoveAttributes() {
        safelist.addAttributes("a", "href", "class");
        safelist.removeAttributes("a", "class");
        Attributes attributes = new Attributes();
        Attribute hrefAttribute = new Attribute("href", "http://example.com");
        Attribute classAttribute = new Attribute("class", "btn");
        attributes.put(hrefAttribute);
        attributes.put(classAttribute);
        Element element = new Element(Tag.valueOf("a"), "", attributes);

        assertTrue(safelist.isSafeAttribute("a", element, hrefAttribute));
        assertFalse(safelist.isSafeAttribute("a", element, classAttribute));
    }

    @Test
    public void testAddAttributes_EnforcedAttributes() {
        safelist.addAttributes("a", "href", "class");
        safelist.addEnforcedAttribute("a", "rel", "nofollow");
        Attributes attributes = new Attributes();
        Attribute hrefAttribute = new Attribute("href", "http://example.com");
        Attribute classAttribute = new Attribute("class", "btn");
        attributes.put(hrefAttribute);
        attributes.put(classAttribute);
        Element element = new Element(Tag.valueOf("a"), "", attributes);

        assertTrue(safelist.isSafeAttribute("a", element, hrefAttribute));
        assertTrue(safelist.isSafeAttribute("a", element, classAttribute));
        Attributes enforcedAttributes = safelist.getEnforcedAttributes("a");
        assertEquals("nofollow", enforcedAttributes.get("rel"));
    }
}
