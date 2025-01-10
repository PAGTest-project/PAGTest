
package org.jsoup.select;

import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class Elements_isTest {

    @Test
    void testIs_ElementMatchesQuery() {
        // Given
        Element mockElement = mock(Element.class);
        Elements elements = new Elements(1);
        elements.add(mockElement);
        Evaluator mockEvaluator = mock(Evaluator.class);
        QueryParser mockQueryParser = mock(QueryParser.class);
        when(mockQueryParser.parse("query")).thenReturn(mockEvaluator);
        when(mockElement.is(mockEvaluator)).thenReturn(true);

        // When
        boolean result = elements.is("query");

        // Then
        assertTrue(result);
    }

    @Test
    void testIs_NoElementMatchesQuery() {
        // Given
        Element mockElement = mock(Element.class);
        Elements elements = new Elements(1);
        elements.add(mockElement);
        Evaluator mockEvaluator = mock(Evaluator.class);
        QueryParser mockQueryParser = mock(QueryParser.class);
        when(mockQueryParser.parse("query")).thenReturn(mockEvaluator);
        when(mockElement.is(mockEvaluator)).thenReturn(false);

        // When
        boolean result = elements.is("query");

        // Then
        assertFalse(result);
    }
}
