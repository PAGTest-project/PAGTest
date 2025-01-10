
package org.jsoup.nodes;

import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Element_getElementsByAttributeTest {

    @Test
    void testGetElementsByAttribute() {
        // Given
        Element element = new Element("div");
        element.attr("key", "value");

        // When
        Elements result = element.getElementsByAttribute("key");

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("value", result.first().attr("key"));
    }
}
