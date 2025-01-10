
package org.jsoup.nodes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Element_ownTextTest {
    private Element element;

    @BeforeEach
    public void setUp() {
        element = new Element("div");
    }

    @Test
    public void testOwnTextWithNoChildNodes() {
        assertEquals("", element.ownText());
    }

    @Test
    public void testOwnTextWithSingleTextNode() {
        element.appendText("Hello World");
        assertEquals("Hello World", element.ownText());
    }

    @Test
    public void testOwnTextWithMultipleTextNodes() {
        element.appendText("Hello");
        element.appendText(" ");
        element.appendText("World");
        assertEquals("Hello World", element.ownText());
    }

    @Test
    public void testOwnTextWithMixedNodes() {
        element.appendText("Hello");
        element.appendChild(new Element("br"));
        element.appendText("World");
        assertEquals("HelloWorld", element.ownText());
    }

    @Test
    public void testOwnTextWithWhitespaceNodes() {
        element.appendText(" Hello ");
        element.appendText(" World ");
        assertEquals("Hello World", element.ownText());
    }
}
