
package org.jsoup.nodes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

public class Element_classNamesTest {
    private Element element;

    @BeforeEach
    public void setUp() {
        element = new Element("div");
    }

    @Test
    public void testClassNamesWithSingleClass() {
        element.attr("class", "testClass");
        Set<String> classNames = element.classNames();
        assertEquals(1, classNames.size());
        assertTrue(classNames.contains("testClass"));
    }

    @Test
    public void testClassNamesWithMultipleClasses() {
        element.attr("class", "testClass1 testClass2");
        Set<String> classNames = element.classNames();
        assertEquals(2, classNames.size());
        assertTrue(classNames.contains("testClass1"));
        assertTrue(classNames.contains("testClass2"));
    }

    @Test
    public void testClassNamesWithEmptyClass() {
        element.attr("class", "");
        Set<String> classNames = element.classNames();
        assertEquals(0, classNames.size());
    }

    @Test
    public void testClassNamesWithWhitespaceOnly() {
        element.attr("class", "   ");
        Set<String> classNames = element.classNames();
        assertEquals(0, classNames.size());
    }

    @Test
    public void testClassNamesAfterAddingClass() {
        element.attr("class", "initialClass");
        element.addClass("addedClass");
        Set<String> classNames = element.classNames();
        assertEquals(2, classNames.size());
        assertTrue(classNames.contains("initialClass"));
        assertTrue(classNames.contains("addedClass"));
    }

    @Test
    public void testClassNamesAfterRemovingClass() {
        element.attr("class", "initialClass toRemoveClass");
        element.removeClass("toRemoveClass");
        Set<String> classNames = element.classNames();
        assertEquals(1, classNames.size());
        assertTrue(classNames.contains("initialClass"));
    }
}
