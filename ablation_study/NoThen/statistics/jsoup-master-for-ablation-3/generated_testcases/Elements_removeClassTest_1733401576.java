
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
        Element element1 = Jsoup.parse("<div class='test'></div>").body().child(0);
        Element element2 = Jsoup.parse("<div class='test'></div>").body().child(0);
        elements.add(element1);
        elements.add(element2);

        // When
        elements.removeClass("test");

        // Then
        assertFalse(element1.hasClass("test"));
        assertFalse(element2.hasClass("test"));
    }
}
