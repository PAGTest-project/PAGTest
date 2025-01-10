
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
        element.addClass("class1");
        Set<String> classNames = element.classNames();
        assertEquals(1, classNames.size());
        assertTrue(classNames.contains("class1"));
    }

    @Test
    public void testClassNamesWithMultipleClasses() {
        element.addClass("class1");
        element.addClass("class2");
        Set<String> classNames = element.classNames();
        assertEquals(2, classNames.size());
        assertTrue(classNames.contains("class1"));
        assertTrue(classNames.contains("class2"));
    }

    @Test
    public void testClassNamesWithEmptyClass() {
        element.addClass("");
        Set<String> classNames = element.classNames();
        assertEquals(0, classNames.size());
    }

    @Test
    public void testClassNamesWithWhitespaceClass() {
        element.addClass("   ");
        Set<String> classNames = element.classNames();
        assertEquals(0, classNames.size());
    }

    @Test
    public void testClassNamesWithMixedClasses() {
        element.addClass("class1");
        element.addClass("   ");
        element.addClass("class2");
        Set<String> classNames = element.classNames();
        assertEquals(2, classNames.size());
        assertTrue(classNames.contains("class1"));
        assertTrue(classNames.contains("class2"));
    }
}
