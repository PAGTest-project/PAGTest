
package org.jsoup.nodes;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Element_expectFirstTest {
    private Document doc;

    @BeforeEach
    public void setUp() {
        doc = Jsoup.parse("<div id='test'><p>One</p><p>Two</p></div>");
    }

    @Test
    public void testExpectFirstWithMatch() {
        Element div = doc.select("div").first();
        Element p = div.expectFirst("p");
        assertNotNull(p);
        assertEquals("One", p.text());
    }

    @Test
    public void testExpectFirstWithoutMatch() {
        Element div = doc.select("div").first();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            div.expectFirst("span");
        });
        String expectedMessage = "No elements matched the query 'span' on element 'div'.";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testExpectFirstInDocumentWithoutMatch() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            doc.expectFirst("span");
        });
        String expectedMessage = "No elements matched the query 'span' in the document.";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}
