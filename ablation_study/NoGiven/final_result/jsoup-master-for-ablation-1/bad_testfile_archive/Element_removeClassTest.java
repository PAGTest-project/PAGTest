
package org.jsoup.nodes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Element_removeClassTest {
    private Element element;

    @BeforeEach
    public void setUp() {
        element = new Element("div");
        element.attr("class", "class1 class2 class3");
    }

    @Test
    public void testRemoveClassSuccess() {
        Element result = element.removeClass("class2");
        assertTrue(result.hasClass("class1"));
        assertFalse(result.hasClass("class2"));
        assertTrue(result.hasClass("class3"));
    }

    @Test
    public void testRemoveClassNonExistent() {
        Element result = element.removeClass("nonExistentClass");
        assertTrue(result.hasClass("class1"));
        assertTrue(result.hasClass("class2"));
        assertTrue(result.hasClass("class3"));
    }

    @Test
    public void testRemoveClassNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            element.removeClass(null);
        });
    }

    @Test
    public void testRemoveClassEmptyString() {
        Element result = element.removeClass("");
        assertTrue(result.hasClass("class1"));
        assertTrue(result.hasClass("class2"));
        assertTrue(result.hasClass("class3"));
    }

    @Test
    public void testRemoveClassAll() {
        Element result = element.removeClass("class1 class2 class3");
        assertFalse(result.hasClass("class1"));
        assertFalse(result.hasClass("class2"));
        assertFalse(result.hasClass("class3"));
    }
}
