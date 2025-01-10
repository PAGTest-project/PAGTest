
package org.jsoup.nodes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Element_prependTextTest {
    private Element element;

    @BeforeEach
    public void setUp() {
        element = new Element("div");
    }

    @Test
    public void testPrependTextSuccess() {
        element.prependText("Hello");
        assertEquals("Hello", element.text());
    }

    @Test
    public void testPrependTextMultipleTimes() {
        element.prependText("World");
        element.prependText("Hello ");
        assertEquals("Hello World", element.text());
    }

    @Test
    public void testPrependTextWithExistingContent() {
        element.appendText("World");
        element.prependText("Hello ");
        assertEquals("Hello World", element.text());
    }

    @Test
    public void testPrependTextWithEmptyElement() {
        element.prependText("");
        assertEquals("", element.text());
    }

    @Test
    public void testPrependTextWithNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            element.prependText(null);
        });
    }
}
