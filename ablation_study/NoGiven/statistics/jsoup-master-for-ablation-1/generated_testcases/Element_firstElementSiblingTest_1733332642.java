
package org.jsoup.nodes;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class Element_firstElementSiblingTest {

    @Test
    void testFirstElementSiblingWithParent() {
        Element parent = mock(Element.class);
        Element sibling = mock(Element.class);
        Element element = new Element("div");
        element.parentNode = parent;

        when(parent.firstElementChild()).thenReturn(sibling);

        assertEquals(sibling, element.firstElementSibling());
    }

    @Test
    void testFirstElementSiblingWithoutParent() {
        Element element = new Element("div");
        element.parentNode = null;

        assertEquals(element, element.firstElementSibling());
    }
}
