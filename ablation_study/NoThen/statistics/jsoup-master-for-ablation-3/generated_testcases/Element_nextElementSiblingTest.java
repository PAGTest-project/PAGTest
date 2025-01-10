
package org.jsoup.nodes;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Element_nextElementSiblingTest {

    @Test
    void testNextElementSibling() {
        // Given
        Element parent = new Element("parent");
        Element child1 = new Element("child1");
        Element child2 = new Element("child2");
        Element child3 = new Element("child3");
        parent.appendChild(child1);
        parent.appendChild(child2);
        parent.appendChild(child3);

        // When
        Element nextSibling = child1.nextElementSibling();

        // Then
        assertNotNull(nextSibling);
        assertEquals(child2, nextSibling);

        // When
        nextSibling = child2.nextElementSibling();

        // Then
        assertNotNull(nextSibling);
        assertEquals(child3, nextSibling);

        // When
        nextSibling = child3.nextElementSibling();

        // Then
        assertNull(nextSibling);
    }
}
