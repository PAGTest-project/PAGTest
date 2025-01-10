
package org.jsoup.select;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class Elements_valTest {

    @Test
    void testVal_withElements() {
        Elements elements = Mockito.mock(Elements.class);
        Element firstElement = Mockito.mock(Element.class);

        when(elements.size()).thenReturn(1);
        when(elements.first()).thenReturn(firstElement);
        when(firstElement.val()).thenReturn("value");

        assertEquals("value", elements.val());
    }

    @Test
    void testVal_withoutElements() {
        Elements elements = Mockito.mock(Elements.class);

        when(elements.size()).thenReturn(0);

        assertEquals("", elements.val());
    }
}
