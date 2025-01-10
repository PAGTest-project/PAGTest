
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
    public void testRelaxedSafelist_isSafeTag() {
        assertTrue(safelist.isSafeTag("a"));
        assertTrue(safelist.isSafeTag("img"));
        assertFalse(safelist.isSafeTag("script"));
    }

    @Test
    public void testRelaxedSafelist_isSafeAttribute() {
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
    public void testRelaxedSafelist_getEnforcedAttributes() {
        Attributes enforcedAttributes = safelist.getEnforcedAttributes("a");
        assertTrue(enforcedAttributes.isEmpty());

        enforcedAttributes = safelist.getEnforcedAttributes("img");
        assertTrue(enforcedAttributes.isEmpty());
    }
}
