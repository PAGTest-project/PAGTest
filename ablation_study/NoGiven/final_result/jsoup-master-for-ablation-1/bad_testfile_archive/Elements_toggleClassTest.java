
package org.jsoup.select;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Elements_toggleClassTest {

    @Test
    public void testToggleClass() {
        // Given
        Elements elements = new Elements();
        Element element = new Element("div");
        elements.add(element);

        // When
        elements.addClass("testClass");
        elements.toggleClass("testClass");

        // Then
        assertFalse(element.hasClass("testClass"));

        // When
        elements.toggleClass("testClass");

        // Then
        assertTrue(element.hasClass("testClass"));
    }
}
