
package org.jsoup.select;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Elements_removeClassTest {

    @Test
    void testRemoveClass() {
        // Given
        Elements elements = new Elements();
        Element element = Jsoup.parse("<div class='test'></div>").select("div").first();
        elements.add(element);

        // When
        elements.addClass("newClass"); // Ensure the class is present
        elements.removeClass("test");

        // Then
        assertFalse(element.hasClass("test"), "Class 'test' should be removed"); // Verify the class is removed
        assertTrue(element.hasClass("newClass"), "Class 'newClass' should still be present"); // Verify the new class is still present
    }
}
