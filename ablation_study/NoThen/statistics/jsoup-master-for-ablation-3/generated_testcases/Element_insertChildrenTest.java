
package org.jsoup.nodes;

import org.jsoup.helper.Validate;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collection;
import static org.junit.jupiter.api.Assertions.*;

class Element_insertChildrenTest {

    @Test
    void testInsertChildren_ValidIndex() {
        Element element = new Element("div");
        Collection<Node> children = Arrays.asList(new Element("span"), new TextNode("text"));
        int index = 0;

        Element result = element.insertChildren(index, children);

        assertEquals(element, result);
        assertEquals(2, element.childNodeSize());
    }

    @Test
    void testInsertChildren_NegativeIndex() {
        Element element = new Element("div");
        Collection<Node> children = Arrays.asList(new Element("span"), new TextNode("text"));
        int index = -1;

        Element result = element.insertChildren(index, children);

        assertEquals(element, result);
        assertEquals(2, element.childNodeSize());
    }

    @Test
    void testInsertChildren_NullChildren() {
        Element element = new Element("div");
        Collection<Node> children = null;
        int index = 0;

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            element.insertChildren(index, children);
        });

        assertEquals("Children collection to be inserted must not be null.", exception.getMessage());
    }

    @Test
    void testInsertChildren_OutOfBoundsIndex() {
        Element element = new Element("div");
        Collection<Node> children = Arrays.asList(new Element("span"), new TextNode("text"));
        int index = 2;

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            element.insertChildren(index, children);
        });

        assertEquals("Insert position out of bounds.", exception.getMessage());
    }
}
