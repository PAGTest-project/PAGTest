
package org.jsoup.safety;

import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Safelist_relaxedTest {

    private Safelist safelist;

    @BeforeEach
    public void setUp() {
        safelist = Safelist.relaxed();
    }

    @Test
    public void testRelaxed_isSafeTag() {
        assertTrue(safelist.isSafeTag("a"));
        assertTrue(safelist.isSafeTag("img"));
        assertFalse(safelist.isSafeTag("script"));
    }

    @Test
    public void testRelaxed_isSafeAttribute() {
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
    public void testRelaxed_getEnforcedAttributes() {
        Attributes enforcedAttributes = safelist.getEnforcedAttributes("a");
        assertTrue(enforcedAttributes.isEmpty());

        safelist.addEnforcedAttribute("a", "rel", "nofollow");
        enforcedAttributes = safelist.getEnforcedAttributes("a");
        assertEquals("nofollow", enforcedAttributes.get("rel"));
    }
}
