
package org.jsoup.select;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;
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
        Collection<Element> collection = Collections.emptyList();
        assertFalse(elements.retainAll(collection));
    }

    @Test
    public void testRetainAllWithNonEmptyCollection() {
        Element element1 = new Element("div");
        Element element2 = new Element("span");
        elements.addAll(Arrays.asList(element1, element2));

        Collection<Element> collection = Collections.singletonList(element1);
        assertTrue(elements.retainAll(collection));
        assertEquals(1, elements.size());
        assertTrue(elements.contains(element1));
        assertFalse(elements.contains(element2));
    }

    @Test
    public void testRetainAllWithAllElementsInCollection() {
        Element element1 = new Element("div");
        Element element2 = new Element("span");
        elements.addAll(Arrays.asList(element1, element2));

        Collection<Element> collection = Arrays.asList(element1, element2);
        assertFalse(elements.retainAll(collection));
        assertEquals(2, elements.size());
        assertTrue(elements.contains(element1));
        assertTrue(elements.contains(element2));
    }

    @Test
    public void testRetainAllWithDifferentElementsInCollection() {
        Element element1 = new Element("div");
        Element element2 = new Element("span");
        elements.addAll(Arrays.asList(element1, element2));

        Collection<Element> collection = Collections.singletonList(new Element("p"));
        assertTrue(elements.retainAll(collection));
        assertTrue(elements.isEmpty());
    }

    @Test
    public void testRetainAllWithDocumentElements() {
        String html = "<div id='1'></div><span id='2'></span>";
        Document doc = Jsoup.parse(html);
        elements = doc.select("*");

        Collection<Element> collection = Collections.singletonList(doc.select("div").first());
        assertTrue(elements.retainAll(collection));
        assertEquals(1, elements.size());
        assertTrue(elements.contains(doc.select("div").first()));
        assertFalse(elements.contains(doc.select("span").first()));
    }
}
