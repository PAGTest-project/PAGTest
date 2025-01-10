
package org.jsoup.safety;

import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Safelist_basicWithImagesTest {

    @Test
    void testBasicWithImages() {
        // Given
        Safelist safelist = Safelist.basicWithImages();

        // Then
        assertTrue(safelist.isSafeTag("img"));
        assertTrue(safelist.isSafeAttribute("img", new Element("img"), new Attribute("src", "http://example.com/image.jpg")));
        assertTrue(safelist.isSafeAttribute("img", new Element("img"), new Attribute("alt", "Example")));
        assertTrue(safelist.isSafeAttribute("img", new Element("img"), new Attribute("width", "100")));
        assertTrue(safelist.isSafeAttribute("img", new Element("img"), new Attribute("height", "100")));
        assertTrue(safelist.isSafeAttribute("img", new Element("img"), new Attribute("title", "Example")));
        assertTrue(safelist.isSafeAttribute("img", new Element("img"), new Attribute("align", "left")));

        Attributes enforcedAttributes = safelist.getEnforcedAttributes("img");
        assertEquals(0, enforcedAttributes.size());
    }
}
