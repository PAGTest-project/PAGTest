
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
    public void testHasClass_ClassExists() {
        element.addClass("testClass");
        assertTrue(element.hasClass("testClass"));
    }

    @Test
    public void testHasClass_ClassDoesNotExist() {
        element.addClass("testClass");
        assertFalse(element.hasClass("nonExistentClass"));
    }

    @Test
    public void testHasClass_EmptyClassAttribute() {
        assertFalse(element.hasClass("testClass"));
    }

    @Test
    public void testHasClass_MultipleClasses() {
        element.addClass("class1 class2 class3");
        assertTrue(element.hasClass("class2"));
    }

    @Test
    public void testHasClass_CaseInsensitive() {
        element.addClass("TestClass");
        assertTrue(element.hasClass("testclass"));
    }

    @Test
    public void testHasClass_AfterRemovingClass() {
        element.addClass("testClass");
        element.removeClass("testClass");
        assertFalse(element.hasClass("testClass"));
    }

    @Test
    public void testHasClass_NullAttributes() {
        element.attributes = null;
        assertFalse(element.hasClass("testClass"));
    }
}
