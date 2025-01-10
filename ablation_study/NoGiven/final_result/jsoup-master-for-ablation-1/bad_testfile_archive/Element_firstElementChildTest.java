
package org.jsoup.nodes;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Element_firstElementChildTest {

    @Test
    void testFirstElementChild() {
        // Given
        Element parent = new Element("parent");
        Element child1 = new Element("child1");
        Element child2 = new Element("child2");
        TextNode textNode = new TextNode("text");

        // When
        parent.appendChild(textNode);
        parent.appendChild(child1);
        parent.appendChild(child2);

        // Then
        assertEquals(child1, parent.firstElementChild());
    }

    @Test
    void testFirstElementChildWithNoElementChild() {
        // Given
        Element parent = new Element("parent");
        TextNode textNode = new TextNode("text");

        // When
        parent.appendChild(textNode);

        // Then
        assertNull(parent.firstElementChild());
    }
}
