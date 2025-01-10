
package org.jsoup.select;

import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import java.util.function.UnaryOperator;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Elements_replaceAllTest {

    @Test
    public void testReplaceAll() {
        // Given
        Element element1 = new Element("div");
        Element element2 = new Element("span");
        Elements elements = new Elements(Arrays.asList(element1, element2));

        UnaryOperator<Element> operator = e -> new Element("p");

        // When
        elements.replaceAll(operator);

        // Then
        List<Element> updatedElements = elements.asList();
        assertEquals("p", updatedElements.get(0).tagName());
        assertEquals("p", updatedElements.get(1).tagName());
    }
}
