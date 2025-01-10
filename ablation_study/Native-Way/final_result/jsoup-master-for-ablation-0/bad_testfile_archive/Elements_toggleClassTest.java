
package org.jsoup.select;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Elements_toggleClassTest {
    private Elements elements;

    @BeforeEach
    public void setUp() {
        Document doc = Jsoup.parse("<div class='test'></div><div></div>");
        elements = new Elements(doc.select("div"));
    }

    @Test
    public void testToggleClassAdd() {
        elements.toggleClass("newClass");
        assertTrue(elements.get(0).hasClass("newClass"));
    }

    @Test
    public void testToggleClassRemove() {
        elements.toggleClass("test");
        assertFalse(elements.get(0).hasClass("test"));
    }

    @Test
    public void testToggleClassMixed() {
        elements.get(0).toggleClass("test");
        elements.get(1).toggleClass("newClass");
        assertFalse(elements.get(0).hasClass("test"));
        assertTrue(elements.get(1).hasClass("newClass"));
    }
}
