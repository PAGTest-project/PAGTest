
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
        Node child = new Element("span");
        element.prependChild(child);

        assertEquals(child, element.child(0));
        assertEquals(1, element.childrenSize());
    }

    @Test
    public void testPrependChildNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            element.prependChild(null);
        });
    }

    @Test
    public void testPrependChildMultiple() {
        Node child1 = new Element("span");
        Node child2 = new Element("p");

        element.prependChild(child1);
        element.prependChild(child2);

        assertEquals(child2, element.child(0));
        assertEquals(child1, element.child(1));
        assertEquals(2, element.childrenSize());
    }
}
