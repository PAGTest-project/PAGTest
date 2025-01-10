
package org.jsoup.select;

import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Elements_setTest {

    @Test
    void testSet() {
        // Given
        Elements elements = new Elements();
        Element oldElement = new Element("div");
        Element newElement = new Element("span");
        elements.add(oldElement);

        // When
        Element result = elements.set(0, newElement);

        // Then
        assertEquals(oldElement, result);
        assertEquals(newElement, elements.get(0));
        assertNull(oldElement.parent()); // Ensure oldElement is detached
    }
}
