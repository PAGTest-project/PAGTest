
package org.jsoup.select;

import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;
import java.util.Collections;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class Collector_collectTest {

    @Test
    public void testCollect() {
        // Given
        Evaluator evaluator = mock(Evaluator.class);
        Element root = mock(Element.class);
        Element child = mock(Element.class);
        when(root.stream()).thenReturn(Collections.singletonList(child).stream());
        when(evaluator.asPredicate(root)).thenReturn(element -> true);

        // When
        Elements result = Collector.collect(evaluator, root);

        // Then
        assertEquals(1, result.size());
        verify(evaluator).reset();
        verify(root).stream();
        verify(evaluator).asPredicate(root);
    }
}
