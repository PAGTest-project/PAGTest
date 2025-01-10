
package org.jsoup.nodes;

import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;

import java.util.regex.PatternSyntaxException;

import static org.junit.jupiter.api.Assertions.*;

class Element_getElementsMatchingOwnTextTest {

    @Test
    void testGetElementsMatchingOwnText_validRegex() {
        Element element = new Element("div");
        Elements result = element.getElementsMatchingOwnText("\\w+");
        assertNotNull(result);
    }

    @Test
    void testGetElementsMatchingOwnText_invalidRegex() {
        Element element = new Element("div");
        assertThrows(IllegalArgumentException.class, () -> {
            element.getElementsMatchingOwnText("[invalid");
        });
    }
}
