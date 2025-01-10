
package org.jsoup.select;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Elements_isTest {
    private Elements elements;

    @BeforeEach
    public void setUp() {
        Document doc = Jsoup.parse("<div class='test'>Test</div><div>NoClass</div>");
        elements = new Elements(doc.select("div"));
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
    public void testIsWithInvalidQuery() {
        assertThrows(IllegalArgumentException.class, () -> elements.is("invalidQuery"));
    }
}
