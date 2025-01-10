
package org.jsoup.nodes;

import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class Element_getElementsByTagTest {

    @Test
    void testGetElementsByTag() {
        // Given
        Element element = new Element("div");
        String tagName = "span";

        // When
        Elements result = element.getElementsByTag(tagName);

        // Then
        assertNotNull(result);
    }

    @Test
    void testGetElementsByTagWithEmptyTagName() {
        // Given
        Element element = new Element("div");
        String tagName = "";

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> {
            element.getElementsByTag(tagName);
        });
    }
}
