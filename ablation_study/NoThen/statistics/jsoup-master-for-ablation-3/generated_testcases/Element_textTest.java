
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
        TextNode textNode = new TextNode("test text");
        element.appendChild(textNode);

        // When
        String result = element.text();

        // Then
        assertEquals("test text", result.trim());
    }
}
