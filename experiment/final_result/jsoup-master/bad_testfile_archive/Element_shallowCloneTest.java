
package org.jsoup.nodes;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Element_shallowCloneTest {

    @Test
    void testShallowClone() {
        // Given
        Attributes attributes = new Attributes();
        attributes.put("key1", "value1");
        Element element = new Element(Tag.valueOf("div"), "http://example.com", attributes);

        // When
        Element clonedElement = element.shallowClone();

        // Then
        assertNotNull(clonedElement);
        assertEquals(element.tag(), clonedElement.tag());
        assertEquals(element.baseUri(), clonedElement.baseUri());
        assertEquals(element.attributes(), clonedElement.attributes());
    }
}
