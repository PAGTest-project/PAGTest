
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
        element.removeClass("class2");
        assertFalse(element.hasClass("class2"));
        assertEquals("class1 class3", element.className());
    }

    @Test
    public void testRemoveClassNonExistent() {
        element.removeClass("nonExistentClass");
        assertEquals("class1 class2 class3", element.className());
    }

    @Test
    public void testRemoveClassNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            element.removeClass(null);
        });
    }

    @Test
    public void testRemoveClassEmpty() {
        element.removeClass("");
        assertEquals("class1 class2 class3", element.className());
    }

    @Test
    public void testRemoveClassAll() {
        element.removeClass("class1").removeClass("class2").removeClass("class3");
        assertEquals("", element.className());
    }
}
