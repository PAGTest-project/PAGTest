
package org.jsoup.safety;

import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Safelist_basicTest {

    @Test
    public void testBasicSafelistTags() {
        Safelist safelist = Safelist.basic();

        // Test allowed tags
        assertTrue(safelist.isSafeTag("a"));
        assertTrue(safelist.isSafeTag("blockquote"));
        assertTrue(safelist.isSafeTag("br"));
        assertTrue(safelist.isSafeTag("cite"));
        assertTrue(safelist.isSafeTag("code"));
        assertTrue(safelist.isSafeTag("dd"));
        assertTrue(safelist.isSafeTag("dl"));
        assertTrue(safelist.isSafeTag("dt"));
        assertTrue(safelist.isSafeTag("em"));
        assertTrue(safelist.isSafeTag("i"));
        assertTrue(safelist.isSafeTag("li"));
        assertTrue(safelist.isSafeTag("ol"));
        assertTrue(safelist.isSafeTag("p"));
        assertTrue(safelist.isSafeTag("pre"));
        assertTrue(safelist.isSafeTag("q"));
        assertTrue(safelist.isSafeTag("small"));
        assertTrue(safelist.isSafeTag("span"));
        assertTrue(safelist.isSafeTag("strike"));
        assertTrue(safelist.isSafeTag("strong"));
        assertTrue(safelist.isSafeTag("sub"));
        assertTrue(safelist.isSafeTag("sup"));
        assertTrue(safelist.isSafeTag("u"));
        assertTrue(safelist.isSafeTag("ul"));

        // Test disallowed tags
        assertFalse(safelist.isSafeTag("script"));
        assertFalse(safelist.isSafeTag("img"));
    }

    @Test
    public void testBasicSafelistAttributes() {
        Safelist safelist = Safelist.basic();
        Element element = new Element(Tag.valueOf("a"), "");
        Attribute hrefAttr = new Attribute("href", "http://example.com");
        Attribute classAttr = new Attribute("class", "example");

        // Test allowed attributes
        assertTrue(safelist.isSafeAttribute("a", element, hrefAttr));
        assertFalse(safelist.isSafeAttribute("a", element, classAttr));

        element = new Element(Tag.valueOf("blockquote"), "");
        Attribute citeAttr = new Attribute("cite", "http://example.com");
        assertTrue(safelist.isSafeAttribute("blockquote", element, citeAttr));

        element = new Element(Tag.valueOf("q"), "");
        assertTrue(safelist.isSafeAttribute("q", element, citeAttr));
    }

    @Test
    public void testBasicSafelistProtocols() {
        Safelist safelist = Safelist.basic();
        Element element = new Element(Tag.valueOf("a"), "");
        Attribute hrefAttr = new Attribute("href", "http://example.com");
        Attribute ftpAttr = new Attribute("href", "ftp://example.com");
        Attribute mailtoAttr = new Attribute("href", "mailto:example@example.com");
        Attribute invalidAttr = new Attribute("href", "javascript:alert('XSS')");

        // Test allowed protocols
        assertTrue(safelist.isSafeAttribute("a", element, hrefAttr));
        assertTrue(safelist.isSafeAttribute("a", element, ftpAttr));
        assertTrue(safelist.isSafeAttribute("a", element, mailtoAttr));

        // Test disallowed protocols
        assertFalse(safelist.isSafeAttribute("a", element, invalidAttr));
    }

    @Test
    public void testBasicSafelistEnforcedAttributes() {
        Safelist safelist = Safelist.basic();
        Attributes enforcedAttrs = safelist.getEnforcedAttributes("a");

        // Test enforced attributes
        assertEquals("nofollow", enforcedAttrs.get("rel"));
    }
}
