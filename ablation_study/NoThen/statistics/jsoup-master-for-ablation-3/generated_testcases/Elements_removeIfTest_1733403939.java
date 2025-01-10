
package org.jsoup.select;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.*;

public class Elements_removeIfTest {
    private Elements elements;

    @BeforeEach
    public void setUp() {
        elements = new Elements();
    }

    @Test
    public void testRemoveIfWithNoElements() {
        Predicate<Element> filter = e -> e.tagName().equals("div");
        assertFalse(elements.removeIf(filter));
    }

    @Test
    public void testRemoveIfWithMatchingElements() {
        Element div1 = new Element("div");
        Element div2 = new Element("div");
        Element span = new Element("span");
        elements.addAll(Arrays.asList(div1, div2, span));

        Predicate<Element> filter = e -> e.tagName().equals("div");
        assertTrue(elements.removeIf(filter));
        assertEquals(1, elements.size());
        assertEquals("span", elements.get(0).tagName());
    }

    @Test
    public void testRemoveIfWithNoMatchingElements() {
        Element span1 = new Element("span");
        Element span2 = new Element("span");
        elements.addAll(Arrays.asList(span1, span2));

        Predicate<Element> filter = e -> e.tagName().equals("div");
        assertFalse(elements.removeIf(filter));
        assertEquals(2, elements.size());
        assertEquals("span", elements.get(0).tagName());
        assertEquals("span", elements.get(1).tagName());
    }

    @Test
    public void testRemoveIfWithAllMatchingElements() {
        Element div1 = new Element("div");
        Element div2 = new Element("div");
        elements.addAll(Arrays.asList(div1, div2));

        Predicate<Element> filter = e -> e.tagName().equals("div");
        assertTrue(elements.removeIf(filter));
        assertTrue(elements.isEmpty());
    }

    @Test
    public void testRemoveIfWithComplexFilter() {
        Element div = new Element("div");
        Element span = new Element("span");
        Element p = new Element("p");
        elements.addAll(Arrays.asList(div, span, p));

        Predicate<Element> filter = e -> e.tagName().equals("div") || e.tagName().equals("p");
        assertTrue(elements.removeIf(filter));
        assertEquals(1, elements.size());
        assertEquals("span", elements.get(0).tagName());
    }

    @Test
    public void testRemoveIfWithSelectMethod() {
        Document doc = Jsoup.parse("<div class='test'></div><span></span>");
        elements = doc.select(".test");

        Predicate<Element> filter = e -> e.className().equals("test");
        assertTrue(elements.removeIf(filter));
        assertTrue(elements.isEmpty());
    }

    @Test
    public void testRemoveIfWithRemoveMethod() {
        Element div = new Element("div");
        Element span = new Element("span");
        elements.addAll(Arrays.asList(div, span));

        Predicate<Element> filter = e -> e.tagName().equals("div");
        assertTrue(elements.removeIf(filter));
        assertEquals(1, elements.size());
        assertEquals("span", elements.get(0).tagName());

        elements.remove();
        assertTrue(elements.isEmpty());
    }

    @Test
    public void testRemoveIfWithClearMethod() {
        Element div = new Element("div");
        Element span = new Element("span");
        elements.addAll(Arrays.asList(div, span));

        Predicate<Element> filter = e -> e.tagName().equals("div");
        assertTrue(elements.removeIf(filter));
        assertEquals(1, elements.size());
        assertEquals("span", elements.get(0).tagName());

        elements.clear();
        assertTrue(elements.isEmpty());
    }
}
