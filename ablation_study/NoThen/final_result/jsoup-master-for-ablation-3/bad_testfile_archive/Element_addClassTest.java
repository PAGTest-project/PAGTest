
package org.jsoup.nodes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Element_addClassTest {
    private Element element;

    @BeforeEach
    public void setUp() {
        element = new Element("div");
    }

    @Test
    public void testAddClassSuccess() {
        element.addClass("newClass");
        assertTrue(element.hasClass("newClass"));
    }

    @Test
    public void testAddClassAlreadyExists() {
        element.addClass("existingClass");
        element.addClass("existingClass");
        assertTrue(element.hasClass("existingClass"));
        assertEquals(1, element.classNames().size());
    }

    @Test
    public void testAddClassNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            element.addClass(null);
        });
    }

    @Test
    public void testAddClassEmptyString() {
        element.addClass("");
        assertFalse(element.hasClass(""));
        assertEquals(0, element.classNames().size());
    }

    @Test
    public void testAddClassWithSpaces() {
        element.addClass(" classWithSpaces ");
        assertTrue(element.hasClass("classWithSpaces"));
        assertEquals(1, element.classNames().size());
    }

    @Test
    public void testAddClassMultiple() {
        element.addClass("class1");
        element.addClass("class2");
        assertTrue(element.hasClass("class1"));
        assertTrue(element.hasClass("class2"));
        assertEquals(2, element.classNames().size());
    }
}
