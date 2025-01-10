
package org.jsoup.nodes;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Element_getElementsByAttributeTest {
    private Document doc;

    @BeforeEach
    public void setUp() {
        doc = Jsoup.parse("<div id='test' class='test-class' data-attr='test-value'><p class='inner-class'>Inner Text</p></div>");
    }

    @Test
    public void testGetElementsByAttribute() {
        Element element = doc.getElementById("test");
        Elements elements = element.getElementsByAttribute("data-attr");
        assertEquals(1, elements.size());
        assertEquals("test-value", elements.first().attr("data-attr"));
    }

    @Test
    public void testGetElementsByAttributeEmptyKey() {
        Element element = doc.getElementById("test");
        assertThrows(IllegalArgumentException.class, () -> {
            element.getElementsByAttribute("");
        });
    }

    @Test
    public void testGetElementsByAttributeNullKey() {
        Element element = doc.getElementById("test");
        assertThrows(IllegalArgumentException.class, () -> {
            element.getElementsByAttribute(null);
        });
    }

    @Test
    public void testGetElementsByAttributeTrimmedKey() {
        Element element = doc.getElementById("test");
        Elements elements = element.getElementsByAttribute(" data-attr ");
        assertEquals(1, elements.size());
        assertEquals("test-value", elements.first().attr("data-attr"));
    }
}
