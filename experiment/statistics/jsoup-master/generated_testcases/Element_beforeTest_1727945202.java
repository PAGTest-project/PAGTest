
package org.jsoup.nodes;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class Element_beforeTest {

    @Test
    void testBefore() {
        // Given
        Element element = mock(Element.class);
        String html = "<div>Test</div>";

        // When
        when(element.before(html)).thenCallRealMethod();
        when(super.before(html)).thenReturn(new Element("div"));

        Element result = element.before(html);

        // Then
        assertEquals("div", result.tagName());
    }
}
