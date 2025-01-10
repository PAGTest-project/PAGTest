
package org.jsoup.nodes;

import org.junit.jupiter.api.Test;
import org.jsoup.select.Elements;
import java.util.regex.PatternSyntaxException;
import static org.junit.jupiter.api.Assertions.*;

class Element_getElementsMatchingTextTest {

    @Test
    void testGetElementsMatchingText_ValidRegex() {
        Element element = new Element("div");
        Elements result = element.getElementsMatchingText("validRegex");
        assertNotNull(result);
    }

    @Test
    void testGetElementsMatchingText_InvalidRegex() {
        Element element = new Element("div");
        assertThrows(IllegalArgumentException.class, () -> {
            element.getElementsMatchingText("*invalidRegex");
        });
    }
}
