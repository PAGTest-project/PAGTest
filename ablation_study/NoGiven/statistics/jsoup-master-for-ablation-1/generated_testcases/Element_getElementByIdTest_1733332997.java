
package org.jsoup.nodes;

import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class Element_getElementByIdTest {

    @Test
    void testGetElementById_ElementFound() {
        // Given
        Element element = new Element("div");
        element.attr("id", "testId");
        Elements elements = new Elements(element);

        // When
        when(Collector.collect(any(Evaluator.Id.class), eq(element))).thenReturn(elements);
        Element result = element.getElementById("testId");

        // Then
        assertNotNull(result);
        assertEquals("testId", result.id());
    }

    @Test
    void testGetElementById_ElementNotFound() {
        // Given
        Element element = new Element("div");
        Elements elements = new Elements();

        // When
        when(Collector.collect(any(Evaluator.Id.class), eq(element))).thenReturn(elements);
        Element result = element.getElementById("nonExistentId");

        // Then
        assertNull(result);
    }
}
