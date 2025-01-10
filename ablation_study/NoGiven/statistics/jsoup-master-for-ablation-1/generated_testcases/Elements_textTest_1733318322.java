
package org.jsoup.select;

import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

public class Elements_textTest {

    @Test
    public void testText() {
        // Given
        Element element1 = mock(Element.class);
        Element element2 = mock(Element.class);
        when(element1.text()).thenReturn("Hello");
        when(element2.text()).thenReturn("World");
        List<Element> elements = Arrays.asList(element1, element2);
        Elements elementsObj = new Elements(elements);

        // When
        String result = elementsObj.text();

        // Then
        assertEquals("Hello World", result);
    }
}
