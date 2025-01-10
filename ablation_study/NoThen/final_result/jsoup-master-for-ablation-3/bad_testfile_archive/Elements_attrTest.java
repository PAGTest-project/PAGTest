
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
        Document doc = Jsoup.parse("<p><a href='/foo'>Foo</a><a href='/bar'>Bar</a><a>None</a></p>");
        elements = new Elements(doc.select("a"));
    }

    @Test
    public void testAttrWithExistingAttribute() {
        assertEquals("/foo", elements.attr("href"));
    }

    @Test
    public void testAttrWithNonExistingAttribute() {
        assertEquals("", elements.attr("nonExistingAttribute"));
    }

    @Test
    public void testAttrAfterRemoveAttr() {
        elements.removeAttr("href");
        assertEquals("", elements.attr("href"));
    }

    @Test
    public void testAttrAfterAddClass() {
        elements.addClass("testClass");
        assertTrue(elements.hasAttr("class"));
    }
}
