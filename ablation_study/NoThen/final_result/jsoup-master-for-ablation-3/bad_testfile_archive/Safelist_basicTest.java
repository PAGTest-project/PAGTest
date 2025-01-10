
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

        // Test allowed attributes for 'a' tag
        Attributes attributes = new Attributes();
        Attribute hrefAttribute = new Attribute("href", "http://example.com");
        attributes.put(hrefAttribute);
        Element aElement = new Element(Tag.valueOf("a"), "", attributes);
        assertTrue(safelist.isSafeAttribute("a", aElement, hrefAttribute));

        // Test disallowed attributes for 'a' tag
        Attribute classAttribute = new Attribute("class", "example");
        attributes.put(classAttribute);
        assertFalse(safelist.isSafeAttribute("a", aElement, classAttribute));

        // Test allowed attributes for 'blockquote' tag
        Attribute citeAttribute = new Attribute("cite", "http://example.com");
        attributes.put(citeAttribute);
        Element blockquoteElement = new Element(Tag.valueOf("blockquote"), "", attributes);
        assertTrue(safelist.isSafeAttribute("blockquote", blockquoteElement, citeAttribute));

        // Test disallowed attributes for 'blockquote' tag
        Attribute idAttribute = new Attribute("id", "example");
        attributes.put(idAttribute);
        assertFalse(safelist.isSafeAttribute("blockquote", blockquoteElement, idAttribute));
    }

    @Test
    public void testBasicSafelistProtocols() {
        Safelist safelist = Safelist.basic();

        // Test allowed protocols for 'a' tag
        Attributes attributes = new Attributes();
        Attribute httpAttribute = new Attribute("href", "http://example.com");
        attributes.put(httpAttribute);
        Element aElement = new Element(Tag.valueOf("a"), "", attributes);
        assertTrue(safelist.isSafeAttribute("a", aElement, httpAttribute));

        Attribute httpsAttribute = new Attribute("href", "https://example.com");
        attributes.put(httpsAttribute);
        assertTrue(safelist.isSafeAttribute("a", aElement, httpsAttribute));

        Attribute ftpAttribute = new Attribute("href", "ftp://example.com");
        attributes.put(ftpAttribute);
        assertTrue(safelist.isSafeAttribute("a", aElement, ftpAttribute));

        Attribute mailtoAttribute = new Attribute("href", "mailto:example@example.com");
        attributes.put(mailtoAttribute);
        assertTrue(safelist.isSafeAttribute("a", aElement, mailtoAttribute));

        // Test disallowed protocols for 'a' tag
        Attribute invalidAttribute = new Attribute("href", "invalid://example.com");
        attributes.put(invalidAttribute);
        assertFalse(safelist.isSafeAttribute("a", aElement, invalidAttribute));
    }

    @Test
    public void testBasicSafelistEnforcedAttributes() {
        Safelist safelist = Safelist.basic();

        // Test enforced attribute for 'a' tag
        Attributes attributes = new Attributes();
        Attribute relAttribute = new Attribute("rel", "nofollow");
        attributes.put(relAttribute);
        Element aElement = new Element(Tag.valueOf("a"), "", attributes);
        assertTrue(safelist.isSafeAttribute("a", aElement, relAttribute));

        // Test enforced attribute value mismatch for 'a' tag
        Attribute invalidRelAttribute = new Attribute("rel", "follow");
        attributes.put(invalidRelAttribute);
        assertFalse(safelist.isSafeAttribute("a", aElement, invalidRelAttribute));
    }
}
