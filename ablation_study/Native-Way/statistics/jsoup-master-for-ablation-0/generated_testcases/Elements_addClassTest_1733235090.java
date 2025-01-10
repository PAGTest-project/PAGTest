
package org.jsoup.select;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Elements_addClassTest {
    private Elements elements;

    @BeforeEach
    public void setUp() {
        Document doc = Jsoup.parse("<div class='test'><p class='item'>Item 1</p><p class='item'>Item 2</p></div>");
        elements = doc.select("p.item");
    }

    @Test
    public void testAddClass() {
        elements.addClass("newClass");
        for (Element element : elements) {
            assertTrue(element.hasClass("newClass"));
        }
    }

    @Test
    public void testAddClassToEmptyElements() {
        Elements emptyElements = new Elements();
        emptyElements.addClass("newClass");
        assertEquals(0, emptyElements.size());
    }

    @Test
    public void testAddClassToSingleElement() {
        Document doc = Jsoup.parse("<div class='test'><p class='item'>Item 1</p></div>");
        Elements singleElement = doc.select("p.item");
        singleElement.addClass("newClass");
        assertTrue(singleElement.first().hasClass("newClass"));
    }

    @Test
    public void testAddClassToAlreadyExistingClass() {
        elements.addClass("item");
        for (Element element : elements) {
            assertTrue(element.hasClass("item"));
        }
    }
}
