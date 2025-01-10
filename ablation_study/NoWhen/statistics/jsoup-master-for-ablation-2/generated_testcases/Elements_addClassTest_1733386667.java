
package org.jsoup.select;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Elements_addClassTest {

    @Test
    void testAddClass() {
        // Given
        Elements elements = new Elements();
        Element element1 = new Element("div");
        Element element2 = new Element("span");
        elements.add(element1);
        elements.add(element2);

        // When
        elements.addClass("testClass");

        // Then
        assertTrue(element1.hasClass("testClass"));
        assertTrue(element2.hasClass("testClass"));
    }
}
