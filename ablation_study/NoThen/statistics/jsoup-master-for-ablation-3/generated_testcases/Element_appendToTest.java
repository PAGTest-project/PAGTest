
package org.jsoup.nodes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Element_appendToTest {
    private Element parent;
    private Element child;

    @BeforeEach
    public void setUp() {
        parent = new Element("div");
        child = new Element("span");
    }

    @Test
    public void testAppendToSuccess() {
        Element result = child.appendTo(parent);
        assertEquals(child, result);
        assertEquals(1, parent.childNodeSize());
        assertEquals(child, parent.child(0));
    }

    @Test
    public void testAppendToNullParent() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            child.appendTo(null);
        });
        assertEquals("Object must not be null", exception.getMessage());
    }
}
