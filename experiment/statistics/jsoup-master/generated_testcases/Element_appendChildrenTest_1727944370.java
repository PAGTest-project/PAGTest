
package org.jsoup.nodes;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collection;
import static org.junit.jupiter.api.Assertions.*;

public class Element_appendChildrenTest {
    private Element element;

    @BeforeEach
    public void setUp() {
        element = new Element("div");
    }

    @Test
    public void testAppendChildren() {
        // Given
        Node child1 = new Element("span");
        Node child2 = new TextNode("text");
        Collection<? extends Node> children = Arrays.asList(child1, child2);

        // When
        Element result = element.appendChildren(children);

        // Then
        assertEquals(element, result);
        assertEquals(2, element.childNodeSize());
        assertEquals(child1, element.child(0));
        assertEquals(child2, element.child(1));
    }
}
