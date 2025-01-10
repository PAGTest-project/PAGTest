
package org.jsoup.select;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

public class Elements_retainAllTest {
    private Elements elements;

    @BeforeEach
    public void setUp() {
        elements = new Elements();
    }

    @Test
    public void testRetainAllWithEmptyCollection() {
        elements.addAll(Arrays.asList(new Element("div"), new Element("span")));
        assertFalse(elements.retainAll(Collections.emptyList()));
        assertEquals(2, elements.size());
    }

    @Test
    public void testRetainAllWithNonEmptyCollection() {
        Element div = new Element("div");
        Element span = new Element("span");
        elements.addAll(Arrays.asList(div, span));
        assertTrue(elements.retainAll(Collections.singletonList(div)));
        assertEquals(1, elements.size());
        assertTrue(elements.contains(div));
        assertFalse(elements.contains(span));
    }

    @Test
    public void testRetainAllWithAllElementsToRetain() {
        Element div = new Element("div");
        Element span = new Element("span");
        elements.addAll(Arrays.asList(div, span));
        assertFalse(elements.retainAll(Arrays.asList(div, span)));
        assertEquals(2, elements.size());
    }

    @Test
    public void testRetainAllWithNoElementsToRetain() {
        Element div = new Element("div");
        Element span = new Element("span");
        elements.addAll(Arrays.asList(div, span));
        assertTrue(elements.retainAll(Collections.singletonList(new Element("p"))));
        assertTrue(elements.isEmpty());
    }

    @Test
    public void testRetainAllWithNullCollection() {
        elements.addAll(Arrays.asList(new Element("div"), new Element("span")));
        assertThrows(NullPointerException.class, () -> elements.retainAll(null));
    }
}
