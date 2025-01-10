
package org.jsoup.nodes;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FormElement_elementsTest {
    private FormElement formElement;

    @BeforeEach
    public void setUp() {
        formElement = new FormElement(Tag.valueOf("form"), null, null);
    }

    @Test
    public void testElementsWithNoLinkedElements() {
        Elements elements = formElement.elements();
        assertTrue(elements.isEmpty());
    }

    @Test
    public void testElementsWithLinkedElements() {
        Element element1 = new Element(Tag.valueOf("input"), null);
        Element element2 = new Element(Tag.valueOf("input"), null);
        formElement.addElement(element1);
        formElement.addElement(element2);

        Elements elements = formElement.elements();
        assertEquals(2, elements.size());
        assertTrue(elements.contains(element1));
        assertTrue(elements.contains(element2));
    }

    @Test
    public void testElementsWithFormChildren() {
        Element child1 = new Element(Tag.valueOf("input"), null);
        Element child2 = new Element(Tag.valueOf("input"), null);
        formElement.appendChild(child1);
        formElement.appendChild(child2);

        Elements elements = formElement.elements();
        assertEquals(2, elements.size());
        assertTrue(elements.contains(child1));
        assertTrue(elements.contains(child2));
    }

    @Test
    public void testElementsWithLinkedAndFormChildren() {
        Element child1 = new Element(Tag.valueOf("input"), null);
        Element child2 = new Element(Tag.valueOf("input"), null);
        Element linked1 = new Element(Tag.valueOf("input"), null);
        formElement.appendChild(child1);
        formElement.appendChild(child2);
        formElement.addElement(linked1);

        Elements elements = formElement.elements();
        assertEquals(3, elements.size());
        assertTrue(elements.contains(child1));
        assertTrue(elements.contains(child2));
        assertTrue(elements.contains(linked1));
    }

    @Test
    public void testElementsWithDuplicateLinkedElements() {
        Element child1 = new Element(Tag.valueOf("input"), null);
        Element linked1 = new Element(Tag.valueOf("input"), null);
        formElement.appendChild(child1);
        formElement.addElement(linked1);
        formElement.addElement(linked1); // Duplicate linked element

        Elements elements = formElement.elements();
        assertEquals(2, elements.size());
        assertTrue(elements.contains(child1));
        assertTrue(elements.contains(linked1));
    }
}
