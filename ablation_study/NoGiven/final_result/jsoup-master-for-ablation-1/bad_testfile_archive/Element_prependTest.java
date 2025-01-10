
package org.jsoup.nodes;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class Element_prependTest {

    @Test
    void testPrepend_ValidHtml() {
        // Given
        Element element = new Element("div");
        String html = "<p>Test</p>";
        Node node = mock(Node.class);
        List<Node> nodes = Collections.singletonList(node);

        Parser parser = mock(Parser.class);
        when(parser.parseFragmentInput(html, element, element.baseUri())).thenReturn(nodes);

        // When
        Element result = element.prepend(html);

        // Then
        assertEquals(element, result);
        verify(parser).parseFragmentInput(html, element, element.baseUri());
        verify(element).addChildren(0, nodes.toArray(new Node[0]));
    }

    @Test
    void testPrepend_NullHtml() {
        // Given
        Element element = new Element("div");
        String html = null;

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> element.prepend(html));
    }
}
