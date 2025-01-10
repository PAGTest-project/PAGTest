
package org.jsoup.select;

import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Elements_hasClassTest {

    @Test
    void testHasClass() {
        // Given
        Element element1 = new Element("div");
        element1.addClass("testClass");
        Elements elements = new Elements();
        elements.add(element1);

        // When
        boolean hasClass = elements.hasClass("testClass");

        // Then
        assertTrue(hasClass);

        // Given
        element1.removeClass("testClass");

        // When
        hasClass = elements.hasClass("testClass");

        // Then
        assertFalse(hasClass);
    }
}
