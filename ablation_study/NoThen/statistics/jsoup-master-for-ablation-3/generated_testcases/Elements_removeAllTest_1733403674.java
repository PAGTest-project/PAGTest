
package org.jsoup.select;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collection;
import static org.junit.jupiter.api.Assertions.*;

class Elements_removeAllTest {

    @Test
    void testRemoveAll() {
        // Given
        Elements elements = new Elements();
        Element element1 = new Element("tag1");
        Element element2 = new Element("tag2");
        elements.addAll(Arrays.asList(element1, element2));
        Collection<?> toRemove = Arrays.asList(element1, new Object());

        // When
        boolean anyRemoved = elements.removeAll(toRemove);

        // Then
        assertTrue(anyRemoved);
        assertFalse(elements.contains(element1));
        assertTrue(elements.contains(element2));
    }
}
