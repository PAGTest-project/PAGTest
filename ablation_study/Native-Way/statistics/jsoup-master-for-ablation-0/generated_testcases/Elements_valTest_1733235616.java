
package org.jsoup.select;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Elements_valTest {
    private Elements elements;

    @BeforeEach
    public void setUp() {
        elements = new Elements();
    }

    @Test
    public void testValWithElements() {
        Document doc = Jsoup.parse("<form><input value='test'></form>");
        elements = doc.select("input");
        assertEquals("test", elements.val());
    }

    @Test
    public void testValWithEmptyElements() {
        Document doc = Jsoup.parse("<form></form>");
        elements = doc.select("input");
        assertEquals("", elements.val());
    }
}
