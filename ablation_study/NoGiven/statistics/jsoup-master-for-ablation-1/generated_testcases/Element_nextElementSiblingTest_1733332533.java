
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
        parent.appendChild(child1);
        parent.appendChild(child2);

        // When
        Element nextSibling = child1.nextElementSibling();

        // Then
        assertNotNull(nextSibling);
        assertEquals(child2, nextSibling);
    }
}
