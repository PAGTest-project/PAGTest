
package org.jsoup.select;

import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Elements_cloneTest {

    @Test
    void testClone() {
        // Given
        Elements elements = new Elements();
        Element element1 = new Element("div");
        Element element2 = new Element("span");
        elements.add(element1);
        elements.add(element2);

        // When
        Elements clonedElements = elements.clone();

        // Then
        assertEquals(elements.size(), clonedElements.size());
        for (int i = 0; i < elements.size(); i++) {
            assertEquals(elements.get(i).tagName(), clonedElements.get(i).tagName());
        }
    }
}
