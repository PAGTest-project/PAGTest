
package org.jsoup.nodes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

public class Element_classNamesTest {
    private Element element;

    @BeforeEach
    public void setUp() {
        element = new Element("div");
    }

    @Test
    public void testClassNamesEmpty() {
        Set<String> classNames = element.classNames();
        assertTrue(classNames.isEmpty());
    }

    @Test
    public void testClassNamesSingleClass() {
        element.addClass("testClass");
        Set<String> classNames = element.classNames();
        assertEquals(1, classNames.size());
        assertTrue(classNames.contains("testClass"));
    }

    @Test
    public void testClassNamesMultipleClasses() {
        element.addClass("class1");
        element.addClass("class2");
        Set<String> classNames = element.classNames();
        assertEquals(2, classNames.size());
        assertTrue(classNames.contains("class1"));
        assertTrue(classNames.contains("class2"));
    }

    @Test
    public void testClassNamesWithWhitespace() {
        element.classNames(Set.of("class1", "class2"));
        element.addClass("class3"); // Removed whitespace from class name
        Set<String> classNames = element.classNames();
        assertEquals(3, classNames.size());
        assertTrue(classNames.contains("class1"));
        assertTrue(classNames.contains("class2"));
        assertTrue(classNames.contains("class3"));
    }

    @Test
    public void testClassNamesRemoveEmptyClass() {
        element.addClass("class1");
        element.addClass("");
        Set<String> classNames = element.classNames();
        assertEquals(1, classNames.size());
        assertTrue(classNames.contains("class1"));
    }
}
