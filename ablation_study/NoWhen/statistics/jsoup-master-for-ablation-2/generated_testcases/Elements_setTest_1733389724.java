
package org.jsoup.select;

import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class Elements_setTest {

    @Test
    void testSet() {
        // Given
        Elements elements = new Elements();
        Element oldElement = mock(Element.class);
        Element newElement = mock(Element.class);
        elements.add(oldElement);

        // When
        Element result = elements.set(0, newElement);

        // Then
        assertEquals(oldElement, result);
        verify(oldElement).replaceWith(newElement);
    }

    @Test
    void testSetWithNullElement() {
        // Given
        Elements elements = new Elements();
        elements.add(mock(Element.class));

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> elements.set(0, null));
    }
}
