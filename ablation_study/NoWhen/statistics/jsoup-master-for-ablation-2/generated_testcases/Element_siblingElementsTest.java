
package org.jsoup.nodes;

import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Element_siblingElementsTest {

    @Test
    void testSiblingElementsWithParent() {
        // Given
        Element parent = new Element("parent");
        Element child1 = new Element("child1");
        Element child2 = new Element("child2");
        parent.appendChild(child1);
        parent.appendChild(child2);

        // When
        Elements siblings = child1.siblingElements();

        // Then
        assertEquals(1, siblings.size());
        assertTrue(siblings.contains(child2));
    }

    @Test
    void testSiblingElementsWithoutParent() {
        // Given
        Element child = new Element("child");

        // When
        Elements siblings = child.siblingElements();

        // Then
        assertEquals(0, siblings.size());
    }
}
