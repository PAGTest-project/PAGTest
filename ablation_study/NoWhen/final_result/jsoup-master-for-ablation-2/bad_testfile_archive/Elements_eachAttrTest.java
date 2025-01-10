
package org.jsoup.select;

import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class Elements_eachAttrTest {

    @Test
    public void testEachAttr_WithMatchingAttribute() {
        // Given
        String attributeKey = "class";
        Element element1 = mock(Element.class);
        Element element2 = mock(Element.class);
        when(element1.hasAttr(attributeKey)).thenReturn(true);
        when(element1.attr(attributeKey)).thenReturn("testClass");
        when(element2.hasAttr(attributeKey)).thenReturn(false);

        Elements elements = new Elements(List.of(element1, element2));

        // When
        List<String> result = elements.eachAttr(attributeKey);

        // Then
        assertEquals(1, result.size());
        assertEquals("testClass", result.get(0));
    }

    @Test
    public void testEachAttr_WithoutMatchingAttribute() {
        // Given
        String attributeKey = "class";
        Element element1 = mock(Element.class);
        Element element2 = mock(Element.class);
        when(element1.hasAttr(attributeKey)).thenReturn(false);
        when(element2.hasAttr(attributeKey)).thenReturn(false);

        Elements elements = new Elements(List.of(element1, element2));

        // When
        List<String> result = elements.eachAttr(attributeKey);

        // Then
        assertEquals(0, result.size());
    }
}
