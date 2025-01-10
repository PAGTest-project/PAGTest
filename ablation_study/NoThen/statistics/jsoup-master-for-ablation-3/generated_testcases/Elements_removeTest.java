
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
        assertNull(element1.parent());
        assertNull(element2.parent());
        assertEquals(0, elements.size());
        assertEquals(elements, result);
    }
}
