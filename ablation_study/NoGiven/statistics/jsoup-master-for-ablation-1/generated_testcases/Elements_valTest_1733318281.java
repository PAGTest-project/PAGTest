
package org.jsoup.select;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class Elements_valTest {

    @Test
    void testVal_withElements() {
        Elements elements = new Elements();
        Element firstElement = Mockito.mock(Element.class);

        elements.add(firstElement);
        when(firstElement.val()).thenReturn("value");

        assertEquals("value", elements.val());
    }

    @Test
    void testVal_withoutElements() {
        Elements elements = new Elements();

        assertEquals("", elements.val());
    }
}
