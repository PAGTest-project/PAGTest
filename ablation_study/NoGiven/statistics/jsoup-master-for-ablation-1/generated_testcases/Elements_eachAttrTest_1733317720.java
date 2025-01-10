
package org.jsoup.select;

import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class Elements_eachAttrTest {

    @Test
    public void testEachAttr_WithAttributePresent() {
        // Given
        Element element1 = mock(Element.class);
        Element element2 = mock(Element.class);
        when(element1.hasAttr("class")).thenReturn(true);
        when(element2.hasAttr("class")).thenReturn(true);
        when(element1.attr("class")).thenReturn("active");
        when(element2.attr("class")).thenReturn("inactive");

        Elements elements = new Elements(Arrays.asList(element1, element2));

        // When
        List<String> result = elements.eachAttr("class");

        // Then
        assertEquals(Arrays.asList("active", "inactive"), result);
    }

    @Test
    public void testEachAttr_WithAttributeNotPresent() {
        // Given
        Element element1 = mock(Element.class);
        Element element2 = mock(Element.class);
        when(element1.hasAttr("class")).thenReturn(false);
        when(element2.hasAttr("class")).thenReturn(false);

        Elements elements = new Elements(Arrays.asList(element1, element2));

        // When
        List<String> result = elements.eachAttr("class");

        // Then
        assertEquals(Collections.emptyList(), result);
    }
}
