
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
        Node child = new Element("p");
        element.appendChild(child);
        assertEquals(1, element.childNodes.size());
        assertEquals(child, element.childNodes.get(0));
    }

    @Test
    public void testAppendChildNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            element.appendChild(null);
        });
    }

    @Test
    public void testAppendChildMultiple() {
        Node child1 = new Element("p");
        Node child2 = new Element("span");
        element.appendChild(child1);
        element.appendChild(child2);
        assertEquals(2, element.childNodes.size());
        assertEquals(child1, element.childNodes.get(0));
        assertEquals(child2, element.childNodes.get(1));
    }

    @Test
    public void testAppendChildAfterEmpty() {
        Node child = new Element("p");
        element.appendChild(child);
        element.empty();
        element.appendChild(child);
        assertEquals(1, element.childNodes.size());
        assertEquals(child, element.childNodes.get(0));
    }
}
