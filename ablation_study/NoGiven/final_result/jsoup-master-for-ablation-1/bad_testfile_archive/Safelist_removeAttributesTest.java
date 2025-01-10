
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
        safelist.addTags("a");
        safelist.addAttributes("a", "href", "class");
        safelist.removeAttributes("a", "href");

        Attributes attributes = new Attributes();
        Attribute hrefAttribute = new Attribute("href", "http://example.com");
        Attribute classAttribute = new Attribute("class", "example");
        attributes.put(hrefAttribute);
        attributes.put(classAttribute);
        Element element = new Element(Tag.valueOf("a"), "", attributes);

        assertFalse(safelist.isSafeAttribute("a", element, hrefAttribute));
        assertTrue(safelist.isSafeAttribute("a", element, classAttribute));
    }

    @Test
    public void testRemoveAttributes_AllTagsSingleAttribute() {
        safelist.addTags("a", "div");
        safelist.addAttributes("a", "href", "class");
        safelist.addAttributes("div", "id", "class");
        safelist.removeAttributes(":all", "class");

        Attributes aAttributes = new Attributes();
        Attribute aHrefAttribute = new Attribute("href", "http://example.com");
        Attribute aClassAttribute = new Attribute("class", "example");
        aAttributes.put(aHrefAttribute);
        aAttributes.put(aClassAttribute);
        Element aElement = new Element(Tag.valueOf("a"), "", aAttributes);

        Attributes divAttributes = new Attributes();
        Attribute divIdAttribute = new Attribute("id", "example");
        Attribute divClassAttribute = new Attribute("class", "example");
        divAttributes.put(divIdAttribute);
        divAttributes.put(divClassAttribute);
        Element divElement = new Element(Tag.valueOf("div"), "", divAttributes);

        assertTrue(safelist.isSafeAttribute("a", aElement, aHrefAttribute));
        assertFalse(safelist.isSafeAttribute("a", aElement, aClassAttribute));
        assertTrue(safelist.isSafeAttribute("div", divElement, divIdAttribute));
        assertFalse(safelist.isSafeAttribute("div", divElement, divClassAttribute));
    }

    @Test
    public void testRemoveAttributes_TagNotAllowed() {
        safelist.addTags("a");
        safelist.addAttributes("a", "href", "class");
        safelist.removeAttributes("div", "id");

        Attributes attributes = new Attributes();
        Attribute idAttribute = new Attribute("id", "example");
        attributes.put(idAttribute);
        Element element = new Element(Tag.valueOf("div"), "", attributes);

        assertTrue(safelist.isSafeAttribute("div", element, idAttribute));
    }

    @Test
    public void testRemoveAttributes_NoAttributesLeft() {
        safelist.addTags("a");
        safelist.addAttributes("a", "href");
        safelist.removeAttributes("a", "href");

        Attributes attributes = new Attributes();
        Attribute hrefAttribute = new Attribute("href", "http://example.com");
        attributes.put(hrefAttribute);
        Element element = new Element(Tag.valueOf("a"), "", attributes);

        assertFalse(safelist.isSafeAttribute("a", element, hrefAttribute));
        assertFalse(safelist.attributes.containsKey(TagName.valueOf("a")));
    }
}
