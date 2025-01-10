
package org.jsoup.nodes;

import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Element_getElementsByAttributeTest {

    @Test
    void testGetElementsByAttribute() {
        // Given
        Element element = new Element("div");
        element.attr("testKey", "testValue");

        // When
        Elements result = element.getElementsByAttribute("testKey");

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("testValue", result.first().attr("testKey"));
    }
}
