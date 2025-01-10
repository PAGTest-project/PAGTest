
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
    public void testValWithEmptyElements() {
        assertEquals("", elements.val());
    }

    @Test
    public void testValWithNonEmptyElements() {
        Document doc = Jsoup.parse("<div value='testValue'></div>");
        elements.add(doc.select("div").first());
        assertEquals("testValue", elements.val());
    }

    @Test
    public void testValWithMultipleElements() {
        Document doc = Jsoup.parse("<div value='testValue1'></div><div value='testValue2'></div>");
        elements.addAll(doc.select("div"));
        assertEquals("testValue1", elements.val());
    }

    @Test
    public void testValWithNoValAttribute() {
        Document doc = Jsoup.parse("<div></div>");
        elements.add(doc.select("div").first());
        assertEquals("", elements.val());
    }
}
