
package org.jsoup.select;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Elements_valTest {
    private Elements elements;

    @BeforeEach
    public void setUp() {
        Document doc = Jsoup.parse("<form><input value='test'></form>");
        elements = new Elements(doc.select("input"));
    }

    @Test
    public void testValWithElements() {
        assertEquals("test", elements.val());
    }

    @Test
    public void testValWithEmptyElements() {
        elements = new Elements();
        assertEquals("", elements.val());
    }
}
