
package org.jsoup.nodes;

import org.jsoup.helper.Validate;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collection;
import static org.junit.jupiter.api.Assertions.*;

class Element_insertChildrenTest {

    @Test
    void testInsertChildren() {
        // Given
        Element element = new Element("div");
        Collection<Node> children = Arrays.asList(new Element("span"), new TextNode("text"));

        // When
        Element result = element.insertChildren(0, children);

        // Then
        assertEquals(2, element.childNodeSize());
        assertEquals("span", element.child(0).nodeName());
        assertEquals("#text", element.child(1).nodeName());
    }

    @Test
    void testInsertChildrenWithNegativeIndex() {
        // Given
        Element element = new Element("div");
        Collection<Node> children = Arrays.asList(new Element("span"), new TextNode("text"));

        // When
        Element result = element.insertChildren(-1, children);

        // Then
        assertEquals(2, element.childNodeSize());
        assertEquals("span", element.child(0).nodeName());
        assertEquals("#text", element.child(1).nodeName());
    }

    @Test
    void testInsertChildrenWithOutOfBoundsIndex() {
        // Given
        Element element = new Element("div");
        Collection<Node> children = Arrays.asList(new Element("span"), new TextNode("text"));

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> element.insertChildren(3, children));
    }

    @Test
    void testInsertChildrenWithNullCollection() {
        // Given
        Element element = new Element("div");

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> element.insertChildren(0, null));
    }
}
