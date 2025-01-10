
package org.jsoup.nodes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collection;
import static org.junit.jupiter.api.Assertions.*;

public class Element_insertChildrenTest {
    private Element element;

    @BeforeEach
    public void setUp() {
        element = new Element("div");
    }

    @Test
    public void testInsertChildrenValidIndex() {
        Collection<Node> children = Arrays.asList(new TextNode("Child1"), new TextNode("Child2"));
        Element result = element.insertChildren(0, children);

        assertEquals(2, element.childNodeSize());
        assertEquals("Child1", element.child(0).outerHtml());
        assertEquals("Child2", element.child(1).outerHtml());
        assertSame(element, result);
    }

    @Test
    public void testInsertChildrenNegativeIndex() {
        Collection<Node> children = Arrays.asList(new TextNode("Child1"), new TextNode("Child2"));
        Element result = element.insertChildren(-1, children);

        assertEquals(2, element.childNodeSize());
        assertEquals("Child1", element.child(0).outerHtml());
        assertEquals("Child2", element.child(1).outerHtml());
        assertSame(element, result);
    }

    @Test
    public void testInsertChildrenOutOfBoundsIndex() {
        Collection<Node> children = Arrays.asList(new TextNode("Child1"), new TextNode("Child2"));
        assertThrows(IllegalArgumentException.class, () -> element.insertChildren(3, children));
    }

    @Test
    public void testInsertChildrenNullCollection() {
        assertThrows(IllegalArgumentException.class, () -> element.insertChildren(0, null));
    }
}
