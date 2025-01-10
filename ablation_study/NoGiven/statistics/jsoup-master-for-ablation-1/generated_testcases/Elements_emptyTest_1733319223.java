
package org.jsoup.select;

import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Elements_emptyTest {

    @Test
    void testEmptyWithNonEmptyElements() {
        // Given
        Element element1 = new Element("div");
        Element element2 = new Element("span");
        Elements elements = new Elements(element1, element2);

        // When
        Elements result = elements.empty();

        // Then
        assertTrue(element1.childNodes().isEmpty());
        assertTrue(element2.childNodes().isEmpty());
        assertEquals(elements, result);
    }

    @Test
    void testEmptyWithEmptyElements() {
        // Given
        Elements elements = new Elements();

        // When
        Elements result = elements.empty();

        // Then
        assertEquals(elements, result);
    }
}
