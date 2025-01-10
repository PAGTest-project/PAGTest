
package org.jsoup.nodes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Element_appendChildTest {
    private Element element;

    @BeforeEach
    public void setUp() {
        element = new Element("div");
    }

    @Test
    public void testAppendChild() {
        Node child = new Element("span");
        Element result = element.appendChild(child);
        assertEquals(element, result);
        assertEquals(1, element.childNodeSize());
        assertEquals(child, element.child(0));
    }

    @Test
    public void testAppendChildNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            element.appendChild(null);
        });
    }

    @Test
    public void testAppendChildMultiple() {
        Node child1 = new Element("span");
        Node child2 = new TextNode("text");
        element.appendChild(child1);
        element.appendChild(child2);
        assertEquals(2, element.childNodeSize());
        assertEquals(child1, element.child(0));
        assertEquals(child2, element.child(1));
    }
}
