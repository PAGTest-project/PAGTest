
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
        Element element = new Element(Tag.valueOf("a"), "");
        Attribute hrefAttr = new Attribute("href", "http://example.com");
        Attribute invalidAttr = new Attribute("onclick", "alert('xss')");

        assertTrue(safelist.isSafeAttribute("a", element, hrefAttr));
        assertFalse(safelist.isSafeAttribute("a", element, invalidAttr));
    }

    @Test
    public void testRelaxed_getEnforcedAttributes() {
        Attributes enforcedAttributes = safelist.getEnforcedAttributes("a");
        assertTrue(enforcedAttributes.isEmpty());
    }

    @Test
    public void testRelaxed_addTags() {
        safelist.addTags("customTag");
        assertTrue(safelist.isSafeTag("customTag"));
    }

    @Test
    public void testRelaxed_addAttributes() {
        safelist.addAttributes("a", "customAttr");
        Element element = new Element(Tag.valueOf("a"), "");
        Attribute customAttr = new Attribute("customAttr", "value");
        assertTrue(safelist.isSafeAttribute("a", element, customAttr));
    }
}
