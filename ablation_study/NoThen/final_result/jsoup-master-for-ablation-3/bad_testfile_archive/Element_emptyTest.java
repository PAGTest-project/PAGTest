
package org.jsoup.nodes;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class Element_emptyTest {

    @Test
    public void testEmptyWithChildren() {
        Element element = new Element("div");
        Node child1 = new TextNode("Child1", "");
        Node child2 = new TextNode("Child2", "");
        element.appendChild(child1);
        element.appendChild(child2);

        Element result = element.empty();

        assertEquals(element, result);
        assertEquals(0, element.childNodeSize());
        assertNull(child1.parentNode());
        assertNull(child2.parentNode());
    }

    @Test
    public void testEmptyWithoutChildren() {
        Element element = new Element("div");

        Element result = element.empty();

        assertEquals(element, result);
        assertEquals(0, element.childNodeSize());
    }
}
