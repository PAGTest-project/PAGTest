
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
    public void testBasicSafelistConfiguration() {
        // Given
        String[] expectedTags = {
            "a", "b", "blockquote", "br", "cite", "code", "dd", "dl", "dt", "em",
            "i", "li", "ol", "p", "pre", "q", "small", "span", "strike", "strong", "sub",
            "sup", "u", "ul"
        };
        String[] expectedAttributesForA = {"href"};
        String[] expectedAttributesForBlockquote = {"cite"};
        String[] expectedAttributesForQ = {"cite"};
        String[] expectedProtocolsForA = {"ftp", "http", "https", "mailto"};
        String[] expectedProtocolsForBlockquote = {"http", "https"};
        String[] expectedProtocolsForCite = {"http", "https"};

        // When
        Safelist basicSafelist = Safelist.basic();

        // Then
        for (String tag : expectedTags) {
            assertTrue(basicSafelist.isSafeTag(tag));
        }

        Attributes enforcedAttributesForA = basicSafelist.getEnforcedAttributes("a");
        assertEquals(1, enforcedAttributesForA.size());
        assertEquals("nofollow", enforcedAttributesForA.get("rel"));

        for (String attr : expectedAttributesForA) {
            assertTrue(basicSafelist.isSafeAttribute("a", new Element(Tag.valueOf("a"), ""), new Attribute(attr, "")));
        }

        for (String attr : expectedAttributesForBlockquote) {
            assertTrue(basicSafelist.isSafeAttribute("blockquote", new Element(Tag.valueOf("blockquote"), ""), new Attribute(attr, "")));
        }

        for (String attr : expectedAttributesForQ) {
            assertTrue(basicSafelist.isSafeAttribute("q", new Element(Tag.valueOf("q"), ""), new Attribute(attr, "")));
        }

        for (String protocol : expectedProtocolsForA) {
            assertTrue(basicSafelist.isSafeAttribute("a", new Element(Tag.valueOf("a"), ""), new Attribute("href", protocol + "://example.com")));
        }

        for (String protocol : expectedProtocolsForBlockquote) {
            assertTrue(basicSafelist.isSafeAttribute("blockquote", new Element(Tag.valueOf("blockquote"), ""), new Attribute("cite", protocol + "://example.com")));
        }

        for (String protocol : expectedProtocolsForCite) {
            assertTrue(basicSafelist.isSafeAttribute("cite", new Element(Tag.valueOf("cite"), ""), new Attribute("cite", protocol + "://example.com")));
        }
    }
}
