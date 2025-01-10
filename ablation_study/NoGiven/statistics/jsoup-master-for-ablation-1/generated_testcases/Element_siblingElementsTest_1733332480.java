
package org.jsoup.nodes;

import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class Element_siblingElementsTest {

    @Test
    void testSiblingElementsWithParent() {
        // Given
        Element parent = mock(Element.class);
        Element child1 = new Element("div");
        Element child2 = new Element("span");
        child1.parentNode = parent;
        child2.parentNode = parent;

        when(parent.childElementsList()).thenReturn(new Elements(child1, child2));

        // When
        Elements siblings = child1.siblingElements();

        // Then
        assertEquals(1, siblings.size());
        assertEquals(child2, siblings.get(0));
    }

    @Test
    void testSiblingElementsWithoutParent() {
        // Given
        Element element = new Element("div");
        element.parentNode = null;

        // When
        Elements siblings = element.siblingElements();

        // Then
        assertEquals(0, siblings.size());
    }
}
