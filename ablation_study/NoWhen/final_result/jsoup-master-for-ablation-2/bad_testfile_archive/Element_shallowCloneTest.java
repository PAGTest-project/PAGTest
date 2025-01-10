
package org.jsoup.nodes;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Element_shallowCloneTest {

    @Test
    void testShallowCloneWithBaseUri() {
        // Given
        Element original = new Element("div", "http://example.com");
        original.attributes = new Attributes();
        original.attributes.put("class", "test");

        // When
        Element cloned = original.shallowClone();

        // Then
        assertNotSame(original, cloned);
        assertEquals(original.tag, cloned.tag);
        assertEquals(original.baseUri(), cloned.baseUri());
        assertEquals(original.attributes, cloned.attributes);
    }

    @Test
    void testShallowCloneWithoutBaseUri() {
        // Given
        Element original = new Element("div");
        original.attributes = new Attributes();
        original.attributes.put("class", "test");

        // When
        Element cloned = original.shallowClone();

        // Then
        assertNotSame(original, cloned);
        assertEquals(original.tag, cloned.tag);
        assertNull(cloned.baseUri());
        assertEquals(original.attributes, cloned.attributes);
    }
}
