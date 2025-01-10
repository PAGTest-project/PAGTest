
package org.jsoup.select;

import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class Elements_tagNameTest {

    @Test
    public void testTagName() {
        // Given
        Element mockElement = mock(Element.class);
        Elements elements = new Elements(mockElement);
        String tagName = "div";

        // When
        Elements result = elements.tagName(tagName);

        // Then
        verify(mockElement).tagName(tagName);
        assertEquals(elements, result);
    }
}
