
package org.jsoup.select;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

public class Elements_removeAllTest {
    private Elements elements;

    @BeforeEach
    public void setUp() {
        elements = new Elements();
    }

    @Test
    public void testRemoveAllWithEmptyCollection() {
        Collection<Element> emptyCollection = Arrays.asList();
        assertFalse(elements.removeAll(emptyCollection));
    }

    @Test
    public void testRemoveAllWithNonEmptyCollection() {
        Document doc = Jsoup.parse("<div id='1'></div><div id='2'></div>");
        elements.addAll(doc.select("div"));

        Collection<Element> elementsToRemove = Arrays.asList(doc.select("div#1").first());
        assertTrue(elements.removeAll(elementsToRemove));
        assertEquals(1, elements.size());
        assertFalse(elements.removeAll(elementsToRemove));
    }

    @Test
    public void testRemoveAllWithAllElements() {
        Document doc = Jsoup.parse("<div id='1'></div><div id='2'></div>");
        elements.addAll(doc.select("div"));

        Collection<Element> allElements = Arrays.asList(doc.select("div").first(), doc.select("div").last());
        assertTrue(elements.removeAll(allElements));
        assertTrue(elements.isEmpty());
    }

    @Test
    public void testRemoveAllWithNonexistentElements() {
        Document doc = Jsoup.parse("<div id='1'></div><div id='2'></div>");
        elements.addAll(doc.select("div"));

        Collection<Element> nonexistentElements = Arrays.asList(new Element("div"));
        assertFalse(elements.removeAll(nonexistentElements));
        assertEquals(2, elements.size());
    }
}
