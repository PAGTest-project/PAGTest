
package org.jsoup.select;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Elements_attrTest {
    private Elements elements;

    @BeforeEach
    public void setUp() {
        Document doc = Jsoup.parse("<div id='test' data-attr='value'><span class='test'>Text</span></div>");
        elements = new Elements(doc.select("div, span"));
    }

    @Test
    public void testAttrPresent() {
        assertEquals("value", elements.attr("data-attr"));
    }

    @Test
    public void testAttrAbsent() {
        assertEquals("", elements.attr("nonexistent-attr"));
    }

    @Test
    public void testAttrOnMultipleElements() {
        assertEquals("test", elements.attr("class"));
    }

    @Test
    public void testAttrOnEmptyElements() {
        Elements emptyElements = new Elements();
        assertEquals("", emptyElements.attr("class"));
    }
}
