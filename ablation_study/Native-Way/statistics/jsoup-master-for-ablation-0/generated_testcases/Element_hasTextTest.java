
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
    public void testHasTextWithNonBlankText() {
        element.appendText("Hello");
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

    @Test
    public void testHasTextWithNestedElements() {
        Element child = new Element("p");
        child.appendText("Hello");
        element.appendChild(child);
        assertTrue(element.hasText());
    }

    @Test
    public void testHasTextWithNestedBlankElements() {
        Element child = new Element("p");
        child.appendText("   ");
        element.appendChild(child);
        assertFalse(element.hasText());
    }

    @Test
    public void testHasTextWithMixedContent() {
        Element child1 = new Element("p");
        child1.appendText("Hello");
        Element child2 = new Element("p");
        child2.appendText("   ");
        element.appendChild(child1);
        element.appendChild(child2);
        assertTrue(element.hasText());
    }
}
