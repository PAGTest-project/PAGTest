
package org.jsoup.select;

import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class Collector_findFirstTest {

    @Test
    void testFindFirst_ElementFound() {
        // Given
        Evaluator evaluator = mock(Evaluator.class);
        Element root = mock(Element.class);
        Element child = mock(Element.class);

        when(root.stream()).thenReturn(java.util.stream.Stream.of(child));
        when(evaluator.asPredicate(root)).thenReturn(element -> true);

        // When
        Element result = Collector.findFirst(evaluator, root);

        // Then
        assertNotNull(result);
        assertEquals(child, result);
    }

    @Test
    void testFindFirst_NoElementFound() {
        // Given
        Evaluator evaluator = mock(Evaluator.class);
        Element root = mock(Element.class);

        when(root.stream()).thenReturn(java.util.stream.Stream.of());
        when(evaluator.asPredicate(root)).thenReturn(element -> false);

        // When
        Element result = Collector.findFirst(evaluator, root);

        // Then
        assertNull(result);
    }
}
