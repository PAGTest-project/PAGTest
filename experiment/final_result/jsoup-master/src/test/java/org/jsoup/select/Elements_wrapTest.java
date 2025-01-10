
package org.jsoup.select;

import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class Elements_wrapTest {

    @Test
    void testWrap() {
        // Given
        Elements elements = new Elements();
        Element mockElement = mock(Element.class);
        elements.add(mockElement);

        // When
        Elements result = elements.wrap("<div></div>");

        // Then
        verify(mockElement).wrap("<div></div>");
        assertEquals(elements, result);
    }
}
