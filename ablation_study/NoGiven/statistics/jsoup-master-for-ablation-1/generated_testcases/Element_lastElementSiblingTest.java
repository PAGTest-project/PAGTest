
package org.jsoup.nodes;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Element_lastElementSiblingTest {

    @Test
    void testLastElementSibling() {
        // Given
        Element parent = new Element("parent");
        Element child1 = new Element("child1");
        Element child2 = new Element("child2");
        parent.appendChild(child1);
        parent.appendChild(child2);

        // When
        Element result = child1.lastElementSibling();

        // Then
        assertEquals(child2, result);
    }

    @Test
    void testLastElementSiblingNoParent() {
        // Given
        Element element = new Element("element");

        // When
        Element result = element.lastElementSibling();

        // Then
        assertEquals(element, result);
    }
}
