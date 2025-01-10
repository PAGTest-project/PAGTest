
package org.jsoup.nodes;

import org.jsoup.Jsoup;
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
    public void testOwnTextWithNoTextNodes() {
        assertEquals("", element.ownText());
    }

    @Test
    public void testOwnTextWithSingleTextNode() {
        element.appendText("Hello");
        assertEquals("Hello", element.ownText());
    }

    @Test
    public void testOwnTextWithMultipleTextNodes() {
        element.appendText("Hello");
        element.appendText(" World");
        assertEquals("Hello World", element.ownText());
    }

    @Test
    public void testOwnTextWithPrependedTextNode() {
        element.appendText("World");
        element.prependText("Hello ");
        assertEquals("Hello World", element.ownText());
    }

    @Test
    public void testOwnTextWithMixedContent() {
        element.appendText("Hello");
        Element childElement = new Element("span");
        childElement.appendText("Child");
        element.appendChild(childElement);
        element.appendText(" World");
        assertEquals("Hello  World", element.ownText());
    }
}
