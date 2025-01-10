
package org.jsoup.nodes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Element_classNamesTest {
    private Element element;

    @BeforeEach
    public void setUp() {
        element = new Element("div");
    }

    @Test
    public void testClassNamesEmpty() {
        assertEquals(0, element.classNames().size());
    }

    @Test
    public void testClassNamesSingleClass() {
        element.addClass("class1");
        assertEquals(1, element.classNames().size());
        assertTrue(element.classNames().contains("class1"));
    }

    @Test
    public void testClassNamesMultipleClasses() {
        element.addClass("class1");
        element.addClass("class2");
        assertEquals(2, element.classNames().size());
        assertTrue(element.classNames().contains("class1"));
        assertTrue(element.classNames().contains("class2"));
    }

    @Test
    public void testClassNamesWithWhitespace() {
        element.attr("class", " class1  class2 ");
        assertEquals(2, element.classNames().size());
        assertTrue(element.classNames().contains("class1"));
        assertTrue(element.classNames().contains("class2"));
    }

    @Test
    public void testClassNamesRemoveClass() {
        element.addClass("class1");
        element.addClass("class2");
        element.removeClass("class1");
        assertEquals(1, element.classNames().size());
        assertTrue(element.classNames().contains("class2"));
    }

    @Test
    public void testClassNamesRemoveNonExistentClass() {
        element.addClass("class1");
        element.removeClass("class2");
        assertEquals(1, element.classNames().size());
        assertTrue(element.classNames().contains("class1"));
    }
}
