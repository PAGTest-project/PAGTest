
package org.jsoup.nodes;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Element_getElementsByAttributeStartingTest {

    private Element element;

    @BeforeEach
    public void setUp() {
        String html = "<div data-prefix-one='value1' data-prefix-two='value2'></div>";
        Document doc = Jsoup.parse(html);
        element = doc.body().child(0);
    }

    @Test
    public void testGetElementsByAttributeStarting() {
        Elements elements = element.getElementsByAttributeStarting("data-prefix");
        assertEquals(1, elements.size());
        assertEquals("value1", elements.get(0).attr("data-prefix-one"));
    }

    @Test
    public void testGetElementsByAttributeStarting_EmptyPrefix() {
        assertThrows(IllegalArgumentException.class, () -> {
            element.getElementsByAttributeStarting("");
        });
    }

    @Test
    public void testGetElementsByAttributeStarting_NullPrefix() {
        assertThrows(IllegalArgumentException.class, () -> {
            element.getElementsByAttributeStarting(null);
        });
    }
}
