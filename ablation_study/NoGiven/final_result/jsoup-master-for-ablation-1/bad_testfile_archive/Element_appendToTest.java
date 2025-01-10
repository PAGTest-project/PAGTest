
package org.jsoup.nodes;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class Element_appendToTest {

    @Test
    void testAppendTo() {
        // Given
        Element parent = mock(Element.class);
        Element child = new Element("div");

        // When
        Element result = child.appendTo(parent);

        // Then
        verify(parent).appendChild(child);
        assertEquals(child, result);
    }

    @Test
    void testAppendToWithNullParent() {
        // Given
        Element child = new Element("div");

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> child.appendTo(null));
    }
}
