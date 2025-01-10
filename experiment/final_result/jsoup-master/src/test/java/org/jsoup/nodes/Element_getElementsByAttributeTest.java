
package org.jsoup.nodes;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Element_getElementsByAttributeTest {

    private Element element;

    @BeforeEach
    public void setUp() {
        String html = "<div id='test' class='test-class' data-attr='test-value'></div>";
        Document doc = Jsoup.parse(html);
        element = doc.body().child(0);
    }

    @Test
    public void testGetElementsByAttribute() {
        Elements elements = element.getElementsByAttribute("data-attr");
        assertEquals(1, elements.size());
        assertEquals("test-value", elements.first().attr("data-attr"));
    }

    @Test
    public void testGetElementsByAttributeEmptyKey() {
        assertThrows(IllegalArgumentException.class, () -> {
            element.getElementsByAttribute("");
        });
    }
}
