
package org.jsoup.nodes;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Element_appendChildTest {

    @Test
    void testAppendChild() {
        // Given
        Element parent = new Element("div");
        Node child = new Element("span");

        // When
        Element result = parent.appendChild(child);

        // Then
        assertEquals(1, parent.childNodeSize());
        assertEquals(child, parent.childNodes().get(0));
        assertEquals(parent, child.parent());
        assertEquals(0, child.siblingIndex());
        assertSame(parent, result);
    }
}
