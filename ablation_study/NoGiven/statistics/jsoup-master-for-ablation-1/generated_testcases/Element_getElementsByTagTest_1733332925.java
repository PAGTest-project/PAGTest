
package org.jsoup.nodes;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Element_getElementsByTagTest {

    private Element element;

    @BeforeEach
    public void setUp() {
        String html = "<div><p>One</p><p>Two</p></div>";
        Document doc = Jsoup.parse(html);
        element = doc.selectFirst("div");
    }

    @Test
    public void testGetElementsByTag() {
        Elements paragraphs = element.getElementsByTag("p");
        assertEquals(2, paragraphs.size());
        assertEquals("One", paragraphs.get(0).text());
        assertEquals("Two", paragraphs.get(1).text());
    }

    @Test
    public void testGetElementsByTagWithEmptyTagName() {
        assertThrows(IllegalArgumentException.class, () -> {
            element.getElementsByTag("");
        });
    }

    @Test
    public void testGetElementsByTagWithNullTagName() {
        assertThrows(IllegalArgumentException.class, () -> {
            element.getElementsByTag(null);
        });
    }

    @Test
    public void testGetElementsByTagWithNonExistentTag() {
        Elements nonExistentTags = element.getElementsByTag("span");
        assertEquals(0, nonExistentTags.size());
    }
}
