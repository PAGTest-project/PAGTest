
package org.jsoup.nodes;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FormElement_elementsTest {

    private FormElement formElement;

    @BeforeEach
    public void setUp() {
        formElement = new FormElement(Tag.valueOf("form"), null, null);
    }

    @Test
    public void testElementsWithNoLinkedElements() {
        // Given
        Element childElement = new Element(Tag.valueOf("input"), null);
        formElement.appendChild(childElement);

        // When
        Elements result = formElement.elements();

        // Then
        assertEquals(1, result.size());
        assertTrue(result.contains(childElement));
    }

    @Test
    public void testElementsWithLinkedElements() {
        // Given
        Element childElement = new Element(Tag.valueOf("input"), null);
        Element linkedElement = new Element(Tag.valueOf("input"), null);
        formElement.appendChild(childElement);
        formElement.addElement(linkedElement);

        // When
        Elements result = formElement.elements();

        // Then
        assertEquals(2, result.size());
        assertTrue(result.contains(childElement));
        assertTrue(result.contains(linkedElement));
    }

    @Test
    public void testElementsWithLinkedElementsNotInDOM() {
        // Given
        Element childElement = new Element(Tag.valueOf("input"), null);
        Element linkedElement = new Element(Tag.valueOf("input"), null);
        formElement.appendChild(childElement);
        formElement.addElement(linkedElement);
        linkedElement.remove(); // Simulate removal from DOM

        // When
        Elements result = formElement.elements();

        // Then
        assertEquals(1, result.size());
        assertTrue(result.contains(childElement));
    }
}
