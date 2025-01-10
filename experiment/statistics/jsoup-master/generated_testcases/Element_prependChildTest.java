
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
    }

    @Test
    public void testPrependChildNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            element.prependChild(null);
        });
    }
}
