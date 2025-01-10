
package org.jsoup.nodes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Element_removeClassTest {
    private Element element;

    @BeforeEach
    public void setUp() {
        element = new Element("div");
        element.addClass("testClass");
    }

    @Test
    public void testRemoveClassSuccess() {
        element.removeClass("testClass");
        assertFalse(element.hasClass("testClass"));
    }

    @Test
    public void testRemoveClassNonExistent() {
        element.removeClass("nonExistentClass");
        assertFalse(element.hasClass("nonExistentClass"));
    }
}
