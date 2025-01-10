
package org.jsoup.select;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Elements_valTest {
    private Elements elements;

    @BeforeEach
    public void setUp() {
        elements = new Elements();
    }

    @Test
    public void testValWithElements() {
        // Given
        Element element = new Element("form");
        elements.add(element);

        // When
        String result = elements.val();

        // Then
        assertEquals("", result);
    }

    @Test
    public void testValWithEmptyElements() {
        // Given
        // elements is already empty from setUp

        // When
        String result = elements.val();

        // Then
        assertEquals("", result);
    }
}
