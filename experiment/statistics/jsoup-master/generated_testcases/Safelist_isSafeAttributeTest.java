
package org.jsoup.safety;

import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Safelist_isSafeAttributeTest {
    private Safelist safelist;
    private Element element;
    private Attribute validAttribute;
    private Attribute invalidAttribute;

    @BeforeEach
    public void setUp() {
        safelist = new Safelist();
        safelist.addTags("a");
        safelist.addAttributes("a", "href");
        safelist.addProtocols("a", "href", "http", "https");

        element = new Element("a");
        validAttribute = new Attribute("href", "http://example.com");
        invalidAttribute = new Attribute("href", "javascript:alert('xss')");
    }

    @Test
    public void testIsSafeAttribute_validAttribute() {
        assertTrue(safelist.isSafeAttribute("a", element, validAttribute));
    }

    @Test
    public void testIsSafeAttribute_invalidAttribute() {
        assertFalse(safelist.isSafeAttribute("a", element, invalidAttribute));
    }

    @Test
    public void testIsSafeAttribute_enforcedAttribute() {
        safelist.addEnforcedAttribute("a", "rel", "nofollow");
        Attribute enforcedAttribute = new Attribute("rel", "nofollow");
        assertTrue(safelist.isSafeAttribute("a", element, enforcedAttribute));
    }

    @Test
    public void testIsSafeAttribute_noAttributesDefined() {
        Safelist emptySafelist = new Safelist();
        assertFalse(emptySafelist.isSafeAttribute("a", element, validAttribute));
    }

    @Test
    public void testIsSafeAttribute_allTag() {
        safelist.addAttributes(":all", "class");
        Attribute classAttribute = new Attribute("class", "example");
        assertTrue(safelist.isSafeAttribute("a", element, classAttribute));
    }
}
