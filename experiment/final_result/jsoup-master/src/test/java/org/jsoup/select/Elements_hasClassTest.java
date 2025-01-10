
package org.jsoup.select;

import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.List;

class Elements_hasClassTest {

    @Test
    void testHasClass_ClassExists() {
        // Given
        Element mockElement = mock(Element.class);
        Elements elements = new Elements(List.of(mockElement));
        when(mockElement.hasClass("testClass")).thenReturn(true);

        // When
        boolean result = elements.hasClass("testClass");

        // Then
        assertTrue(result);
    }

    @Test
    void testHasClass_ClassDoesNotExist() {
        // Given
        Element mockElement = mock(Element.class);
        Elements elements = new Elements(List.of(mockElement));
        when(mockElement.hasClass("testClass")).thenReturn(false);

        // When
        boolean result = elements.hasClass("testClass");

        // Then
        assertFalse(result);
    }
}
