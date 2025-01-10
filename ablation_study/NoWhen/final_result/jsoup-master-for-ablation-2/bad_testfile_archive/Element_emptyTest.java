
package org.jsoup.nodes;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Element_emptyTest {

    @Test
    void testEmptyWithChildren() {
        Element element = new Element("div");
        Node child1 = new TextNode("Child1", "");
        Node child2 = new TextNode("Child2", "");
        element.appendChild(child1);
        element.appendChild(child2);

        Element result = element.empty();

        assertTrue(element.childNodes().isEmpty());
        assertNull(child1.parentNode());
        assertNull(child2.parentNode());
        assertEquals(element, result);
    }

    @Test
    void testEmptyWithoutChildren() {
        Element element = new Element("div");

        Element result = element.empty();

        assertTrue(element.childNodes().isEmpty());
        assertEquals(element, result);
    }
}
