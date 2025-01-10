
package org.jsoup.nodes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Element_nextElementSiblingTest {
    private Element element;
    private Element siblingElement;
    private Element parentElement;

    @BeforeEach
    public void setUp() {
        parentElement = new Element("div");
        element = new Element("div");
        siblingElement = new Element("span");
        parentElement.appendChild(element);
    }

    @Test
    public void testNextElementSiblingWithNoSibling() {
        assertNull(element.nextElementSibling());
    }

    @Test
    public void testNextElementSiblingWithNonElementSibling() {
        Node textNode = new TextNode("text");
        parentElement.appendChild(textNode);
        assertNull(element.nextElementSibling());
    }

    @Test
    public void testNextElementSiblingWithElementSibling() {
        parentElement.appendChild(siblingElement);
        assertEquals(siblingElement, element.nextElementSibling());
    }
}
