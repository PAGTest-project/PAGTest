
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
        String[] expectedTags = {
            "a", "b", "blockquote", "br", "cite", "code", "dd", "dl", "dt", "em",
            "i", "li", "ol", "p", "pre", "q", "small", "span", "strike", "strong", "sub",
            "sup", "u", "ul"
        };

        for (String tag : expectedTags) {
            assertTrue(safelist.isSafeTag(tag));
        }
    }

    @Test
    public void testBasicSafelistAttributes() {
        Element aElement = new Element(Tag.valueOf("a"), "");
        aElement.attr("href", "http://example.com");
        assertTrue(safelist.isSafeAttribute("a", aElement, aElement.attributes().get(0)));

        Element blockquoteElement = new Element(Tag.valueOf("blockquote"), "");
        blockquoteElement.attr("cite", "http://example.com");
        assertTrue(safelist.isSafeAttribute("blockquote", blockquoteElement, blockquoteElement.attributes().get(0)));

        Element qElement = new Element(Tag.valueOf("q"), "");
        qElement.attr("cite", "http://example.com");
        assertTrue(safelist.isSafeAttribute("q", qElement, qElement.attributes().get(0)));
    }

    @Test
    public void testBasicSafelistProtocols() {
        Element aElement = new Element(Tag.valueOf("a"), "");
        aElement.attr("href", "http://example.com");
        assertTrue(safelist.isSafeAttribute("a", aElement, aElement.attributes().get(0)));

        aElement.attr("href", "https://example.com");
        assertTrue(safelist.isSafeAttribute("a", aElement, aElement.attributes().get(0)));

        aElement.attr("href", "ftp://example.com");
        assertTrue(safelist.isSafeAttribute("a", aElement, aElement.attributes().get(0)));

        aElement.attr("href", "mailto:user@example.com");
        assertTrue(safelist.isSafeAttribute("a", aElement, aElement.attributes().get(0)));

        Element blockquoteElement = new Element(Tag.valueOf("blockquote"), "");
        blockquoteElement.attr("cite", "http://example.com");
        assertTrue(safelist.isSafeAttribute("blockquote", blockquoteElement, blockquoteElement.attributes().get(0)));

        blockquoteElement.attr("cite", "https://example.com");
        assertTrue(safelist.isSafeAttribute("blockquote", blockquoteElement, blockquoteElement.attributes().get(0)));

        Element qElement = new Element(Tag.valueOf("q"), "");
        qElement.attr("cite", "http://example.com");
        assertTrue(safelist.isSafeAttribute("q", qElement, qElement.attributes().get(0)));

        qElement.attr("cite", "https://example.com");
        assertTrue(safelist.isSafeAttribute("q", qElement, qElement.attributes().get(0)));
    }

    @Test
    public void testBasicSafelistEnforcedAttributes() {
        Attributes enforcedAttributes = safelist.getEnforcedAttributes("a");
        assertEquals(1, enforcedAttributes.size());
        assertEquals("nofollow", enforcedAttributes.get("rel"));
    }
}
