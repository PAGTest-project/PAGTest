
package org.jsoup.nodes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Element_nextElementSiblingTest {
    private Element element;
    private Element siblingElement;

    @BeforeEach
    public void setUp() {
        element = new Element("div");
        siblingElement = new Element("span");
    }

    @Test
    public void testNextElementSiblingWithNoSibling() {
        assertNull(element.nextElementSibling());
    }

    @Test
    public void testNextElementSiblingWithNonElementSibling() {
        Node textNode = new TextNode("text");
        element.parent().appendChild(textNode);
        assertNull(element.nextElementSibling());
    }

    @Test
    public void testNextElementSiblingWithElementSibling() {
        element.parent().appendChild(siblingElement);
        assertEquals(siblingElement, element.nextElementSibling());
    }
}
