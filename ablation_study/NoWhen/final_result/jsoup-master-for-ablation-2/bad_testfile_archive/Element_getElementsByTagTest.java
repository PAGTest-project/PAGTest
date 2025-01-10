
package org.jsoup.nodes;

import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class Element_getElementsByTagTest {

    @Test
    void testGetElementsByTag() {
        // Given
        Element element = new Element("div");
        Evaluator.Tag mockEvaluator = mock(Evaluator.Tag.class);
        Elements mockElements = mock(Elements.class);
        Collector mockCollector = mock(Collector.class);

        when(mockCollector.collect(any(Evaluator.Tag.class), eq(element))).thenReturn(mockElements);

        // When
        Elements result = element.getElementsByTag("div");

        // Then
        assertNotNull(result);
        verify(mockCollector).collect(any(Evaluator.Tag.class), eq(element));
    }
}
