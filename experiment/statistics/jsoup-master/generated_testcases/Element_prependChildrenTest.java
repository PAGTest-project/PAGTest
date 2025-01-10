
package org.jsoup.nodes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

public class Element_prependChildrenTest {
    private Element element;

    @BeforeEach
    public void setUp() {
        element = new Element("div");
    }

    @Test
    public void testPrependChildren() {
        // Given
        Node child1 = new TextNode("Child1");
        Node child2 = new TextNode("Child2");
        Collection<? extends Node> children = Arrays.asList(child1, child2);

        // When
        element.prependChildren(children);

        // Then
        assertEquals(2, element.childNodeSize());
        assertEquals(child1, element.child(0));
        assertEquals(child2, element.child(1));
    }
}
