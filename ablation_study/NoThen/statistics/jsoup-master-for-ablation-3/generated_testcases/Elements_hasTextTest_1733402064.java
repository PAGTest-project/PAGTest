
package org.jsoup.select;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Elements_hasTextTest {
    private Elements elements;

    @BeforeEach
    public void setUp() {
        elements = new Elements();
    }

    @Test
    public void testHasTextWithText() {
        Element element = new Element("p");
        element.text("Sample text");
        elements.add(element);
        assertTrue(elements.hasText());
    }

    @Test
    public void testHasTextWithoutText() {
        Element element = new Element("p");
        elements.add(element);
        assertFalse(elements.hasText());
    }

    @Test
    public void testHasTextWithMultipleElements() {
        Element element1 = new Element("p");
        element1.text("Sample text 1");
        Element element2 = new Element("p");
        element2.text("Sample text 2");
        Element element3 = new Element("p");
        elements.add(element1);
        elements.add(element2);
        elements.add(element3);
        assertTrue(elements.hasText());
    }

    @Test
    public void testHasTextWithNoElements() {
        assertFalse(elements.hasText());
    }

    @Test
    public void testHasTextWithEmptyText() {
        Element element = new Element("p");
        element.text("");
        elements.add(element);
        assertFalse(elements.hasText());
    }

    @Test
    public void testHasTextWithWhitespaceText() {
        Element element = new Element("p");
        element.text("   ");
        elements.add(element);
        assertFalse(elements.hasText());
    }

    @Test
    public void testHasTextWithMixedElements() {
        Element element1 = new Element("p");
        element1.text("Sample text 1");
        Element element2 = new Element("p");
        element2.text("");
        Element element3 = new Element("p");
        element3.text("Sample text 2");
        elements.add(element1);
        elements.add(element2);
        elements.add(element3);
        assertTrue(elements.hasText());
    }
}
