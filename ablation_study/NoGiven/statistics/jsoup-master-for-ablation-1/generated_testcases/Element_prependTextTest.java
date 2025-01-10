
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
        // When
        element.prependText("Hello");

        // Then
        assertEquals("Hello", element.text());
    }

    @Test
    public void testPrependTextToExistingText() {
        // Given
        element.appendText("World");

        // When
        element.prependText("Hello ");

        // Then
        assertEquals("Hello World", element.text());
    }

    @Test
    public void testPrependTextWithNull() {
        // When & Then
        assertThrows(IllegalArgumentException.class, () -> element.prependText(null));
    }

    @Test
    public void testPrependTextWithEmptyString() {
        // When
        element.prependText("");

        // Then
        assertEquals("", element.text());
    }

    @Test
    public void testPrependTextWithWhitespace() {
        // When
        element.prependText("   ");

        // Then
        assertEquals("   ", element.text());
    }
}
