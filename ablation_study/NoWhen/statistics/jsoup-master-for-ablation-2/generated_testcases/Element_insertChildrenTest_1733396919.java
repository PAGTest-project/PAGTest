
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
        Node child1 = new TextNode("Child 1");
        Node child2 = new TextNode("Child 2");
        Collection<Node> children = Arrays.asList(child1, child2);

        element.insertChildren(0, children);

        assertEquals(2, element.childNodeSize());
        assertEquals(child1, element.child(0));
        assertEquals(child2, element.child(1));
    }

    @Test
    public void testInsertChildrenNegativeIndex() {
        Node child1 = new TextNode("Child 1");
        Node child2 = new TextNode("Child 2");
        Collection<Node> children = Arrays.asList(child1, child2);

        element.appendChild(new TextNode("Existing Child"));
        element.insertChildren(-1, children);

        assertEquals(3, element.childNodeSize());
        assertEquals(child1, element.child(1));
        assertEquals(child2, element.child(2));
    }

    @Test
    public void testInsertChildrenOutOfBoundsIndex() {
        Node child1 = new TextNode("Child 1");
        Node child2 = new TextNode("Child 2");
        Collection<Node> children = Arrays.asList(child1, child2);

        assertThrows(IllegalArgumentException.class, () -> {
            element.insertChildren(2, children);
        });
    }

    @Test
    public void testInsertChildrenNullCollection() {
        assertThrows(IllegalArgumentException.class, () -> {
            element.insertChildren(0, null);
        });
    }
}
