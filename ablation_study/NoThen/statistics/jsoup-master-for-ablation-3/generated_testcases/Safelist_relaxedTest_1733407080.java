
package org.jsoup.safety;

import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Safelist_relaxedTest {

    @Test
    public void testRelaxed_AddTags() {
        Safelist safelist = Safelist.relaxed();
        assertTrue(safelist.isSafeTag("a"));
        assertTrue(safelist.isSafeTag("img"));
        assertTrue(safelist.isSafeTag("table"));
    }

    @Test
    public void testRelaxed_AddAttributes() {
        Safelist safelist = Safelist.relaxed();
        Attributes attributes = new Attributes();
        Attribute hrefAttribute = new Attribute("href", "http://example.com");
        Attribute imgSrcAttribute = new Attribute("src", "http://example.com/image.jpg");
        attributes.put(hrefAttribute);
        attributes.put(imgSrcAttribute);
        Element aElement = new Element(Tag.valueOf("a"), "", attributes);
        Element imgElement = new Element(Tag.valueOf("img"), "", attributes);

        assertTrue(safelist.isSafeAttribute("a", aElement, hrefAttribute));
        assertTrue(safelist.isSafeAttribute("img", imgElement, imgSrcAttribute));
    }

    @Test
    public void testRelaxed_AddProtocols() {
        Safelist safelist = Safelist.relaxed();
        Attributes attributes = new Attributes();
        Attribute hrefAttribute = new Attribute("href", "http://example.com");
        Attribute imgSrcAttribute = new Attribute("src", "https://example.com/image.jpg");
        attributes.put(hrefAttribute);
        attributes.put(imgSrcAttribute);
        Element aElement = new Element(Tag.valueOf("a"), "", attributes);
        Element imgElement = new Element(Tag.valueOf("img"), "", attributes);

        assertTrue(safelist.isSafeAttribute("a", aElement, hrefAttribute));
        assertTrue(safelist.isSafeAttribute("img", imgElement, imgSrcAttribute));
    }

    @Test
    public void testRelaxed_InvalidProtocols() {
        Safelist safelist = Safelist.relaxed();
        Attributes attributes = new Attributes();
        Attribute invalidHrefAttribute = new Attribute("href", "invalid://example.com");
        Attribute invalidImgSrcAttribute = new Attribute("src", "invalid://example.com/image.jpg");
        attributes.put(invalidHrefAttribute);
        attributes.put(invalidImgSrcAttribute);
        Element aElement = new Element(Tag.valueOf("a"), "", attributes);
        Element imgElement = new Element(Tag.valueOf("img"), "", attributes);

        assertFalse(safelist.isSafeAttribute("a", aElement, invalidHrefAttribute));
        assertFalse(safelist.isSafeAttribute("img", imgElement, invalidImgSrcAttribute));
    }
}
