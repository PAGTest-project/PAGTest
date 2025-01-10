
package org.jsoup.select;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Elements_hasClassTest {
    private Elements elements;

    @BeforeEach
    public void setUp() {
        Document doc = Jsoup.parse("<div class='test'><p class='test'>Text</p></div>");
        elements = new Elements(doc.select("*"));
    }

    @Test
    public void testHasClassPresent() {
        assertTrue(elements.hasClass("test"));
    }

    @Test
    public void testHasClassNotPresent() {
        assertFalse(elements.hasClass("nonexistent"));
    }

    @Test
    public void testHasClassEmptyElements() {
        Elements emptyElements = new Elements();
        assertFalse(emptyElements.hasClass("test"));
    }

    @Test
    public void testHasClassSingleElement() {
        Document doc = Jsoup.parse("<div class='single'></div>");
        Elements singleElement = new Elements(doc.select("div"));
        assertTrue(singleElement.hasClass("single"));
    }

    @Test
    public void testHasClassMultipleElements() {
        Document doc = Jsoup.parse("<div class='one'></div><div class='two'></div>");
        Elements multipleElements = new Elements(doc.select("div"));
        assertTrue(multipleElements.hasClass("one"));
        assertTrue(multipleElements.hasClass("two"));
    }
}
