
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

        parent.appendChild(textNode);
        parent.appendChild(child1);
        parent.appendChild(child2);

        // When
        Element firstElementChild = parent.firstElementChild();

        // Then
        assertNotNull(firstElementChild);
        assertEquals(child1, firstElementChild);
    }

    @Test
    void testFirstElementChildWithNoElementChildren() {
        // Given
        Element parent = new Element("parent");
        TextNode textNode = new TextNode("text");

        parent.appendChild(textNode);

        // When
        Element firstElementChild = parent.firstElementChild();

        // Then
        assertNull(firstElementChild);
    }
}
