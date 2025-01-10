
package org.jsoup.select;

import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class Elements_hasClassTest {

    @Test
    void testHasClass_ClassPresent() {
        Element mockElement = mock(Element.class);
        when(mockElement.hasClass("testClass")).thenReturn(true);

        Elements elements = new Elements();
        elements.add(mockElement);

        assertTrue(elements.hasClass("testClass"));
    }

    @Test
    void testHasClass_ClassNotPresent() {
        Element mockElement = mock(Element.class);
        when(mockElement.hasClass("testClass")).thenReturn(false);

        Elements elements = new Elements();
        elements.add(mockElement);

        assertFalse(elements.hasClass("testClass"));
    }
}
