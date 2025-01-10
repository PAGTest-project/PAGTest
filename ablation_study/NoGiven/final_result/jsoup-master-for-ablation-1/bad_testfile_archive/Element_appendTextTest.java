
package org.jsoup.nodes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Element_appendTextTest {
    private Element element;

    @BeforeEach
    public void setUp() {
        element = new Element("div");
    }

    @Test
    public void testAppendTextWithNonNullText() {
        String text = "Sample Text";
        Element result = element.appendText(text);
        assertEquals(element, result);
        assertEquals(text, element.text());
    }

    @Test
    public void testAppendTextWithNullText() {
        assertThrows(IllegalArgumentException.class, () -> {
            element.appendText(null);
        });
    }

    @Test
    public void testAppendTextWithEmptyText() {
        String text = "";
        Element result = element.appendText(text);
        assertEquals(element, result);
        assertEquals(text, element.text());
    }

    @Test
    public void testAppendTextWithWhitespaceText() {
        String text = "   ";
        Element result = element.appendText(text);
        assertEquals(element, result);
        assertEquals(text, element.text());
    }

    @Test
    public void testAppendTextWithSpecialCharacters() {
        String text = "Text with special characters: &<>\"'";
        Element result = element.appendText(text);
        assertEquals(element, result);
        assertEquals(text, element.text());
    }
}
