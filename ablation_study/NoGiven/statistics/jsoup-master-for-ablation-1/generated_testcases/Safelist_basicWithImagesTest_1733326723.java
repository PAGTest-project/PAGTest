
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
        Attributes attributes = new Attributes();
        Attribute alignAttr = new Attribute("align", "left");
        Attribute altAttr = new Attribute("alt", "image");
        Attribute heightAttr = new Attribute("height", "100");
        Attribute srcAttr = new Attribute("src", "http://example.com/image.jpg");
        Attribute titleAttr = new Attribute("title", "Image Title");
        Attribute widthAttr = new Attribute("width", "200");
        attributes.put(alignAttr);
        attributes.put(altAttr);
        attributes.put(heightAttr);
        attributes.put(srcAttr);
        attributes.put(titleAttr);
        attributes.put(widthAttr);
        Element imgElement = new Element(Tag.valueOf("img"), "", attributes);

        assertTrue(safelist.isSafeAttribute("img", imgElement, alignAttr));
        assertTrue(safelist.isSafeAttribute("img", imgElement, altAttr));
        assertTrue(safelist.isSafeAttribute("img", imgElement, heightAttr));
        assertTrue(safelist.isSafeAttribute("img", imgElement, srcAttr));
        assertTrue(safelist.isSafeAttribute("img", imgElement, titleAttr));
        assertTrue(safelist.isSafeAttribute("img", imgElement, widthAttr));
    }

    @Test
    public void testBasicWithImages_AddsImgProtocols() {
        Safelist safelist = Safelist.basicWithImages();
        Attributes attributes = new Attributes();
        Attribute httpSrcAttr = new Attribute("src", "http://example.com/image.jpg");
        Attribute httpsSrcAttr = new Attribute("src", "https://example.com/image.jpg");
        attributes.put(httpSrcAttr);
        attributes.put(httpsSrcAttr);
        Element imgElement = new Element(Tag.valueOf("img"), "", attributes);

        assertTrue(safelist.isSafeAttribute("img", imgElement, httpSrcAttr));
        assertTrue(safelist.isSafeAttribute("img", imgElement, httpsSrcAttr));
    }

    @Test
    public void testBasicWithImages_InvalidProtocol() {
        Safelist safelist = Safelist.basicWithImages();
        Attributes attributes = new Attributes();
        Attribute invalidSrcAttr = new Attribute("src", "ftp://example.com/image.jpg");
        attributes.put(invalidSrcAttr);
        Element imgElement = new Element(Tag.valueOf("img"), "", attributes);

        assertFalse(safelist.isSafeAttribute("img", imgElement, invalidSrcAttr));
    }
}
