
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
        Element element1 = new Element("div").addClass("testClass");
        Element element2 = new Element("div");
        elements.add(element1);
        elements.add(element2);

        // When
        elements.toggleClass("testClass");

        // Then
        assertFalse(element1.hasClass("testClass"));
        assertTrue(element2.hasClass("testClass"));
    }
}
