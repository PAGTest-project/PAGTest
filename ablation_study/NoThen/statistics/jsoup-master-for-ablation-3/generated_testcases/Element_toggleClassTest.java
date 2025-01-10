
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
        element.toggleClass("newClass");
        assertTrue(element.hasClass("newClass"));
    }

    @Test
    public void testToggleClassRemove() {
        element.addClass("existingClass");
        element.toggleClass("existingClass");
        assertFalse(element.hasClass("existingClass"));
    }

    @Test
    public void testToggleClassNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            element.toggleClass(null);
        });
    }

    @Test
    public void testToggleClassNoChange() {
        element.addClass("class1");
        element.addClass("class2");
        element.toggleClass("class3");
        assertTrue(element.hasClass("class1"));
        assertTrue(element.hasClass("class2"));
        assertTrue(element.hasClass("class3"));
    }
}
