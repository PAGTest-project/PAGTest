
package org.jsoup.nodes;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class Element_emptyTest {

    @Test
    void testEmpty() {
        // Given
        Element element = new Element("div");
        Node child1 = new TextNode("Child1", "");
        Node child2 = new TextNode("Child2", "");
        element.appendChild(child1);
        element.appendChild(child2);

        // When
        Element result = element.empty();

        // Then
        assertTrue(element.childNodes().isEmpty());
        assertNull(child1.parentNode());
        assertNull(child2.parentNode());
        assertEquals(element, result);
    }
}
