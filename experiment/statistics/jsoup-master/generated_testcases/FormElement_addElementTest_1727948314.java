
package org.jsoup.nodes;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FormElement_addElementTest {

    private FormElement formElement;

    @BeforeEach
    public void setUp() {
        formElement = new FormElement(Tag.valueOf("form"), null, null);
    }

    @Test
    public void testAddElement() {
        Element element = new Element(Tag.valueOf("input"), null);
        formElement.addElement(element);
        assertEquals(1, formElement.elements().size());
        assertEquals(element, formElement.elements().get(0));
    }
}
