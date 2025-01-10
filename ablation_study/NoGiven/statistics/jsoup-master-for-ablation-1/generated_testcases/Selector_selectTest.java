
package org.jsoup.select;

import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class Selector_selectTest {

    @Test
    void testSelectWithValidInputs() {
        Evaluator evaluator = mock(Evaluator.class);
        Element root = mock(Element.class);
        Elements expected = new Elements();

        when(Collector.collect(evaluator, root)).thenReturn(expected);

        Elements result = Selector.select(evaluator, root);

        assertEquals(expected, result);
    }

    @Test
    void testSelectWithNullEvaluator() {
        Element root = mock(Element.class);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Selector.select(null, root);
        });

        assertEquals("Object must not be null", exception.getMessage());
    }

    @Test
    void testSelectWithNullRoot() {
        Evaluator evaluator = mock(Evaluator.class);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Selector.select(evaluator, null);
        });

        assertEquals("Object must not be null", exception.getMessage());
    }
}
