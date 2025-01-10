
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
    public void testPrependTextValid() {
        String text = "Hello, World!";
        element.prependText(text);
        assertEquals(text, element.text());
    }

    @Test
    public void testPrependTextNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            element.prependText(null);
        });
    }
}
