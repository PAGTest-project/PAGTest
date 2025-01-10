
package org.jsoup.select;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Elements_toggleClassTest {

    @Test
    void testToggleClass() {
        Elements elements = new Elements();
        Element element = new Element("div");
        elements.add(element);

        // Given: Element with no class
        assertFalse(element.hasClass("testClass"));

        // When: toggleClass is called
        elements.toggleClass("testClass");

        // Then: Class should be added
        assertTrue(element.hasClass("testClass"));

        // When: toggleClass is called again
        elements.toggleClass("testClass");

        // Then: Class should be removed
        assertFalse(element.hasClass("testClass"));
    }
}
