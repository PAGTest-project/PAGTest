
package org.jsoup.nodes;

import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class Element_siblingElementsTest {

    @Test
    void testSiblingElements_NoParent() {
        Element element = new Element("div");
        element.parentNode = null;

        Elements siblings = element.siblingElements();

        assertEquals(0, siblings.size());
    }

    @Test
    void testSiblingElements_WithParent() {
        Element parent = new Element("div");
        Element child1 = new Element("span");
        Element child2 = new Element("span");
        Element child3 = new Element("span");

        parent.appendChild(child1);
        parent.appendChild(child2);
        parent.appendChild(child3);

        child2.parentNode = parent;

        Elements siblings = child2.siblingElements();

        assertEquals(2, siblings.size());
        assertTrue(siblings.contains(child1));
        assertTrue(siblings.contains(child3));
    }
}
