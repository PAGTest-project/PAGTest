
package org.jsoup.nodes;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Element_getElementsByTagTest {
    private Document doc;

    @BeforeEach
    public void setUp() {
        doc = Jsoup.parse("<div><p>One</p><p>Two</p><p>Three</p></div>");
    }

    @Test
    public void testGetElementsByTag() {
        Elements elements = doc.getElementsByTag("p");
        assertEquals(3, elements.size());
        assertEquals("One", elements.get(0).text());
        assertEquals("Two", elements.get(1).text());
        assertEquals("Three", elements.get(2).text());
    }

    @Test
    public void testGetElementsByTagWithEmptyTagName() {
        assertThrows(IllegalArgumentException.class, () -> {
            doc.getElementsByTag("");
        });
    }

    @Test
    public void testGetElementsByTagWithNullTagName() {
        assertThrows(IllegalArgumentException.class, () -> {
            doc.getElementsByTag(null);
        });
    }

    @Test
    public void testGetElementsByTagWithNonExistentTag() {
        Elements elements = doc.getElementsByTag("span");
        assertEquals(0, elements.size());
    }
}
