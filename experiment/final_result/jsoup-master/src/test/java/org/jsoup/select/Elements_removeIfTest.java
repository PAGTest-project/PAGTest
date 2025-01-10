
package org.jsoup.select;

import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.*;

class Elements_removeIfTest {

    @Test
    void testRemoveIf() {
        // Given
        Elements elements = new Elements(Arrays.asList(
            new Element("div"),
            new Element("span"),
            new Element("div")
        ));
        Predicate<Element> filter = el -> "div".equals(el.tagName());

        // When
        boolean anyRemoved = elements.removeIf(filter);

        // Then
        assertTrue(anyRemoved);
        assertEquals(1, elements.size());
        assertEquals("span", elements.get(0).tagName());
    }
}
