
package org.jsoup.select;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Elements_emptyTest {
    private Elements elements;

    @BeforeEach
    public void setUp() {
        Document doc = Jsoup.parse("<div><p class='test'>Text1</p><p>Text2</p></div>");
        elements = new Elements(doc.select("p"));
    }

    @Test
    public void testEmptyWithNonEmptyElements() {
        Elements result = elements.empty();
        for (Element element : result) {
            assertEquals("", element.html());
        }
        assertEquals(elements, result);
    }

    @Test
    public void testEmptyWithEmptyElements() {
        elements.clear();
        Elements result = elements.empty();
        assertTrue(result.isEmpty());
        assertEquals(elements, result);
    }

    @Test
    public void testEmptyWithSingleElement() {
        elements.clear();
        elements.add(Jsoup.parse("<p>Single</p>").select("p").first());
        Elements result = elements.empty();
        assertEquals("", result.first().html());
        assertEquals(elements, result);
    }
}
