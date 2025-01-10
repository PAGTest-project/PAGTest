
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
        element.attr("class", "existing-class");
        element.toggleClass("new-class");
        assertTrue(element.hasClass("existing-class"));
        assertTrue(element.hasClass("new-class"));
    }

    @Test
    public void testToggleClassRemove() {
        element.attr("class", "existing-class");
        element.toggleClass("existing-class");
        assertFalse(element.hasClass("existing-class"));
    }

    @Test
    public void testToggleClassNoChange() {
        element.attr("class", "existing-class");
        element.toggleClass("existing-class");
        element.toggleClass("existing-class");
        assertTrue(element.hasClass("existing-class"));
    }

    @Test
    public void testToggleClassEmpty() {
        element.toggleClass("new-class");
        assertTrue(element.hasClass("new-class"));
    }
}
