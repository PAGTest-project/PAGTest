
package org.jsoup.nodes;

import org.jsoup.Jsoup;
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
    public void testHasTextWithTextNodes() {
        element.html("<p>Hello World</p>");
        assertTrue(element.hasText());
    }

    @Test
    public void testHasTextWithBlankTextNodes() {
        element.html("<p>   </p>");
        assertFalse(element.hasText());
    }

    @Test
    public void testHasTextWithNoTextNodes() {
        element.html("<div></div>");
        assertFalse(element.hasText());
    }

    @Test
    public void testHasTextWithMixedContent() {
        element.html("<div><p>Hello</p><p>   </p></div>");
        assertTrue(element.hasText());
    }
}
