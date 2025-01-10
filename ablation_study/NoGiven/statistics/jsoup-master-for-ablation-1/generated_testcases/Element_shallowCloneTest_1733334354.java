
package org.jsoup.nodes;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Element_shallowCloneTest {

    @Test
    void testShallowClone() {
        // Given
        Tag tag = Tag.valueOf("div");
        String baseUri = "http://example.com";
        Attributes attributes = new Attributes();
        attributes.put("class", "test");
        Element element = new Element(tag, baseUri, attributes);

        // When
        Element clonedElement = element.shallowClone();

        // Then
        assertEquals(tag, clonedElement.tag());
        assertEquals(baseUri, clonedElement.baseUri());
        assertEquals(attributes.size(), clonedElement.attributes().size());
        assertEquals(attributes.get("class"), clonedElement.attributes().get("class"));
    }

    @Test
    void testShallowCloneWithEmptyBaseUri() {
        // Given
        Tag tag = Tag.valueOf("div");
        String baseUri = "";
        Attributes attributes = new Attributes();
        attributes.put("class", "test");
        Element element = new Element(tag, baseUri, attributes);

        // When
        Element clonedElement = element.shallowClone();

        // Then
        assertEquals(tag, clonedElement.tag());
        assertNull(clonedElement.baseUri());
        assertEquals(attributes.size(), clonedElement.attributes().size());
        assertEquals(attributes.get("class"), clonedElement.attributes().get("class"));
    }
}
