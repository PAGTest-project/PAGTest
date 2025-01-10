
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
        Document doc = Jsoup.parse("<div class='test'>Test</div><div>NoClass</div>");
        elements = doc.select("div");
    }

    @Test
    public void testRemoveClass() {
        // Given
        elements.addClass("newClass");

        // When
        elements.removeClass("test");

        // Then
        assertFalse(elements.hasClass("test"));
        assertTrue(elements.hasClass("newClass"));
    }
}
