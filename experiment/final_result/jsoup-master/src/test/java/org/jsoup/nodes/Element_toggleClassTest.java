
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
        element.addClass("initialClass");
        element.toggleClass("newClass");

        assertTrue(element.hasClass("initialClass"));
        assertTrue(element.hasClass("newClass"));
    }

    @Test
    public void testToggleClassRemove() {
        element.addClass("initialClass");
        element.toggleClass("initialClass");

        assertFalse(element.hasClass("initialClass"));
    }

    @Test
    public void testToggleClassNoChange() {
        element.toggleClass("newClass");

        assertTrue(element.hasClass("newClass"));
    }
}
