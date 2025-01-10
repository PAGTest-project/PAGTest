
package org.jsoup.nodes;

import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class Element_getElementByIdTest {

    @Test
    void testGetElementById_ElementFound() {
        // Given
        Element element = mock(Element.class);
        Elements elements = mock(Elements.class);
        when(elements.size()).thenReturn(1);
        when(elements.get(0)).thenReturn(element);
        when(Collector.collect(any(Evaluator.Id.class), eq(element))).thenReturn(elements);

        // When
        Element result = element.getElementById("testId");

        // Then
        assertEquals(element, result);
    }

    @Test
    void testGetElementById_ElementNotFound() {
        // Given
        Element element = mock(Element.class);
        Elements elements = mock(Elements.class);
        when(elements.size()).thenReturn(0);
        when(Collector.collect(any(Evaluator.Id.class), eq(element))).thenReturn(elements);

        // When
        Element result = element.getElementById("testId");

        // Then
        assertNull(result);
    }
}
