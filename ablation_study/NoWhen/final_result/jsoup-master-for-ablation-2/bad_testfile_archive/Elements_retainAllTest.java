
package org.jsoup.select;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

public class Elements_retainAllTest {
    private Elements elements;

    @BeforeEach
    public void setUp() {
        elements = new Elements();
    }

    @Test
    public void testRetainAllWithMatchingElements() {
        Element el1 = new Element("div");
        Element el2 = new Element("span");
        elements.addAll(Arrays.asList(el1, el2));

        Collection<Element> toRetain = Arrays.asList(el1);
        boolean result = elements.retainAll(toRetain);

        assertTrue(result);
        assertEquals(1, elements.size());
        assertTrue(elements.contains(el1));
        assertFalse(elements.contains(el2));
    }

    @Test
    public void testRetainAllWithNoMatchingElements() {
        Element el1 = new Element("div");
        Element el2 = new Element("span");
        elements.addAll(Arrays.asList(el1, el2));

        Collection<Element> toRetain = Arrays.asList(new Element("p"));
        boolean result = elements.retainAll(toRetain);

        assertTrue(result);
        assertTrue(elements.isEmpty());
    }

    @Test
    public void testRetainAllWithAllMatchingElements() {
        Element el1 = new Element("div");
        Element el2 = new Element("span");
        elements.addAll(Arrays.asList(el1, el2));

        Collection<Element> toRetain = Arrays.asList(el1, el2);
        boolean result = elements.retainAll(toRetain);

        assertFalse(result);
        assertEquals(2, elements.size());
        assertTrue(elements.contains(el1));
        assertTrue(elements.contains(el2));
    }

    @Test
    public void testRetainAllWithEmptyCollection() {
        Element el1 = new Element("div");
        Element el2 = new Element("span");
        elements.addAll(Arrays.asList(el1, el2));

        Collection<Element> toRetain = Arrays.asList();
        boolean result = elements.retainAll(toRetain);

        assertTrue(result);
        assertTrue(elements.isEmpty());
    }

    @Test
    public void testRetainAllWithNullCollection() {
        Element el1 = new Element("div");
        Element el2 = new Element("span");
        elements.addAll(Arrays.asList(el1, el2));

        Collection<Element> toRetain = null;
        assertThrows(NullPointerException.class, () -> elements.retainAll(toRetain));
    }
}
