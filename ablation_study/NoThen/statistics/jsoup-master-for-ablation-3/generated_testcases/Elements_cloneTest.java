
package org.jsoup.select;

import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Elements_cloneTest {

    @Test
    public void testClone() {
        // Given
        Element element1 = new Element("div");
        Element element2 = new Element("span");
        Elements elements = new Elements(Arrays.asList(element1, element2));

        // When
        Elements clonedElements = elements.clone();

        // Then
        assertEquals(elements.size(), clonedElements.size());
        for (int i = 0; i < elements.size(); i++) {
            assertEquals(elements.get(i).tagName(), clonedElements.get(i).tagName());
        }
    }
}
