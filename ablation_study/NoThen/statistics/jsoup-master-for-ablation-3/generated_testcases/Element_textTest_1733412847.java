
package org.jsoup.nodes;

import org.jsoup.internal.StringUtil;
import org.jsoup.select.NodeTraversor;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class Element_textTest {

    @Test
    void testText() {
        // Given
        Element element = new Element("div");
        StringBuilder mockAccum = new StringBuilder("test text");
        NodeTraversor.traverse(new Element.TextAccumulator(mockAccum), element);

        // When
        String result = element.text();

        // Then
        assertEquals("test text", result);
    }
}
