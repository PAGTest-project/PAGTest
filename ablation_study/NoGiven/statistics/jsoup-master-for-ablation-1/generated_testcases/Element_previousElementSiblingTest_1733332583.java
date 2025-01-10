
package org.jsoup.nodes;

import org.junit.jupiter.api.Test;
import org.jspecify.annotations.Nullable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class Element_previousElementSiblingTest {

    @Test
    void testPreviousElementSibling() {
        // Given
        Element parent = new Element("parent");
        Element child1 = new Element("child1");
        Element child2 = new Element("child2");
        Element child3 = new Element("child3");

        parent.appendChild(child1);
        parent.appendChild(child2);
        parent.appendChild(child3);

        // When
        Element result = child3.previousElementSibling();

        // Then
        assertEquals(child2, result);

        // When
        result = child2.previousElementSibling();

        // Then
        assertEquals(child1, result);

        // When
        result = child1.previousElementSibling();

        // Then
        assertNull(result);
    }
}
