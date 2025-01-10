
package org.jsoup.nodes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Element_hasTextTest {
    private Element element;

    @BeforeEach
    public void setUp() {
        element = new Element("div");
    }

    @Test
    public void testHasTextWithNonBlankText() {
        element.appendText("Hello, World!");
        assertTrue(element.hasText());
    }

    @Test
    public void testHasTextWithBlankText() {
        element.appendText("   ");
        assertFalse(element.hasText());
    }

    @Test
    public void testHasTextWithNoText() {
        assertFalse(element.hasText());
    }
}
