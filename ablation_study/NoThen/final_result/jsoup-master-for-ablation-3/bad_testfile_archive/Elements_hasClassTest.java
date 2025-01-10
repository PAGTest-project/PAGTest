
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
        String html = "<div class='test-class'></div><div></div>";
        Document doc = Jsoup.parse(html);
        elements = new Elements(doc.select("div"));
    }

    @Test
    public void testHasClassWhenClassExists() {
        assertTrue(elements.hasClass("test-class"));
    }

    @Test
    public void testHasClassWhenClassDoesNotExist() {
        assertFalse(elements.hasClass("non-existent-class"));
    }

    @Test
    public void testHasClassAfterAddingClass() {
        elements.addClass("new-class");
        assertTrue(elements.hasClass("new-class"));
    }

    @Test
    public void testHasClassAfterRemovingClass() {
        elements.removeClass("test-class");
        assertFalse(elements.hasClass("test-class"));
    }

    @Test
    public void testHasClassAfterTogglingClass() {
        elements.toggleClass("test-class");
        assertFalse(elements.hasClass("test-class"));
        elements.toggleClass("test-class");
        assertTrue(elements.hasClass("test-class"));
    }

    @Test
    public void testHasClassWithMultipleElements() {
        String html = "<div class='test-class'></div><div class='test-class'></div>";
        Document doc = Jsoup.parse(html);
        elements = new Elements(doc.select("div"));
        assertTrue(elements.hasClass("test-class"));
    }

    @Test
    public void testHasClassWithNoElements() {
        elements = new Elements();
        assertFalse(elements.hasClass("test-class"));
    }
}
