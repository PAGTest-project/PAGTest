
package org.jsoup.nodes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Element_lastElementChildTest {
    private Element element;

    @BeforeEach
    public void setUp() {
        element = new Element("div");
    }

    @Test
    public void testLastElementChildWithNoChildren() {
        assertNull(element.lastElementChild());
    }

    @Test
    public void testLastElementChildWithNonElementChildren() {
        element.appendChild(new TextNode("Text1"));
        element.appendChild(new TextNode("Text2"));
        assertNull(element.lastElementChild());
    }

    @Test
    public void testLastElementChildWithElementChildren() {
        Element child1 = new Element("span");
        Element child2 = new Element("p");
        element.appendChild(child1);
        element.appendChild(new TextNode("Text"));
        element.appendChild(child2);
        assertEquals(child2, element.lastElementChild());
    }
}
