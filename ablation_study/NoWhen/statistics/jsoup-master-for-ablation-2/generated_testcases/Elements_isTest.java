
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
        Document doc = Jsoup.parse("<div class='test'>Text1</div><div class='test'>Text2</div>");
        elements = doc.select("div.test");
    }

    @Test
    public void testIsWithMatchingQuery() {
        assertTrue(elements.is(".test"));
    }

    @Test
    public void testIsWithNonMatchingQuery() {
        assertFalse(elements.is(".nonexistent"));
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
    public void testIsWithSingleElementMatchingQuery() {
        Element singleElement = elements.first();
        assertTrue(singleElement.is(".test"));
    }

    @Test
    public void testIsWithSingleElementNonMatchingQuery() {
        Element singleElement = elements.first();
        assertFalse(singleElement.is(".nonexistent"));
    }
}
