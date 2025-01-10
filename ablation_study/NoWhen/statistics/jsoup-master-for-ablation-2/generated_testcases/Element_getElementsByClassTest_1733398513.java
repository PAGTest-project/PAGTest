
package org.jsoup.nodes;

import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Element_getElementsByClassTest {

    @Test
    void testGetElementsByClass() {
        // Given
        Element element = new Element("div");
        element.addClass("testClass");

        // When
        Elements result = element.getElementsByClass("testClass");

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("div", result.first().tagName());
    }
}
