
package org.jsoup.safety;

import org.jsoup.nodes.Attributes;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Safelist_basicWithImagesTest {

    @Test
    public void testBasicWithImages() {
        Safelist safelist = Safelist.basicWithImages();

        // Test tags
        assertTrue(safelist.isSafeTag("img"));
        assertTrue(safelist.isSafeTag("a"));
        assertFalse(safelist.isSafeTag("script"));

        // Test attributes
        Attributes attrs = new Attributes();
        attrs.put("src", "http://example.com/image.jpg");
        attrs.put("alt", "Example Image");
        attrs.put("width", "100");
        attrs.put("height", "100");
        attrs.put("title", "Image Title");
        attrs.put("align", "left");

        assertTrue(safelist.isSafeAttribute("img", attrs, "src"));
        assertTrue(safelist.isSafeAttribute("img", attrs, "alt"));
        assertTrue(safelist.isSafeAttribute("img", attrs, "width"));
        assertTrue(safelist.isSafeAttribute("img", attrs, "height"));
        assertTrue(safelist.isSafeAttribute("img", attrs, "title"));
        assertTrue(safelist.isSafeAttribute("img", attrs, "align"));

        // Test protocols
        assertTrue(safelist.isSafeAttribute("img", attrs, "src"));
        attrs.put("src", "https://example.com/image.jpg");
        assertTrue(safelist.isSafeAttribute("img", attrs, "src"));
        attrs.put("src", "ftp://example.com/image.jpg");
        assertFalse(safelist.isSafeAttribute("img", attrs, "src"));
    }
}
