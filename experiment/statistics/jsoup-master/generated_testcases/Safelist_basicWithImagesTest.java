
package org.jsoup.safety;

import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Safelist_basicWithImagesTest {

    @Test
    public void testBasicWithImages_AddsImgTag() {
        Safelist safelist = Safelist.basicWithImages();
        assertTrue(safelist.isSafeTag("img"));
    }

    @Test
    public void testBasicWithImages_AddsImgAttributes() {
        Safelist safelist = Safelist.basicWithImages();
        Element imgElement = new Element(Tag.valueOf("img"), "");
        Attributes attributes = new Attributes();
        attributes.put("align", "left");
        attributes.put("alt", "image");
        attributes.put("height", "100");
        attributes.put("src", "http://example.com/image.jpg");
        attributes.put("title", "Example Image");
        attributes.put("width", "200");
        imgElement.attributes().addAll(attributes);

        for (Attribute attr : attributes) {
            assertTrue(safelist.isSafeAttribute("img", imgElement, attr));
        }
    }

    @Test
    public void testBasicWithImages_AddsImgProtocols() {
        Safelist safelist = Safelist.basicWithImages();
        Element imgElement = new Element(Tag.valueOf("img"), "");
        Attributes attributes = new Attributes();
        attributes.put("src", "http://example.com/image.jpg");
        imgElement.attributes().addAll(attributes);

        for (Attribute attr : attributes) {
            assertTrue(safelist.isSafeAttribute("img", imgElement, attr));
        }

        attributes.put("src", "https://example.com/image.jpg");
        imgElement.attributes().addAll(attributes);

        for (Attribute attr : attributes) {
            assertTrue(safelist.isSafeAttribute("img", imgElement, attr));
        }
    }

    @Test
    public void testBasicWithImages_InvalidProtocol() {
        Safelist safelist = Safelist.basicWithImages();
        Element imgElement = new Element(Tag.valueOf("img"), "");
        Attributes attributes = new Attributes();
        attributes.put("src", "ftp://example.com/image.jpg");
        imgElement.attributes().addAll(attributes);

        for (Attribute attr : attributes) {
            assertFalse(safelist.isSafeAttribute("img", imgElement, attr));
        }
    }
}
