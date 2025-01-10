
package org.jsoup.select;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Elements_toggleClassTest {

    @Test
    void testToggleClass() {
        // Given
        Element element1 = new Element("div").addClass("testClass");
        Element element2 = new Element("div");
        Elements elements = new Elements(element1, element2);

        // When
        elements.toggleClass("testClass");

        // Then
        assertFalse(element1.hasClass("testClass"));
        assertTrue(element2.hasClass("testClass"));
    }
}
