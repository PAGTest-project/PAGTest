
package org.jsoup.nodes;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Element_prependChildTest {

    @Test
    void testPrependChild() {
        // Given
        Element element = new Element("div");
        Node child = new TextNode("text");

        // When
        Element result = element.prependChild(child);

        // Then
        assertNotNull(result);
        assertEquals(1, element.childNodeSize());
        assertEquals(child, element.child(0));
    }

    @Test
    void testPrependChildWithNull() {
        // Given
        Element element = new Element("div");

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> {
            element.prependChild(null);
        });
    }
}
