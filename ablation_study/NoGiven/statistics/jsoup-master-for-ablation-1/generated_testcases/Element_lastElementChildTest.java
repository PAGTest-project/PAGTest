
package org.jsoup.nodes;

import org.jspecify.annotations.Nullable;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Element_lastElementChildTest {

    @Test
    void testLastElementChild() {
        // Given
        Element parent = new Element("parent");
        Element child1 = new Element("child1");
        Element child2 = new Element("child2");
        TextNode textNode = new TextNode("text");

        // When
        parent.appendChild(child1);
        parent.appendChild(textNode);
        parent.appendChild(child2);

        // Then
        Element lastElementChild = parent.lastElementChild();
        assertNotNull(lastElementChild);
        assertEquals("child2", lastElementChild.tagName());
    }

    @Test
    void testLastElementChildWithNoElementChildren() {
        // Given
        Element parent = new Element("parent");
        TextNode textNode = new TextNode("text");

        // When
        parent.appendChild(textNode);

        // Then
        Element lastElementChild = parent.lastElementChild();
        assertNull(lastElementChild);
    }
}
