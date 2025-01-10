
package org.jsoup.nodes;

import org.jsoup.Jsoup;
import org.jsoup.internal.StringUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Element_ownTextTest {
    private Element element;

    @BeforeEach
    public void setUp() {
        element = new Element("p");
    }

    @Test
    public void testOwnTextWithNoChildren() {
        element.text("Hello World");
        assertEquals("Hello World", element.ownText());
    }

    @Test
    public void testOwnTextWithChildren() {
        element.text("Hello");
        Element child = new Element("span");
        child.text("World");
        element.appendChild(child);
        assertEquals("Hello", element.ownText());
    }

    @Test
    public void testOwnTextWithWhitespace() {
        element.text("   Hello World   ");
        assertEquals("Hello World", element.ownText());
    }

    @Test
    public void testOwnTextWithNewlines() {
        element.text("Hello\nWorld");
        assertEquals("Hello World", element.ownText());
    }

    @Test
    public void testOwnTextWithMixedContent() {
        element.text("Hello");
        Element child1 = new Element("span");
        child1.text("World");
        Element child2 = new Element("span");
        child2.text("!");
        element.appendChild(child1);
        element.appendChild(child2);
        assertEquals("Hello", element.ownText());
    }

    @Test
    public void testOwnTextWithEmptyElement() {
        assertEquals("", element.ownText());
    }

    @Test
    public void testOwnTextWithDataNode() {
        element.text("Hello");
        DataNode dataNode = new DataNode("World");
        element.appendChild(dataNode);
        assertEquals("Hello", element.ownText());
    }

    @Test
    public void testOwnTextWithCommentNode() {
        element.text("Hello");
        Comment commentNode = new Comment("World");
        element.appendChild(commentNode);
        assertEquals("Hello", element.ownText());
    }

    @Test
    public void testOwnTextWithCDataNode() {
        element.text("Hello");
        CDataNode cDataNode = new CDataNode("World");
        element.appendChild(cDataNode);
        assertEquals("Hello", element.ownText());
    }

    @Test
    public void testOwnTextWithMultipleTextNodes() {
        element.text("Hello");
        TextNode textNode1 = new TextNode(" ");
        TextNode textNode2 = new TextNode("World");
        element.appendChild(textNode1);
        element.appendChild(textNode2);
        assertEquals("Hello", element.ownText());
    }
}
