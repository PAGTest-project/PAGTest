
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
    public void testPrependText() {
        element.prependText("Hello");
        assertEquals("Hello", element.text());
    }

    @Test
    public void testPrependTextMultiple() {
        element.prependText("Hello");
        element.prependText("World");
        assertEquals("WorldHello", element.text());
    }

    @Test
    public void testPrependTextWithExistingContent() {
        element.appendText("World");
        element.prependText("Hello");
        assertEquals("HelloWorld", element.text());
    }

    @Test
    public void testPrependTextNull() {
        assertThrows(IllegalArgumentException.class, () -> element.prependText(null));
    }
}
