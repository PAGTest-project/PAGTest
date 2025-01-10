
package org.jsoup.nodes;

import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Element_getElementsByAttributeStartingTest {

    @Test
    void testGetElementsByAttributeStarting() {
        // Given
        Element element = new Element("div");
        element.attr("data-test", "value");

        // When
        Elements result = element.getElementsByAttributeStarting("data-");

        // Then
        assertEquals(1, result.size());
        assertEquals("div", result.get(0).tagName());
    }
}
