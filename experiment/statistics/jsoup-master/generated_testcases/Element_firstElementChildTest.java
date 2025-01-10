
package org.jsoup.nodes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Element_firstElementChildTest {
    private Element element;

    @BeforeEach
    public void setUp() {
        element = new Element("div");
    }

    @Test
    public void testFirstElementChildWithElement() {
        Element childElement = new Element("span");
        element.appendChild(childElement);
        assertEquals(childElement, element.firstElementChild());
    }

    @Test
    public void testFirstElementChildWithNonElement() {
        TextNode textNode = new TextNode("text");
        element.appendChild(textNode);
        assertNull(element.firstElementChild());
    }

    @Test
    public void testFirstElementChildWithMixedChildren() {
        TextNode textNode = new TextNode("text");
        Element childElement = new Element("span");
        element.appendChild(textNode);
        element.appendChild(childElement);
        assertEquals(childElement, element.firstElementChild());
    }

    @Test
    public void testFirstElementChildWithNoChildren() {
        assertNull(element.firstElementChild());
    }
}
