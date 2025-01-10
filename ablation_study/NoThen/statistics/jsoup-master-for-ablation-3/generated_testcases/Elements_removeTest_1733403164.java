
package org.jsoup.select;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Elements_removeTest {

    @Test
    void testRemove() {
        // Given
        Elements elements = new Elements();
        Element element1 = new Element("div");
        Element element2 = new Element("span");
        elements.add(element1);
        elements.add(element2);

        // When
        Elements result = elements.remove();

        // Then
        assertTrue(element1.parent() == null);
        assertTrue(element2.parent() == null);
        assertEquals(elements, result);
    }
}
