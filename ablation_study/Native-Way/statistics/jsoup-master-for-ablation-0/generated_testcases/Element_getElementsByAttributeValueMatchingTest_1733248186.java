
package org.jsoup.nodes;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.regex.PatternSyntaxException;

import static org.junit.jupiter.api.Assertions.*;

public class Element_getElementsByAttributeValueMatchingTest {
    private Document doc;

    @BeforeEach
    public void setUp() {
        String html = "<body><div class='test'>One</div><div class='test'>Two</div><div class='other'>Three</div></body>";
        doc = Jsoup.parse(html);
    }

    @Test
    public void testGetElementsByAttributeValueMatching_ValidRegex() {
        Elements elements = doc.getElementsByAttributeValueMatching("class", "test");
        assertEquals(2, elements.size());
        assertEquals("One", elements.get(0).text());
        assertEquals("Two", elements.get(1).text());
    }

    @Test
    public void testGetElementsByAttributeValueMatching_InvalidRegex() {
        assertThrows(IllegalArgumentException.class, () -> {
            doc.getElementsByAttributeValueMatching("class", "[invalidRegex");
        });
    }

    @Test
    public void testGetElementsByAttributeValueMatching_NoMatch() {
        Elements elements = doc.getElementsByAttributeValueMatching("class", "nonexistent");
        assertEquals(0, elements.size());
    }

    @Test
    public void testGetElementsByAttributeValueMatching_EmptyRegex() {
        Elements elements = doc.getElementsByAttributeValueMatching("class", "");
        assertEquals(3, elements.size()); // Matches all elements with any class attribute
    }

    @Test
    public void testGetElementsByAttributeValueMatching_NullKey() {
        assertThrows(IllegalArgumentException.class, () -> {
            doc.getElementsByAttributeValueMatching(null, "test");
        });
    }

    @Test
    public void testGetElementsByAttributeValueMatching_NullRegex() {
        assertThrows(IllegalArgumentException.class, () -> {
            doc.getElementsByAttributeValueMatching("class", null);
        });
    }
}
