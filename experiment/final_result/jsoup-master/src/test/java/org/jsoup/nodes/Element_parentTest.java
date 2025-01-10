
package org.jsoup.nodes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Element_parentTest {
    private Element element;

    @BeforeEach
    public void setUp() {
        element = new Element("div");
    }

    @Test
    public void testParentWithParentNode() {
        Element parentElement = new Element("div");
        element.parentNode = parentElement;
        assertEquals(parentElement, element.parent());
    }

    @Test
    public void testParentWithoutParentNode() {
        assertNull(element.parent());
    }
}
