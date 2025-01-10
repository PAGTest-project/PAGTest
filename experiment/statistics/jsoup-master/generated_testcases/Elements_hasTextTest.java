
package org.jsoup.select;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Elements_hasTextTest {
    private Elements elements;

    @BeforeEach
    public void setUp() {
        elements = new Elements();
    }

    @Test
    public void testHasTextWithText() {
        Element elementWithText = new Element("p").text("Sample Text");
        elements.add(elementWithText);
        assertTrue(elements.hasText());
    }

    @Test
    public void testHasTextWithoutText() {
        Element elementWithoutText = new Element("p").text("");
        elements.add(elementWithoutText);
        assertFalse(elements.hasText());
    }

    @Test
    public void testHasTextMixed() {
        Element elementWithText = new Element("p").text("Sample Text");
        Element elementWithoutText = new Element("p").text("");
        elements.add(elementWithText);
        elements.add(elementWithoutText);
        assertTrue(elements.hasText());
    }
}
