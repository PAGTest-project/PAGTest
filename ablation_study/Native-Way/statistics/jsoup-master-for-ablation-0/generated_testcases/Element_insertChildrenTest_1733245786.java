
package org.jsoup.nodes;

import org.jsoup.Jsoup;
import org.jsoup.helper.ValidationException;
import org.jsoup.internal.StringUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

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
        assertEquals(element, result);
        assertEquals(2, element.childNodeSize());
        assertEquals("Child1", element.child(0).outerHtml());
        assertEquals("Child2", element.child(1).outerHtml());
    }

    @Test
    public void testInsertChildrenNegativeIndex() {
        Collection<Node> children = Arrays.asList(new TextNode("Child1"), new TextNode("Child2"));
        Element result = element.insertChildren(-1, children);
        assertEquals(element, result);
        assertEquals(2, element.childNodeSize());
        assertEquals("Child1", element.child(0).outerHtml());
        assertEquals("Child2", element.child(1).outerHtml());
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

    @Test
    public void testInsertChildrenEmptyCollection() {
        Collection<Node> children = Collections.emptyList();
        Element result = element.insertChildren(0, children);
        assertEquals(element, result);
        assertEquals(0, element.childNodeSize());
    }

    @Test
    public void testInsertChildrenExistingContent() {
        element.appendChild(new TextNode("ExistingChild"));
        Collection<Node> children = Arrays.asList(new TextNode("Child1"), new TextNode("Child2"));
        Element result = element.insertChildren(1, children);
        assertEquals(element, result);
        assertEquals(3, element.childNodeSize());
        assertEquals("ExistingChild", element.child(0).outerHtml());
        assertEquals("Child1", element.child(1).outerHtml());
        assertEquals("Child2", element.child(2).outerHtml());
    }
}
