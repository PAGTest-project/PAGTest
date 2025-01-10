
package org.jsoup.safety;

import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Safelist_isSafeAttributeTest {
    private Safelist safelist;
    private Element element;

    @BeforeEach
    public void setUp() {
        safelist = new Safelist();
        element = new Element("a");
    }

    @Test
    public void testIsSafeAttribute_AllowedAttribute() {
        safelist.addAttributes("a", "href");
        Attribute attr = new Attribute("href", "http://example.com");
        assertTrue(safelist.isSafeAttribute("a", element, attr));
    }

    @Test
    public void testIsSafeAttribute_DisallowedAttribute() {
        safelist.addAttributes("a", "href");
        Attribute attr = new Attribute("onclick", "alert('xss')");
        assertFalse(safelist.isSafeAttribute("a", element, attr));
    }

    @Test
    public void testIsSafeAttribute_EnforcedAttribute() {
        safelist.addEnforcedAttribute("a", "rel", "nofollow");
        Attribute attr = new Attribute("rel", "nofollow");
        assertTrue(safelist.isSafeAttribute("a", element, attr));
    }

    @Test
    public void testIsSafeAttribute_InvalidProtocol() {
        safelist.addAttributes("a", "href");
        safelist.addProtocols("a", "href", "http");
        Attribute attr = new Attribute("href", "javascript:alert('xss')");
        assertFalse(safelist.isSafeAttribute("a", element, attr));
    }

    @Test
    public void testIsSafeAttribute_ValidProtocol() {
        safelist.addAttributes("a", "href");
        safelist.addProtocols("a", "href", "http");
        Attribute attr = new Attribute("href", "http://example.com");
        assertTrue(safelist.isSafeAttribute("a", element, attr));
    }

    @Test
    public void testIsSafeAttribute_AllTag() {
        safelist.addAttributes(":all", "class");
        Attribute attr = new Attribute("class", "example");
        assertTrue(safelist.isSafeAttribute("a", element, attr));
    }
}
