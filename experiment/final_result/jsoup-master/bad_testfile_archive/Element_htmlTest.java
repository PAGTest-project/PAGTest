
package org.jsoup.nodes;

import org.jsoup.internal.StringUtil;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class Element_htmlTest {

    @Test
    public void testHtml_prettyPrintTrue() {
        // Given
        Element element = new Element("div");
        StringBuilder mockBuilder = new StringBuilder("test content");
        NodeUtils.OutputSettings mockOutputSettings = mock(NodeUtils.OutputSettings.class);
        when(NodeUtils.outputSettings(element)).thenReturn(mockOutputSettings);
        when(mockOutputSettings.prettyPrint()).thenReturn(true);

        // When
        String result = element.html();

        // Then
        assertEquals("test content", result);
    }

    @Test
    public void testHtml_prettyPrintFalse() {
        // Given
        Element element = new Element("div");
        StringBuilder mockBuilder = new StringBuilder("test content");
        NodeUtils.OutputSettings mockOutputSettings = mock(NodeUtils.OutputSettings.class);
        when(NodeUtils.outputSettings(element)).thenReturn(mockOutputSettings);
        when(mockOutputSettings.prettyPrint()).thenReturn(false);

        // When
        String result = element.html();

        // Then
        assertEquals("test content", result);
    }
}
