
package org.jsoup.nodes;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Element_previousElementSiblingTest {

    @Test
    void testPreviousElementSibling() {
        // Given
        Element parent = new Element("parent");
        Element child1 = new Element("child1");
        Element child2 = new Element("child2");
        parent.appendChild(child1);
        parent.appendChild(child2);

        // When
        Element previousSibling = child2.previousElementSibling();

        // Then
        assertNotNull(previousSibling);
        assertEquals(child1, previousSibling);
    }

    @Test
    void testPreviousElementSiblingNoSibling() {
        // Given
        Element parent = new Element("parent");
        Element child1 = new Element("child1");
        parent.appendChild(child1);

        // When
        Element previousSibling = child1.previousElementSibling();

        // Then
        assertNull(previousSibling);
    }
}
