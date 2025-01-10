
package org.jsoup.nodes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Element_ownTextTest {
    private Element element;

    @BeforeEach
    public void setUp() {
        element = new Element("div");
    }

    @Test
    public void testOwnText() {
        // Given
        element.appendText("Hello, World!");

        // When
        String result = element.ownText();

        // Then
        assertEquals("Hello, World!", result);
    }

    @Test
    public void testOwnTextWithChildElements() {
        // Given
        element.appendText("Hello, ");
        Element child = new Element("span");
        child.appendText("World!");
        element.appendChild(child);

        // When
        String result = element.ownText();

        // Then
        assertEquals("Hello, ", result);
    }

    @Test
    public void testOwnTextWithNoText() {
        // Given
        // No text appended

        // When
        String result = element.ownText();

        // Then
        assertEquals("", result);
    }
}
