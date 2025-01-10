
package org.jsoup.select;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Elements_retainAllTest {

    @Test
    void testRetainAll_NoElementsRemoved() {
        Elements elements = new Elements(Arrays.asList(
                new Element("div"),
                new Element("span")
        ));
        List<Element> collection = Arrays.asList(
                new Element("div"),
                new Element("span")
        );

        boolean result = elements.retainAll(collection);

        assertFalse(result);
        assertEquals(2, elements.size());
    }

    @Test
    void testRetainAll_ElementsRemoved() {
        Elements elements = new Elements(Arrays.asList(
                new Element("div"),
                new Element("span"),
                new Element("p")
        ));
        List<Element> collection = Arrays.asList(
                new Element("div"),
                new Element("span")
        );

        boolean result = elements.retainAll(collection);

        assertTrue(result);
        assertEquals(2, elements.size());
    }

    @Test
    void testRetainAll_EmptyCollection() {
        Elements elements = new Elements(Arrays.asList(
                new Element("div"),
                new Element("span")
        ));
        List<Element> collection = Collections.emptyList();

        boolean result = elements.retainAll(collection);

        assertTrue(result);
        assertEquals(0, elements.size());
    }
}
