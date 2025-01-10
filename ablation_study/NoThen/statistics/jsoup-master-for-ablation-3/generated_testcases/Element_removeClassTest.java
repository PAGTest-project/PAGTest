
package org.jsoup.nodes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Element_removeClassTest {
    private Element element;

    @BeforeEach
    public void setUp() {
        element = new Element("div");
    }

    @Test
    public void testRemoveClassSuccess() {
        element.addClass("testClass");
        assertEquals("testClass", element.className());

        Element result = element.removeClass("testClass");
        assertEquals("", element.className());
        assertSame(element, result);
    }

    @Test
    public void testRemoveClassNonExistent() {
        element.addClass("existingClass");
        assertEquals("existingClass", element.className());

        Element result = element.removeClass("nonExistentClass");
        assertEquals("existingClass", element.className());
        assertSame(element, result);
    }

    @Test
    public void testRemoveClassNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            element.removeClass(null);
        });
    }

    @Test
    public void testRemoveClassEmpty() {
        element.addClass("testClass");
        assertEquals("testClass", element.className());

        Element result = element.removeClass("");
        assertEquals("testClass", element.className());
        assertSame(element, result);
    }

    @Test
    public void testRemoveClassMultiple() {
        element.addClass("class1 class2 class3");
        assertEquals("class1 class2 class3", element.className());

        Element result = element.removeClass("class2");
        assertEquals("class1 class3", element.className());
        assertSame(element, result);
    }
}
