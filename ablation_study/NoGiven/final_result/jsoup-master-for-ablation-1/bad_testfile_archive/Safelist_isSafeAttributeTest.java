
package org.jsoup.safety;

import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Safelist_isSafeAttributeTest {
    private Safelist safelist;

    @BeforeEach
    public void setUp() {
        safelist = new Safelist();
    }

    @Test
    public void testIsSafeAttribute_AllowedAttribute() {
        safelist.addAttributes("a", "href");
        Element el = new Element("a");
        Attribute attr = new Attribute("href", "http://example.com");
        assertTrue(safelist.isSafeAttribute("a", el, attr));
    }

    @Test
    public void testIsSafeAttribute_DisallowedAttribute() {
        safelist.addAttributes("a", "href");
        Element el = new Element("a");
        Attribute attr = new Attribute("onclick", "alert('xss')");
        assertFalse(safelist.isSafeAttribute("a", el, attr));
    }

    @Test
    public void testIsSafeAttribute_EnforcedAttribute() {
        safelist.addEnforcedAttribute("a", "rel", "nofollow");
        Element el = new Element("a");
        Attribute attr = new Attribute("rel", "nofollow");
        assertTrue(safelist.isSafeAttribute("a", el, attr));
    }

    @Test
    public void testIsSafeAttribute_EnforcedAttributeMismatch() {
        safelist.addEnforcedAttribute("a", "rel", "nofollow");
        Element el = new Element("a");
        Attribute attr = new Attribute("rel", "follow");
        assertFalse(safelist.isSafeAttribute("a", el, attr));
    }

    @Test
    public void testIsSafeAttribute_ProtocolValidation() {
        safelist.addAttributes("a", "href");
        safelist.addProtocols("a", "href", "http", "https");
        Element el = new Element("a");
        Attribute attr = new Attribute("href", "http://example.com");
        assertTrue(safelist.isSafeAttribute("a", el, attr));
    }

    @Test
    public void testIsSafeAttribute_InvalidProtocol() {
        safelist.addAttributes("a", "href");
        safelist.addProtocols("a", "href", "http", "https");
        Element el = new Element("a");
        Attribute attr = new Attribute("href", "javascript:alert('xss')");
        assertFalse(safelist.isSafeAttribute("a", el, attr));
    }

    @Test
    public void testIsSafeAttribute_AllTag() {
        safelist.addAttributes(":all", "class");
        Element el = new Element("div");
        Attribute attr = new Attribute("class", "container");
        assertTrue(safelist.isSafeAttribute("div", el, attr));
    }
}
