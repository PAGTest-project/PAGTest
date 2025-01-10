
package org.jsoup.nodes;

import org.junit.jupiter.api.Test;
import org.jspecify.annotations.Nullable;

import static org.junit.jupiter.api.Assertions.*;

class Element_lastElementChildTest {

    @Test
    void testLastElementChild() {
        // Given
        Element parent = new Element("parent");
        Element child1 = new Element("child1");
        Element child2 = new Element("child2");
        parent.appendChild(child1);
        parent.appendChild(child2);

        // When
        Element result = parent.lastElementChild();

        // Then
        assertNotNull(result);
        assertEquals(child2, result);
    }

    @Test
    void testLastElementChildWithNonElementChild() {
        // Given
        Element parent = new Element("parent");
        Element child1 = new Element("child1");
        TextNode textNode = new TextNode("text");
        parent.appendChild(child1);
        parent.appendChild(textNode);

        // When
        Element result = parent.lastElementChild();

        // Then
        assertNotNull(result);
        assertEquals(child1, result);
    }

    @Test
    void testLastElementChildWithNoChildren() {
        // Given
        Element parent = new Element("parent");

        // When
        Element result = parent.lastElementChild();

        // Then
        assertNull(result);
    }
}
