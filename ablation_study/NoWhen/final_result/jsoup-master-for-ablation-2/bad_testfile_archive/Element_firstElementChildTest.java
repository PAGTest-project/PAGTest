
package org.jsoup.nodes;

import org.junit.jupiter.api.Test;
import org.jspecify.annotations.Nullable;

import static org.junit.jupiter.api.Assertions.*;

class Element_firstElementChildTest {

    @Test
    void testFirstElementChild() {
        // Given
        Element parent = new Element("parent");
        Element child1 = new Element("child1");
        Element child2 = new Element("child2");
        parent.appendChild(child1);
        parent.appendChild(child2);

        // When
        Element firstChild = parent.firstElementChild();

        // Then
        assertNotNull(firstChild);
        assertEquals(child1, firstChild);
    }

    @Test
    void testFirstElementChildWithNoElementChild() {
        // Given
        Element parent = new Element("parent");
        TextNode textNode = new TextNode("text");
        parent.appendChild(textNode);

        // When
        Element firstChild = parent.firstElementChild();

        // Then
        assertNull(firstChild);
    }

    @Test
    void testFirstElementChildWithNoChildren() {
        // Given
        Element parent = new Element("parent");

        // When
        Element firstChild = parent.firstElementChild();

        // Then
        assertNull(firstChild);
    }
}
