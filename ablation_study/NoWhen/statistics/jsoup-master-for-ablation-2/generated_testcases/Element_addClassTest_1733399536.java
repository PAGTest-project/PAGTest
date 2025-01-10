
package org.jsoup.nodes;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Element_addClassTest {

    @Test
    void testAddClass() {
        Element element = new Element("div");
        element.addClass("testClass");

        assertTrue(element.hasClass("testClass"));
    }

    @Test
    void testAddClassWithNull() {
        Element element = new Element("div");

        assertThrows(IllegalArgumentException.class, () -> {
            element.addClass(null);
        });
    }
}
