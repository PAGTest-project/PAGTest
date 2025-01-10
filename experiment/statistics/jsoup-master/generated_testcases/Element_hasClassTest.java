
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
    public void testHasClass_NoAttributes() {
        assertFalse(element.hasClass("testClass"));
    }

    @Test
    public void testHasClass_ExactMatch() {
        element.attr("class", "testClass");
        assertTrue(element.hasClass("testClass"));
    }

    @Test
    public void testHasClass_MultipleClasses() {
        element.attr("class", "class1 class2 class3");
        assertTrue(element.hasClass("class2"));
        assertFalse(element.hasClass("class4"));
    }

    @Test
    public void testHasClass_WhitespaceHandling() {
        element.attr("class", "  class1   class2  ");
        assertTrue(element.hasClass("class1"));
        assertTrue(element.hasClass("class2"));
        assertFalse(element.hasClass("class3"));
    }

    @Test
    public void testHasClass_CaseInsensitivity() {
        element.attr("class", "TestClass");
        assertTrue(element.hasClass("testclass"));
    }

    @Test
    public void testHasClass_EmptyClassAttribute() {
        element.attr("class", "");
        assertFalse(element.hasClass("testClass"));
    }

    @Test
    public void testHasClass_LongerThanAttribute() {
        element.attr("class", "short");
        assertFalse(element.hasClass("longerClass"));
    }

    @Test
    public void testHasClass_LastEntry() {
        element.attr("class", "class1 class2 class3");
        assertTrue(element.hasClass("class3"));
    }
}
