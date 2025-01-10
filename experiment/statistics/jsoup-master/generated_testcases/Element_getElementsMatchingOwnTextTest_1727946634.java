
package org.jsoup.nodes;

import org.junit.jupiter.api.Test;
import java.util.regex.PatternSyntaxException;
import static org.junit.jupiter.api.Assertions.*;

class Element_getElementsMatchingOwnTextTest {

    @Test
    void testGetElementsMatchingOwnText_ValidRegex() {
        Element element = new Element("div");
        Elements result = element.getElementsMatchingOwnText("\\w+");
        assertNotNull(result);
    }

    @Test
    void testGetElementsMatchingOwnText_InvalidRegex() {
        Element element = new Element("div");
        assertThrows(IllegalArgumentException.class, () -> {
            element.getElementsMatchingOwnText("\\w+[");
        });
    }
}
