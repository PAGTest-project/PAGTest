
package org.jsoup.nodes;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class Document_bodyTest {

    @Test
    void testBody_ExistingBody() {
        // Given
        Document document = new Document("baseUri");
        Element mockHtml = mock(Element.class);
        Element mockBody = mock(Element.class);
        when(mockHtml.firstElementChild()).thenReturn(mockBody);
        when(mockBody.nameIs("body")).thenReturn(true);
        when(document.htmlEl()).thenReturn(mockHtml);

        // When
        Element result = document.body();

        // Then
        assertEquals(mockBody, result);
    }

    @Test
    void testBody_NoBody_AppendsNewBody() {
        // Given
        Document document = new Document("baseUri");
        Element mockHtml = mock(Element.class);
        when(mockHtml.firstElementChild()).thenReturn(null);
        when(document.htmlEl()).thenReturn(mockHtml);
        when(mockHtml.appendElement("body")).thenReturn(mock(Element.class));

        // When
        Element result = document.body();

        // Then
        assertNotNull(result);
        verify(mockHtml).appendElement("body");
    }
}
