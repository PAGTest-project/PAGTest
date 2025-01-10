
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
    public void testAppendText() {
        String text = "Sample Text";
        element.appendText(text);
        assertEquals(text, element.text());
    }

    @Test
    public void testAppendTextNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            element.appendText(null);
        });
    }

    @Test
    public void testAppendTextMultiple() {
        String text1 = "First Text";
        String text2 = "Second Text";
        element.appendText(text1);
        element.appendText(text2);
        assertEquals(text1 + text2, element.text());
    }
}
