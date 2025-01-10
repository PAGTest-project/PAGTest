
package org.jsoup.select;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.*;

public class Elements_removeIfTest {
    private Elements elements;

    @BeforeEach
    public void setUp() {
        String html = "<div class='test'>Test Element</div><div class='remove'>Remove Element</div>";
        Document doc = Jsoup.parse(html);
        elements = new Elements(doc.select("div"));
    }

    @Test
    public void testRemoveIf_AllElementsRemoved() {
        Predicate<Element> filter = element -> element.hasClass("remove");
        boolean anyRemoved = elements.removeIf(filter);
        assertTrue(anyRemoved);
        assertEquals(1, elements.size());
        assertFalse(elements.first().hasClass("remove"));
    }

    @Test
    public void testRemoveIf_NoElementsRemoved() {
        Predicate<Element> filter = element -> element.hasClass("nonexistent");
        boolean anyRemoved = elements.removeIf(filter);
        assertFalse(anyRemoved);
        assertEquals(2, elements.size());
    }

    @Test
    public void testRemoveIf_SomeElementsRemoved() {
        Predicate<Element> filter = element -> element.text().equals("Remove Element");
        boolean anyRemoved = elements.removeIf(filter);
        assertTrue(anyRemoved);
        assertEquals(1, elements.size());
        assertEquals("Test Element", elements.first().text());
    }
}
