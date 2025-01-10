
package org.jsoup.nodes;

import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;

import java.util.regex.PatternSyntaxException;

import static org.junit.jupiter.api.Assertions.*;

class Element_getElementsByAttributeValueMatchingTest {

    @Test
    void testGetElementsByAttributeValueMatching_validRegex() {
        Element element = new Element("div");
        Elements result = element.getElementsByAttributeValueMatching("class", "validRegex");
        assertNotNull(result);
    }

    @Test
    void testGetElementsByAttributeValueMatching_invalidRegex() {
        Element element = new Element("div");
        assertThrows(IllegalArgumentException.class, () -> {
            element.getElementsByAttributeValueMatching("class", "[invalidRegex");
        });
    }
}
