
package org.jsoup.select;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Elements_isTest {
    private Elements elements;

    @BeforeEach
    public void setUp() {
        Document doc = Jsoup.parse("<div>Hello</div><div>World</div>");
        elements = new Elements(doc.select("div"));
    }

    @Test
    public void testIsWithMatchingQuery() {
        assertTrue(elements.is("div"));
    }

    @Test
    public void testIsWithNonMatchingQuery() {
        assertFalse(elements.is("span"));
    }

    @Test
    public void testIsWithEmptyQuery() {
        assertFalse(elements.is(""));
    }

    @Test
    public void testIsWithNullQuery() {
        assertFalse(elements.is(null));
    }

    @Test
    public void testIsWithComplexQuery() {
        Document doc = Jsoup.parse("<div class='test'>Hello</div><div>World</div>");
        elements = new Elements(doc.select("div"));
        assertTrue(elements.is(".test"));
    }
}
