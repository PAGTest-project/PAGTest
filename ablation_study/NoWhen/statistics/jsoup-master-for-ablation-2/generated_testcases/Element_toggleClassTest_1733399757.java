
package org.jsoup.nodes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Element_toggleClassTest {
    private Element element;

    @BeforeEach
    public void setUp() {
        element = new Element("div");
    }

    @Test
    public void testToggleClassAdd() {
        element.attr("class", "initialClass");
        element.toggleClass("newClass");
        assertTrue(element.hasClass("initialClass"));
        assertTrue(element.hasClass("newClass"));
    }

    @Test
    public void testToggleClassRemove() {
        element.attr("class", "initialClass");
        element.toggleClass("initialClass");
        assertFalse(element.hasClass("initialClass"));
    }

    @Test
    public void testToggleClassEmpty() {
        element.toggleClass("newClass");
        assertTrue(element.hasClass("newClass"));
    }

    @Test
    public void testToggleClassMultiple() {
        element.attr("class", "class1 class2");
        element.toggleClass("class1");
        assertFalse(element.hasClass("class1"));
        assertTrue(element.hasClass("class2"));
    }

    @Test
    public void testToggleClassMultipleAdd() {
        element.attr("class", "class1 class2");
        element.toggleClass("class3");
        assertTrue(element.hasClass("class1"));
        assertTrue(element.hasClass("class2"));
        assertTrue(element.hasClass("class3"));
    }
}
