
package org.jsoup.select;

import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class Elements_eachAttrTest {

    @Test
    public void testEachAttr_withMatchingAttribute() {
        // Given
        Element element1 = mock(Element.class);
        Element element2 = mock(Element.class);
        Elements elements = new Elements(List.of(element1, element2));

        when(element1.hasAttr("key")).thenReturn(true);
        when(element1.attr("key")).thenReturn("value1");
        when(element2.hasAttr("key")).thenReturn(true);
        when(element2.attr("key")).thenReturn("value2");

        // When
        List<String> result = elements.eachAttr("key");

        // Then
        assertEquals(2, result.size());
        assertEquals("value1", result.get(0));
        assertEquals("value2", result.get(1));
    }

    @Test
    public void testEachAttr_withoutMatchingAttribute() {
        // Given
        Element element1 = mock(Element.class);
        Element element2 = mock(Element.class);
        Elements elements = new Elements(List.of(element1, element2));

        when(element1.hasAttr("key")).thenReturn(false);
        when(element2.hasAttr("key")).thenReturn(false);

        // When
        List<String> result = elements.eachAttr("key");

        // Then
        assertEquals(0, result.size());
    }
}
