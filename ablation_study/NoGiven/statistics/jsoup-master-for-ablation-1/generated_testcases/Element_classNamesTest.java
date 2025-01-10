
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
    public void testClassNamesWithSingleClass() {
        element.attr("class", "single");
        Set<String> classNames = element.classNames();
        assertEquals(1, classNames.size());
        assertTrue(classNames.contains("single"));
    }

    @Test
    public void testClassNamesWithMultipleClasses() {
        element.attr("class", "first second third");
        Set<String> classNames = element.classNames();
        assertEquals(3, classNames.size());
        assertTrue(classNames.contains("first"));
        assertTrue(classNames.contains("second"));
        assertTrue(classNames.contains("third"));
    }

    @Test
    public void testClassNamesWithEmptyClass() {
        element.attr("class", "");
        Set<String> classNames = element.classNames();
        assertTrue(classNames.isEmpty());
    }

    @Test
    public void testClassNamesWithWhitespaceOnly() {
        element.attr("class", "   ");
        Set<String> classNames = element.classNames();
        assertTrue(classNames.isEmpty());
    }

    @Test
    public void testClassNamesWithLeadingAndTrailingWhitespace() {
        element.attr("class", "  first second  ");
        Set<String> classNames = element.classNames();
        assertEquals(2, classNames.size());
        assertTrue(classNames.contains("first"));
        assertTrue(classNames.contains("second"));
    }
}
