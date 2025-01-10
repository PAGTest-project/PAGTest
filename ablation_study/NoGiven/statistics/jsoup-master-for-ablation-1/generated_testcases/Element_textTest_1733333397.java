
package org.jsoup.nodes;

import org.jsoup.helper.StringUtil;
import org.jsoup.select.NodeTraversor;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class Element_textTest {

    @Test
    void testText() {
        // Given
        Element element = new Element("div");
        TextNode textNode = new TextNode("Hello World");
        element.appendChild(textNode);

        // When
        String result = element.text();

        // Then
        assertEquals("Hello World", result.trim());
    }
}
