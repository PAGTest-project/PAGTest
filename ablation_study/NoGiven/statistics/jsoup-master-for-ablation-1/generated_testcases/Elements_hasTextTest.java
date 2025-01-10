
package org.jsoup.select;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class Elements_hasTextTest {
    private Elements elements;

    @BeforeEach
    public void setUp() {
        elements = new Elements();
    }

    @Test
    public void testHasText_WithTextElements() {
        Element element1 = new Element("div");
        element1.text("Hello");
        Element element2 = new Element("span");
        element2.text("World");

        elements.addAll(Arrays.asList(element1, element2));

        assertTrue(elements.hasText());
    }

    @Test
    public void testHasText_WithoutTextElements() {
        Element element1 = new Element("div");
        Element element2 = new Element("span");

        elements.addAll(Arrays.asList(element1, element2));

        assertFalse(elements.hasText());
    }

    @Test
    public void testHasText_MixedElements() {
        Element element1 = new Element("div");
        element1.text("Hello");
        Element element2 = new Element("span");

        elements.addAll(Arrays.asList(element1, element2));

        assertTrue(elements.hasText());
    }

    @Test
    public void testHasText_EmptyElements() {
        assertFalse(elements.hasText());
    }

    @Test
    public void testHasText_UsingTextMethod() {
        String body = "<div>Hello</div><span></span>";
        Document doc = Jsoup.parse(body);
        elements = doc.select("div, span");

        assertTrue(elements.hasText());
    }

    @Test
    public void testHasText_UsingEachTextMethod() {
        String body = "<div>Hello</div><span></span>";
        Document doc = Jsoup.parse(body);
        elements = doc.select("div, span");

        List<String> texts = elements.eachText();
        assertEquals(1, texts.size());
        assertTrue(elements.hasText());
    }
}
