
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
        Element sibling1 = new Element("div");
        Element sibling2 = new Element("span");
        Element current = new Element("p");
        current.parentNode = parent;

        when(parent.childElementsList()).thenReturn(new Elements(sibling1, current, sibling2));

        // When
        Elements siblings = current.siblingElements();

        // Then
        assertEquals(2, siblings.size());
        assertTrue(siblings.contains(sibling1));
        assertTrue(siblings.contains(sibling2));
    }

    @Test
    void testSiblingElementsWithoutParent() {
        // Given
        Element current = new Element("p");
        current.parentNode = null;

        // When
        Elements siblings = current.siblingElements();

        // Then
        assertEquals(0, siblings.size());
    }
}
