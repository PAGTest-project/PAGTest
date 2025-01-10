
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
    public void testHasClassWhenClassIsPresent() {
        element.addClass("testClass");
        assertTrue(element.hasClass("testClass"));
    }

    @Test
    public void testHasClassWhenClassIsNotPresent() {
        element.addClass("anotherClass");
        assertFalse(element.hasClass("testClass"));
    }

    @Test
    public void testHasClassWhenAttributesIsNull() {
        element.attributes().remove("class");
        assertFalse(element.hasClass("testClass"));
    }

    @Test
    public void testHasClassWhenClassAttributeIsEmpty() {
        element.attributes().put("class", "");
        assertFalse(element.hasClass("testClass"));
    }

    @Test
    public void testHasClassWhenClassAttributeIsWhitespace() {
        element.attributes().put("class", "   ");
        assertFalse(element.hasClass("testClass"));
    }

    @Test
    public void testHasClassWhenClassAttributeContainsMultipleClasses() {
        element.attributes().put("class", "class1 class2 class3");
        assertTrue(element.hasClass("class2"));
    }

    @Test
    public void testHasClassWhenClassAttributeContainsMultipleClassesWithWhitespace() {
        element.attributes().put("class", " class1  class2   class3 ");
        assertTrue(element.hasClass("class2"));
    }

    @Test
    public void testHasClassWhenClassAttributeContainsPartialMatch() {
        element.attributes().put("class", "class1 class2 class3");
        assertFalse(element.hasClass("class"));
    }

    @Test
    public void testHasClassWhenClassAttributeContainsExactMatchWithDifferentCase() {
        element.attributes().put("class", "class1 CLASS2 class3");
        assertTrue(element.hasClass("class2"));
    }

    @Test
    public void testHasClassWhenClassAttributeContainsExactMatchWithDifferentCaseAndWhitespace() {
        element.attributes().put("class", " class1  CLASS2   class3 ");
        assertTrue(element.hasClass("class2"));
    }
}
