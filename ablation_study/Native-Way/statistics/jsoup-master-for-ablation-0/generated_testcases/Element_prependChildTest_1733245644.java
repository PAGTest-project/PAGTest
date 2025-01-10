
package org.jsoup.nodes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Element_prependChildTest {
    private Element element;

    @BeforeEach
    public void setUp() {
        element = new Element("div");
    }

    @Test
    public void testPrependChild() {
        Node child = new TextNode("Child Text");
        element.prependChild(child);
        assertEquals(child, element.childNode(0));
    }

    @Test
    public void testPrependChildNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            element.prependChild(null);
        });
    }

    @Test
    public void testPrependChildMultiple() {
        Node child1 = new TextNode("Child 1");
        Node child2 = new TextNode("Child 2");
        element.prependChild(child1);
        element.prependChild(child2);
        assertEquals(child2, element.childNode(0));
        assertEquals(child1, element.childNode(1));
    }
}
