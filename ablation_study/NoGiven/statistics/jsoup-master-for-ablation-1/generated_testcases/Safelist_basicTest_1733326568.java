
package org.jsoup.safety;

import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Safelist_basicTest {

    private Safelist safelist;

    @BeforeEach
    public void setUp() {
        safelist = Safelist.basic();
    }

    @Test
    public void testBasicSafelistTags() {
        assertTrue(safelist.isSafeTag("a"));
        assertTrue(safelist.isSafeTag("blockquote"));
        assertFalse(safelist.isSafeTag("script"));
    }

    @Test
    public void testBasicSafelistAttributes() {
        Attributes attributes = new Attributes();
        Attribute hrefAttribute = new Attribute("href", "http://example.com");
        attributes.put(hrefAttribute);
        Element aElement = new Element(Tag.valueOf("a"), "", attributes);

        assertTrue(safelist.isSafeAttribute("a", aElement, hrefAttribute));

        Attribute invalidAttribute = new Attribute("onclick", "alert('xss')");
        attributes.put(invalidAttribute);

        assertFalse(safelist.isSafeAttribute("a", aElement, invalidAttribute));
    }

    @Test
    public void testBasicSafelistProtocols() {
        Attributes attributes = new Attributes();
        Attribute hrefAttribute = new Attribute("href", "http://example.com");
        attributes.put(hrefAttribute);
        Element aElement = new Element(Tag.valueOf("a"), "", attributes);

        assertTrue(safelist.isSafeAttribute("a", aElement, hrefAttribute));

        Attribute invalidHrefAttribute = new Attribute("href", "javascript:alert('xss')");
        attributes.put(invalidHrefAttribute);

        assertFalse(safelist.isSafeAttribute("a", aElement, invalidHrefAttribute));
    }

    @Test
    public void testBasicSafelistEnforcedAttributes() {
        Attributes enforcedAttributes = safelist.getEnforcedAttributes("a");
        assertEquals("nofollow", enforcedAttributes.get("rel"));
    }
}
