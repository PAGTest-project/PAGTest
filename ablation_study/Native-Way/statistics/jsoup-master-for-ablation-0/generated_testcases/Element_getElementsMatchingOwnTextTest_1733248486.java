
package org.jsoup.nodes;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Element_getElementsMatchingOwnTextTest {
    private Document doc;

    @BeforeEach
    public void setUp() {
        doc = Jsoup.parse("<div>Test <span>Text</span></div>");
    }

    @Test
    public void testGetElementsMatchingOwnTextValidRegex() {
        Elements elements = doc.getElementsMatchingOwnText("Test");
        assertEquals(1, elements.size());
        assertEquals("div", elements.get(0).tagName());
    }

    @Test
    public void testGetElementsMatchingOwnTextInvalidRegex() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            doc.getElementsMatchingOwnText("[invalid regex");
        });
        String expectedMessage = "Pattern syntax error: [invalid regex";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testGetElementsMatchingOwnTextNoMatch() {
        Elements elements = doc.getElementsMatchingOwnText("NoMatch");
        assertEquals(0, elements.size());
    }
}
