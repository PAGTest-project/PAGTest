
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

        // Verify the child was added correctly
        assertEquals(1, element.childNodeSize());
        assertEquals(child, element.child(0));
        assertEquals(element, child.parent());
        assertEquals(0, child.siblingIndex());

        // Verify the method returns the correct element
        assertEquals(element, result);
    }

    @Test
    public void testAppendChildNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            element.appendChild(null);
        });
    }
}
