
package org.jsoup.safety;

import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Safelist_basicWithImagesTest {

    private static final String TEST_TAG = "img";
    private static final String TEST_ATTRIBUTE = "src";
    private static final String TEST_SCHEME = "http";

    @Test
    public void testBasicWithImages_AddsImgTag() {
        Safelist safelist = Safelist.basicWithImages();
        assertTrue(safelist.isSafeTag(TEST_TAG));
    }

    @Test
    public void testBasicWithImages_AddsImgAttributes() {
        Safelist safelist = Safelist.basicWithImages();
        Attributes attributes = new Attributes();
        Attribute validAttribute = new Attribute(TEST_ATTRIBUTE, TEST_SCHEME + "://someValue");
        attributes.put(validAttribute);
        Element validElement = new Element(Tag.valueOf(TEST_TAG), "", attributes);

        assertTrue(safelist.isSafeAttribute(TEST_TAG, validElement, validAttribute));
    }

    @Test
    public void testBasicWithImages_InvalidProtocol() {
        Safelist safelist = Safelist.basicWithImages();
        Attributes attributes = new Attributes();
        Attribute invalidAttribute = new Attribute(TEST_ATTRIBUTE, "invalid-scheme://someValue");
        attributes.put(invalidAttribute);
        Element invalidElement = new Element(Tag.valueOf(TEST_TAG), "", attributes);

        assertFalse(safelist.isSafeAttribute(TEST_TAG, invalidElement, invalidAttribute));
    }

    @Test
    public void testBasicWithImages_EnforcedAttributes() {
        Safelist safelist = Safelist.basicWithImages();
        Attributes enforcedAttributes = safelist.getEnforcedAttributes(TEST_TAG);
        assertTrue(enforcedAttributes.hasKeyIgnoreCase("align"));
        assertTrue(enforcedAttributes.hasKeyIgnoreCase("alt"));
        assertTrue(enforcedAttributes.hasKeyIgnoreCase("height"));
        assertTrue(enforcedAttributes.hasKeyIgnoreCase("width"));
    }
}
