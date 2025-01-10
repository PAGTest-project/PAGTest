
package org.jsoup.nodes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Element_hasClassTest {
    private Element element;

    @BeforeEach
    public void setUp() {
        element = new Element("div");
    }

    @Test
    public void testHasClassWhenClassExists() {
        element.attr("class", "testClass");
        assertTrue(element.hasClass("testClass"));
    }

    @Test
    public void testHasClassWhenClassDoesNotExist() {
        element.attr("class", "anotherClass");
        assertFalse(element.hasClass("testClass"));
    }

    @Test
    public void testHasClassWhenClassAttributeIsEmpty() {
        element.attr("class", "");
        assertFalse(element.hasClass("testClass"));
    }

    @Test
    public void testHasClassWhenClassAttributeIsNull() {
        element.attr("class", null);
        assertFalse(element.hasClass("testClass"));
    }

    @Test
    public void testHasClassWhenClassAttributeContainsMultipleClasses() {
        element.attr("class", "class1 class2 class3");
        assertTrue(element.hasClass("class2"));
    }

    @Test
    public void testHasClassWhenClassAttributeContainsWhitespace() {
        element.attr("class", "  class1  class2  ");
        assertTrue(element.hasClass("class1"));
        assertTrue(element.hasClass("class2"));
    }

    @Test
    public void testHasClassWhenClassAttributeContainsMixedCase() {
        element.attr("class", "Class1 class2");
        assertTrue(element.hasClass("class1"));
        assertTrue(element.hasClass("CLASS2"));
    }

    @Test
    public void testHasClassWhenClassAttributeContainsPartialMatch() {
        element.attr("class", "class1 class2");
        assertFalse(element.hasClass("class"));
    }

    @Test
    public void testHasClassWhenClassAttributeContainsExactMatch() {
        element.attr("class", "class");
        assertTrue(element.hasClass("class"));
    }

    @Test
    public void testHasClassWhenClassAttributeContainsExactMatchWithWhitespace() {
        element.attr("class", "  class  ");
        assertTrue(element.hasClass("class"));
    }
}
