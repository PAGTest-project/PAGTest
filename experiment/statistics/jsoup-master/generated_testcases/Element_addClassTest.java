
package org.jsoup.nodes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Element_addClassTest {
    private Element element;

    @BeforeEach
    public void setUp() {
        element = new Element("div");
    }

    @Test
    public void testAddClassSuccess() {
        element.addClass("newClass");
        assertTrue(element.hasClass("newClass"));
    }

    @Test
    public void testAddClassNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            element.addClass(null);
        });
    }
}
