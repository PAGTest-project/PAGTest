
package org.jsoup.select;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Elements_removeClassTest {
    private Elements elements;

    @BeforeEach
    public void setUp() {
        Document doc = Jsoup.parse("<div class='test'>Test</div><div class='remove'>Remove</div>");
        elements = doc.select("div");
    }

    @Test
    public void testRemoveClass() {
        elements.removeClass("remove");
        assertFalse(elements.get(0).hasClass("remove"));
        assertFalse(elements.get(1).hasClass("remove"));
    }

    @Test
    public void testRemoveClassNoClass() {
        elements.removeClass("nonexistent");
        assertFalse(elements.get(0).hasClass("nonexistent"));
        assertFalse(elements.get(1).hasClass("nonexistent"));
    }

    @Test
    public void testRemoveClassEmptyClass() {
        elements.removeClass("");
        assertFalse(elements.get(0).hasClass(""));
        assertFalse(elements.get(1).hasClass(""));
    }

    @Test
    public void testRemoveClassNullClass() {
        elements.removeClass(null);
        assertFalse(elements.get(0).hasClass(null));
        assertFalse(elements.get(1).hasClass(null));
    }

    @Test
    public void testRemoveClassMultipleClasses() {
        Document doc = Jsoup.parse("<div class='test remove'>Test</div><div class='remove test'>Remove</div>");
        elements = doc.select("div");
        elements.removeClass("remove");
        assertFalse(elements.get(0).hasClass("remove"));
        assertFalse(elements.get(1).hasClass("remove"));
    }
}
